package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownRepository extends JpaRepository<Town, Long> {

    List<Town> findAllByOrderByNameAsc();
}
