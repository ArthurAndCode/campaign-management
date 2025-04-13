package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOwner(Owner owner, Pageable pageable);

}
