package euljiro.project.childcareproducts.api.user.sharecode.dto;

import euljiro.project.childcareproducts.domain.user.sharecode.ShareCode;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ShareCodeDtoMapper {

    ShareCodeDto.ShareCodeResponse of(ShareCode shareCode);
}
