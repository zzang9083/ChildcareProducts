package euljiro.project.childcareproducts.api.product;


import euljiro.project.childcareproducts.api.item.dto.ItemDto;
import euljiro.project.childcareproducts.api.item.dto.ItemDtoMapper;
import euljiro.project.childcareproducts.api.product.dto.ProductDto;
import euljiro.project.childcareproducts.api.product.dto.ProductDtoMapper;
import euljiro.project.childcareproducts.application.item.ItemApplicationService;
import euljiro.project.childcareproducts.application.product.ProductApplicationService;
import euljiro.project.childcareproducts.common.response.CommonResponse;
import euljiro.project.childcareproducts.domain.product.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    private final ProductDtoMapper productDtoMapper;


    @GetMapping("/{productToken}")
    public CommonResponse getProduct(@PathVariable String productToken) {
        var productInfo
                = productApplicationService.getProduct(productToken);

        var response= productDtoMapper.of(productInfo);

        return CommonResponse.success(response);
    }

    @PutMapping("/{productToken}")
    public CommonResponse updateProduct(@PathVariable String productToken
            , @RequestBody @Valid ProductDto.UpdateProductRequest request) {
        var command = request.toCommand(productToken);

        productApplicationService.updateProduct(command);

        return CommonResponse.success(HttpStatus.OK);
    }

    @DeleteMapping("/{productToken}")
    public CommonResponse deleteProduct(@PathVariable String productToken) {
        productApplicationService.deleteProduct(productToken);
        return CommonResponse.success(HttpStatus.OK);
    }

}
