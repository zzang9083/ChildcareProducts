package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.group.dto.GroupItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemService;
import euljiro.project.childcareproducts.domain.product.ProductService;
import euljiro.project.childcareproducts.infrastructure.user.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemProductService {

    private final ItemService itemService;
    private final ProductService productService;

    private final PuchaseHistoryService puchaseHistoryService;

    private final TokenUtil tokenUtil;



    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {

        long itemId = tokenUtil.getIdByToken(command.getItemToken());
        Item item = itemService.getItem(itemId);

        ItemProductInfo.RegisterProductResponse response = productService.registerProduct(item, command);

        tokenUtil.storeIdByToken(response.getProductToken(), response.getProductId());

        return response.getProductToken();
    }

    public void confirmPurchase(ItemProductCommand.ConfirmProductRequest command) {


        long itemId = tokenUtil.getIdByToken(command.getItemToken());
        long selectedProductId = tokenUtil.getIdByToken(command.getProductToken());

        // 품목 구매완료처리
        itemService.confirmPurchase(itemId, selectedProductId);

        // 구매정보 조회
        GroupItemInfo.SpecificItemAndProductResponse purchaseInfo
                                    = itemService.findWithSpecificProduct(itemId, selectedProductId);

        // 구매이력 생성
        var itemCommand =
                new ItemCommand.ConfirmPurchaseRequest(command, purchaseInfo);
        puchaseHistoryService.addPurchaseHistory(itemCommand);


    }



}
