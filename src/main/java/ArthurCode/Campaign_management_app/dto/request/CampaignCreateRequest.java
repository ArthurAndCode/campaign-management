package ArthurCode.Campaign_management_app.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CampaignCreateRequest implements CampaignRequestData {

    @NotBlank(message = "Campaign name must not be blank")
    private String campaignName;

    @NotEmpty(message = "At least one keyword must be provided")
    private List<String> keywords;

    @NotNull(message = "Bid amount must be provided")
    @DecimalMin(value = "0.01", message = "Bid amount must be at least 0.01")
    private BigDecimal bidAmount;

    @NotNull(message = "Campaign fund must be provided")
    @DecimalMin(value = "0.01", message = "Campaign fund must be at least 0.01")
    private BigDecimal campaignFund;

    @NotNull(message = "Campaign status must be specified")
    private Boolean status;

    @NotBlank(message = "Town must not be blank")
    private String town;

    @NotNull(message = "Radius must be provided")
    @Min(value = 1, message = "Radius must be at least 1")
    private Integer radius;

    @NotNull(message = "Product ID must be provided")
    private Long productId;

    @NotNull(message = "Owner ID must be provided")
    private Long ownerId;
}

