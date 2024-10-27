package euljiro.project.childcareproducts.api.complex.dto;

import euljiro.project.childcareproducts.application.complex.dto.UserRegisterInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserInfoRegisterDtoMapper {

    UserInfoRegisterDto.UserInfoRegisterResponse of(UserRegisterInfo.UserInfoRegisterResponse registerResponse);
}
