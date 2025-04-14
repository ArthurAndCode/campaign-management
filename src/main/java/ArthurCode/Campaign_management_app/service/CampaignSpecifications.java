package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.model.Campaign;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class CampaignSpecifications {

    public static Specification<Campaign> withFilters(
            Long ownerId,
            Long productId,
            Boolean status,
            String town,
            String campaignName,
            String keyword
    ) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (ownerId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("owner").get("id"), ownerId));
            }

            if (productId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("product").get("id"), productId));
            }

            if (status != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }

            if (town != null && !town.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("town")), "%" + town.toLowerCase() + "%"));
            }

            if (campaignName != null && !campaignName.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("campaignName")), "%" + campaignName.toLowerCase() + "%"));
            }

            if (keyword != null && !keyword.isBlank()) {
                Join<Campaign, String> keywordJoin = root.join("keywords");
                predicate = cb.and(predicate, cb.like(cb.lower(keywordJoin), "%" + keyword.toLowerCase() + "%"));
                assert query != null;
                query.distinct(true);
            }

            return predicate;
        };
    }
}

