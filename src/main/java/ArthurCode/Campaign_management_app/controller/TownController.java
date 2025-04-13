package ArthurCode.Campaign_management_app.controller;

import ArthurCode.Campaign_management_app.service.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/towns")
@CrossOrigin(origins = "http://localhost:5137", allowCredentials = "true")
public class TownController {

    private final TownService townService;

    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllTowns() {
        List<String> towns = townService.getAllTowns();
        return ResponseEntity.ok(towns);
    }
}

