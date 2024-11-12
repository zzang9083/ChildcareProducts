package euljiro.project.childcareproducts.api.item.dto;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {


    //register
    ItemCommand.RegisterItemRequest of (ItemDto.RegisterItemRequest registerItemRequest);


    //retrieve
    ItemDto.GetItemResponse of (ItemInfo.Main itemInfo);
}
