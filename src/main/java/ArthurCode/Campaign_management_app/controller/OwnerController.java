package ArthurCode.Campaign_management_app.controller;

import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.repository.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin(origins = "http://localhost:5137", allowCredentials = "true")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostMapping("/mock")
    public ResponseEntity<Owner> createMockUser() {
        Owner mockOwner = new Owner();
        mockOwner.setFirstName("Arthur");
        mockOwner.setLastName("Code");
        mockOwner.setEmail("arthur@example.com");
        mockOwner.setPassword("securepassword"); // In real apps: hash this
        mockOwner.setPhone("+48123456789");
        mockOwner.setEmeraldFunds(new BigDecimal("500.00"));

        Owner savedUser = ownerRepository.save(mockOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
