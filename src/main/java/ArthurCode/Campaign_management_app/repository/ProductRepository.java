package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
