package euljiro.project.childcareproducts.application.item;

import euljiro.project.childcareproducts.application.item.dto.ItemCommand;
import euljiro.project.childcareproducts.application.item.dto.ItemInfo;
import euljiro.project.childcareproducts.application.item.dto.ItemProductCommand;
import euljiro.project.childcareproducts.domain.group.history.PuchaseHistoryService;
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

    private final ItemService itemService;
    private final ProductService productService;

    private final PuchaseHistoryService puchaseHistoryService;



    public String registerProduct(ItemProductCommand.RegisterProductRequest command) {

        return productService.registerProduct(command);
    }

    public void confirmPurchase(ItemProductCommand.ConfirmProductRequest command) {


        // TOKEN->ID로 변경했을 때
        //ItemInfo.Main item = itemService.getItem(command.getItemToken());

        // 품목 구매완료처리
        var itemCommand = new ItemCommand.ConfirmPurchaseRequest(command.getItemToken(), command.getPayment(), command.getCardNumber());
        ItemInfo.Main completedItemInfo = itemService.confirmPurchase(itemCommand);

        // 구매이력 생성
        command.setGroupToken(completedItemInfo.getGroupToken());
        puchaseHistoryService.addPurchaseHistory(command);


    }



}
