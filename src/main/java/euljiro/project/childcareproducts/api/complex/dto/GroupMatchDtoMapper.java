package euljiro.project.childcareproducts.api.complex.dto;


import euljiro.project.childcareproducts.application.complex.dto.GroupMatchInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GroupMatchDtoMapper {

    GroupMatchDto.MatchGroupResponse of (GroupMatchInfo.MatchGroupResponse matchGroupResponse);
}
