package euljiro.project.childcareproducts.api.group.dto;

import euljiro.project.childcareproducts.application.group.dto.GroupChildInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ChildDtoMapper {

    ChildDto.GetChildrenResponse toGetChildrenResponse(GroupChildInfo.GetChildrenResponse source);

    List<ChildDto.Main> toMainList(List<GroupChildInfo.Main> source);

    ChildDto.Main toMain(GroupChildInfo.Main source);

}
