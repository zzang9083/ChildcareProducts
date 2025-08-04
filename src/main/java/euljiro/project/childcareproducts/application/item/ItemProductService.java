package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.event.PushNotificationEvent;
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
import euljiro.project.childcareproducts.domain.user.User;
import euljiro.project.childcareproducts.domain.user.UserService;
import euljiro.project.childcareproducts.infrastructure.external.fcm.dto.PushMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemProductService {

    private final UserService userService;

    private final GroupService groupService;

    private final ItemService itemService;
    private final ProductService productService;

    private final CardService cardService;

    private final PuchaseHistoryService puchaseHistoryService;


    private final ApplicationEventPublisher eventPublisher;


    public ItemProductInfo.Main getItemAndProduct(String itemToken) {
        Item item = itemService.getItemBy(itemToken);

        ItemProductInfo.Main itemAndProducts = itemService.getItemAndProducts(item.getId());

        User user = userService.getUserBy(itemAndProducts.getRegisteredUserId());

        itemAndProducts.setRegisteredUserKey(user.getUserKey());

        return itemAndProducts;
    }


    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {

        User user = userService.getUser(command.getUserKey());

        Item item = itemService.getItemBy(command.getItemToken());

        ItemProductInfo.RegisterProductResponse response = productService.registerProduct(item, user, command);

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
        long selectedProductId = product.getId();

        Card card = null;
        if (command.getPayment() == PuchaseHistory.PAYMENT.CARD) {
            card = cardService.getCard(command.getCardToken());
        }

        // 품목 구매완료처리
        itemService.confirmPurchase(itemId, selectedProductId);
        command.setPurchaseInfo(item, product, card, group);

        // 구매이력 생성
        puchaseHistoryService.addPurchaseHistory(command);

        // push메시지 발송
        for (User user : group.getUserList())
        {
            eventPublisher.publishEvent(
                    new PushNotificationEvent(user.getUserKey(), PushMessageType.PRODUCT_PURCHASED, new Object[]{item.getItemName()})
            );
        }


    }



}
