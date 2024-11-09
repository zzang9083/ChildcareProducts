package euljiro.project.childcareproducts.application.complex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class GroupCommand {

    @Getter
    @Builder
    @ToString
    public static class MatchGroupRequest {
        private final String userKey;
        private final String shareCode;
    }
}
