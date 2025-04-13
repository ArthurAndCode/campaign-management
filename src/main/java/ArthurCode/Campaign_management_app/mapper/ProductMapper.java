package ArthurCode.Campaign_management_app.mapper;

import ArthurCode.Campaign_management_app.dto.request.ProductRequest;
import ArthurCode.Campaign_management_app.dto.response.ProductResponse;
import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final OwnerMapper ownerMapper;

    public ProductMapper(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setOwner(ownerMapper.toResponse(product.getOwner()));
        return response;
    }

    public Product fromRequest(ProductRequest request, Owner owner) {
        Product product = new Product();
        product.setName(request.getName());
        product.setOwner(owner);
        return product;
    }
}



