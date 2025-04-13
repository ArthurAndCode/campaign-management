package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.dto.request.ProductRequest;
import ArthurCode.Campaign_management_app.dto.response.ProductResponse;
import ArthurCode.Campaign_management_app.exception.OwnerNotFoundException;
import ArthurCode.Campaign_management_app.exception.ProductNotFoundException;
import ArthurCode.Campaign_management_app.mapper.ProductMapper;
import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.model.Product;
import ArthurCode.Campaign_management_app.repository.OwnerRepository;
import ArthurCode.Campaign_management_app.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OwnerRepository ownerRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, OwnerRepository ownerRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse createProduct(ProductRequest request) {
        Owner owner = getOwnerOrThrow(request.getOwnerId());
        Product product = productMapper.fromRequest(request, owner);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Owner owner = getOwnerOrThrow(request.getOwnerId());
        Product product = getProductOrThrow(id);
        product.setName(request.getName());
        product.setOwner(owner);
        Product updated = productRepository.save(product);
        return productMapper.toResponse(updated);
    }

    public ProductResponse getProductResponseById(Long id) {
        Product product = getProductOrThrow(id);
        return productMapper.toResponse(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProductsByOwnerId(Long ownerId, int page, int size) {
        Owner owner = getOwnerOrThrow(ownerId);
        return productRepository.findAllByOwner(owner, PageRequest.of(page, size));
    }

    private Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private Owner getOwnerOrThrow(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId));
    }

}

