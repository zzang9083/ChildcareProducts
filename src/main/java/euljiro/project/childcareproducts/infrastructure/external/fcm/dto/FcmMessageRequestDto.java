package euljiro.project.childcareproducts.infrastructure.external.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FcmMessageRequestDto {

    private String token;

    private String title;

    private String body;
}
