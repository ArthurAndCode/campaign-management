package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Page<Campaign> findAllByOwner(Owner owner, Pageable pageable);

}
