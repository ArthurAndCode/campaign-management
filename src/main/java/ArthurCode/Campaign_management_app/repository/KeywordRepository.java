package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findAllByOrderByNameAsc();
}
