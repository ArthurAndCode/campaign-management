package ArthurCode.Campaign_management_app.controller;

import ArthurCode.Campaign_management_app.dto.response.OwnerResponse;
import ArthurCode.Campaign_management_app.mapper.OwnerMapper;
import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.repository.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin(origins = "https://campaign-management-app-production.up.railway.app", allowCredentials = "true")
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping
    public ResponseEntity<OwnerResponse> getMockedOwner() {
        Owner owner = ownerRepository.findById(1L).orElseThrow();
        OwnerResponse response = ownerMapper.toResponse(owner);
        return ResponseEntity.ok(response);
    }




}
