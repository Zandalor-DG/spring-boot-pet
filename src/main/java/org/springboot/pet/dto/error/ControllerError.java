package org.springboot.pet.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import org.springboot.pet.dto.common.CommonResponse;

@Schema(description = "Класс описания пользовательской ошибки")
@Setter
public class ControllerError extends CommonResponse {
}
