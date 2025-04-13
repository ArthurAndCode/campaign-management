package ArthurCode.Campaign_management_app.mapper;

import ArthurCode.Campaign_management_app.dto.response.CampaignResponse;
import ArthurCode.Campaign_management_app.model.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    private final OwnerMapper ownerMapper;

    public CampaignMapper(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    public CampaignResponse toResponse(Campaign campaign) {
        CampaignResponse response = new CampaignResponse();
        response.setId(campaign.getId());
        response.setProductName(campaign.getProduct().getName());
        response.setCampaignName(campaign.getCampaignName());
        response.setKeywords(campaign.getKeywords());
        response.setBidAmount(campaign.getBidAmount());
        response.setCampaignFund(campaign.getCampaignFund());
        response.setStatus(campaign.getStatus());
        response.setTown(campaign.getTown());
        response.setRadius(campaign.getRadius());
        response.setOwner(ownerMapper.toResponse(campaign.getOwner()));
        return response;
    }
}


