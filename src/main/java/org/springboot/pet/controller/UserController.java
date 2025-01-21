package org.springboot.pet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springboot.pet.annotation.LogExecutionTime;
import org.springboot.pet.dto.error.ControllerError;
import org.springboot.pet.dto.user.AuthUserDto;
import org.springboot.pet.dto.user.RegisterUserDto;
import org.springboot.pet.dto.user.UpdateUserDto;
import org.springboot.pet.dto.user.UserWithTokenDto;
import org.springboot.pet.entity.User;
import org.springboot.pet.exception.ExistUserException;
import org.springboot.pet.exception.PasswordConfirmationFailed;
import org.springboot.pet.repository.IUserRepository;
import org.springboot.pet.services.jwt.JwtService;
import org.springboot.pet.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "User Controller", description = "Контроллер юзера")
public class UserController {

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Авторизация юзера")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Юзер авторизован"),
            @ApiResponse(
                    responseCode = "512",
                    description = "Ошибка при обработке запроса",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ControllerError.class
                                    ))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Неизвестная ошибка",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ControllerError.class
                                    ))}
            )})
    @PostMapping("/sign-in")
    @LogExecutionTime
    public UserWithTokenDto signIn(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description =
                    "{\n" +
                            "  \"userEmail\": \"test@test.com\",\n" +
                            "  \"password\": \"123654789\" ,\n" +
                            "}")
            @RequestBody AuthUserDto authUserDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserDto.getUserEmail(), authUserDto.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(authUserDto.getUserEmail());
            String token = jwtService.generateToken(authUserDto.getUserEmail());

            return new UserWithTokenDto(user, token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @Operation(summary = "Регистрация юзера")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Юзер зарегистрирован"
                    ),
                    @ApiResponse(
                            responseCode = "512",
                            description = "Ошибка при обработке запроса",
                            content = {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ControllerError.class))}
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Неизвестная ошибка",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    implementation = ControllerError.class
                                            ))}
                    )})
    @PostMapping("/sign-up")
    @LogExecutionTime
    public UserWithTokenDto signUp(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "{\n" +
                            "  \"userEmail\": \"test@test.com\",\n" +
                            "  \"password\": \"123654789\" ,\n" +
                            "  \"confirmPassword\": \"123654789\" ,\n" +
                            "}"
            )
            @RequestBody RegisterUserDto registerUserDto) throws ExistUserException, PasswordConfirmationFailed {

        if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            throw new PasswordConfirmationFailed();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerUserDto.getUserEmail(),
                        registerUserDto.getPassword()
                ));
        User existUser = userRepository.findByEmail(registerUserDto.getUserEmail());
        if (authentication.isAuthenticated() || existUser != null) {
            throw new ExistUserException(registerUserDto.getUserEmail());
        }

        User newUser = new User(registerUserDto);
        User user = userService.createUser(newUser);
        String token = jwtService.generateToken(user.getEmail());
        return new UserWithTokenDto(user, token);
    }

    @Operation(summary = "Обновленние данных юзера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "512", description = "Ошибка при обработке запроса", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ControllerError.class))),
            @ApiResponse(responseCode = "500", description = "Неизвестная ошибка", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ControllerError.class)))
    })
    @DeleteMapping("/{id}")
    @LogExecutionTime
    public String deleteUser(
            @PathVariable long id) throws Exception {
        userService.deleteUser(id);
        return "Пользователь успешно удален";
    }

    @Operation(summary = "Обновленние данных юзера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "512", description = "Ошибка при обработке запроса", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ControllerError.class))),
            @ApiResponse(responseCode = "500", description = "Неизвестная ошибка", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ControllerError.class)))
    })
    @PatchMapping("/{id}")
    @LogExecutionTime
    public String updateUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description =
                    "{\n" +
                            "  \"newPassword\": \"987654321\" ,\n" +
                            "  \"newFirstName\": \"987654321\" ,\n" +
                            "  \"newSecondName\": \"987654321\" ,\n" +
                            "}")
            @RequestBody UpdateUserDto updateUserDto, @PathVariable long id) throws Exception {

        userService.updateUser(updateUserDto, id);
        return "Пользователь успешно обновлен";
    }
}
