package euljiro.project.childcareproducts.api.user.dto;


import euljiro.project.childcareproducts.application.child.dto.ChildInfo;
import euljiro.project.childcareproducts.application.user.dto.UserInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserDtoMapper {

    UserDto.UserInfoRegisterResponse of(UserInfo.UserInfoRegisterResponse registerResponse);

    UserDto.ChildRegisterResponse of(ChildInfo.ChildRegisterResponse registerResponse);

}
