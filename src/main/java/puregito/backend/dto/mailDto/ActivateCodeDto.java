package puregito.backend.dto.mailDto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link puregito.backend.entity.ActivateCode}
 */
@Value
public class ActivateCodeDto implements Serializable {
    String code;
}