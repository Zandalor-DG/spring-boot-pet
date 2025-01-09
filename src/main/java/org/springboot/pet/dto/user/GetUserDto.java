package org.springboot.pet.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import org.springboot.pet.entity.User;

@Value
@Schema(description = "Вход в систему пользователя")
public class GetUserDto {
    @Schema(title = "Id пользователя")
    Long id;

    @Schema(title = "Email пользователя")
    String email;

    @Schema(title = "Имя пользователя")
    String first_name;

    @Schema(title = "Фамилия пользователя")
    String second_name;

    public GetUserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.first_name = user.getFirst_name();
        this.second_name = user.getSecond_name();
    }
}
