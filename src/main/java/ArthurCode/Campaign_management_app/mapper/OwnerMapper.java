package ArthurCode.Campaign_management_app.mapper;

import ArthurCode.Campaign_management_app.dto.response.OwnerResponse;
import ArthurCode.Campaign_management_app.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public OwnerResponse toResponse(Owner owner) {
        OwnerResponse response = new OwnerResponse();
        response.setId(owner.getId());
        response.setFirstName(owner.getFirstName());
        response.setLastName(owner.getLastName());
        response.setEmail(owner.getEmail());
        response.setPhone(owner.getPhone());
        response.setEmeraldFunds(owner.getEmeraldFunds());
        return response;
    }
}

