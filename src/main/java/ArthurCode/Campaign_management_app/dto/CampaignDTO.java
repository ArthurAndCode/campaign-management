package ArthurCode.Campaign_management_app.dto;

import ArthurCode.Campaign_management_app.model.Product;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CampaignDTO {
    @NotBlank
    private String campaignName;

    @NotEmpty
    private List<String> keywords;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal bidAmount;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal campaignFund;

    @NotNull
    private Boolean status;

    @NotBlank
    private String town;

    @NotNull
    @Min(1)
    private Integer radius;

    @NotNull
    private Long productId;

}
