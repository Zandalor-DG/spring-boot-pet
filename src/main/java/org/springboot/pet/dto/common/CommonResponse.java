package org.springboot.pet.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Схема общего ответа")
public class CommonResponse {

    @Schema(title = "Код ответа")
    private int statusCode;

    @Schema(title = "Текст сообщения")
    private String message;
}
