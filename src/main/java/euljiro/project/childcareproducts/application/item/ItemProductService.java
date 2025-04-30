package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemProductInfo;
import euljiro.project.childcareproducts.application.product.dto.ProductInfo;
import euljiro.project.childcareproducts.domain.group.Group;
import euljiro.project.childcareproducts.domain.group.GroupService;
import euljiro.project.childcareproducts.domain.group.card.Card;
import euljiro.project.childcareproducts.domain.group.card.CardService;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistory;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
import euljiro.project.childcareproducts.domain.item.Item;
import euljiro.project.childcareproducts.domain.item.ItemService;
import euljiro.project.childcareproducts.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemProductService {

    private final GroupService groupService;

    private final ItemService itemService;
    private final ProductService productService;

    private final CardService cardService;

    private final PuchaseHistoryService puchaseHistoryService;


    public ItemProductInfo.Main getItemAndProduct(String itemToken) {
        Item item = itemService.getItemBy(itemToken);

        return itemService.getItemAndProducts(item.getId());
    }


    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {

        Item item = itemService.getItemBy(command.getItemToken());

        ItemProductInfo.RegisterProductResponse response = productService.registerProduct(item, command);

        return response.getProductToken();
    }

    public void confirmPurchase(ItemProductCommand.ConfirmProductRequest command) {

        //READ ITEM
        Item item = itemService.getItemBy(command.getItemToken());
        long itemId = item.getId();

        //READ GROUP
        Group group = groupService.getGroupBy(item.getGroupId());

        //READ PRODUCT
        ProductInfo.Main product = productService.getProductBy(command.getProductToken());
        long selectedProductId = item.getId();

        Card card = null;
        if(command.getPayment() == PuchaseHistory.PAYMENT.CARD) {
            card = cardService.getCard(command.getCardToken());
        }

        // 품목 구매완료처리
        itemService.confirmPurchase(itemId, selectedProductId);
        command.setPurchaseInfo(item, product, card, group);

        // 구매이력 생성
        puchaseHistoryService.addPurchaseHistory(command);


    }



}
