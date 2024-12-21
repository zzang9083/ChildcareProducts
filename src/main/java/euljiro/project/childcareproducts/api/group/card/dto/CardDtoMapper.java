package euljiro.project.childcareproducts.api.group.card.dto;

import euljiro.project.childcareproducts.api.item.dto.ItemDto;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CardDtoMapper {


    //retrieve
    ItemDto.GetItemResponse of (ItemInfo.Main itemInfo);

    ItemDto.GetItemDetailResponse of (ItemInfo.MainDetail itemInfo);



}
