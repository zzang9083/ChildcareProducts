package euljiro.project.childcareproducts.application.group.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class GroupChildCommand {

    @Getter
    @Builder
    @ToString
    public static class ChangeSelectedChildRequest {

        private String childToken;

    }
}
