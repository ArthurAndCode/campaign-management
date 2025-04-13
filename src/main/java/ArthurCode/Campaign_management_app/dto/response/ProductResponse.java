package ArthurCode.Campaign_management_app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private OwnerResponse owner;
}

