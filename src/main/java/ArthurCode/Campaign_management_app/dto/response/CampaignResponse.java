package ArthurCode.Campaign_management_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CampaignResponse {
    private Long id;
    private String productName;
    private String campaignName;
    private List<String> keywords;
    private BigDecimal bidAmount;
    private BigDecimal campaignFund;
    private Boolean status;
    private String town;
    private Integer radius;
    private OwnerResponse owner;

}
