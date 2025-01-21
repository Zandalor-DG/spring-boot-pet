package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springboot.pet.entity.User;

@Data
@Setter
@Getter
@Schema(description = "Возвращающийся юзер после авторизации")
public class UserWithTokenDto {
    @Schema(title = "Id пользователя")
    Long id;

    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Имя пользователя")
    String firstName;

    @Schema(title = "Фамилия пользователя")
    String secondName;

    @Schema(title = "AccessToken")
    String token;

    public UserWithTokenDto(User user, String token) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.token = token;
    }
}
