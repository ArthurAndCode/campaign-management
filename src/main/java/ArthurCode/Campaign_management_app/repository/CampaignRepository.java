package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
