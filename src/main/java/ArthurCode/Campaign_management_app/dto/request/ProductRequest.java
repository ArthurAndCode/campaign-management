package ArthurCode.Campaign_management_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Product name must not be blank")
    private String name;

    @NotNull(message = "Owner ID must be provided")
    private Long ownerId;

}
