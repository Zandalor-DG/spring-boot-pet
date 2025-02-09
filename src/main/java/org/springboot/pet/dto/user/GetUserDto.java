package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.springboot.pet.entity.User;

@Value
@Schema(description = "Данные пользователя")
public class GetUserDto {
    @Schema(title = "Id пользователя")
    Long id;

    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Имя пользователя")
    String firstName;

    @Schema(title = "Фамилия пользователя")
    String secondName;

    public GetUserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
    }
}
