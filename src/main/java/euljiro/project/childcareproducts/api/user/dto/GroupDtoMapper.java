package euljiro.project.childcareproducts.api.user.dto;


import euljiro.project.childcareproducts.domain.user.group.GroupInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GroupDtoMapper {

    GroupDto.MatchGroupResponse of (GroupInfo.MatchGroupResponse matchGroupResponse);
}
