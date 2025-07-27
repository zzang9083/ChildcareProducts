package euljiro.project.childcareproducts.domain.common;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("남성"), FEMALE("여성");
    private final String description;
}
