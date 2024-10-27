package euljiro.project.childcareproducts.api.complex.dto;

import euljiro.project.childcareproducts.application.complex.dto.LoginInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface LoginDtoMapper {


    LoginDto.GetShareCodeResponse of(String shareCode);

    //UserDto.Main of(UserInfo userInfo);

    LoginDto.LoginResponse of(LoginInfo.LoginResponse loginResponse);

    LoginDto.ReissueResponse of(LoginInfo.ReissueResponse reissueResponse);



}
