package ArthurCode.Campaign_management_app.dto.request;

import java.math.BigDecimal;
import java.util.List;

public interface CampaignRequestData {
    String getCampaignName();
    List<String> getKeywords();
    BigDecimal getBidAmount();
    BigDecimal getCampaignFund();
    Boolean getStatus();
    String getTown();
    Integer getRadius();
}

