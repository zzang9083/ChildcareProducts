package euljiro.project.childcareproducts.api.user.dto;

import euljiro.project.childcareproducts.domain.user.UserInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserDtoMapper {


    UserDto.GetShareCodeResponse of(String shareCode);

    //UserDto.Main of(UserInfo userInfo);

    UserDto.LoginResponse of(UserInfo.LoginResponse loginResponse);

    UserDto.ReissueResponse of(UserInfo.ReissueResponse reissueResponse);



}
