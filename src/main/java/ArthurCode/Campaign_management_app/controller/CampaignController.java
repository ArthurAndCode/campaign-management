package ArthurCode.Campaign_management_app.controller;

import ArthurCode.Campaign_management_app.dto.CampaignDTO;
import ArthurCode.Campaign_management_app.model.Campaign;
import ArthurCode.Campaign_management_app.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
@CrossOrigin(origins = "http://localhost:5137", allowCredentials = "true")
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<Campaign> getAll() {
        return campaignService.getAll();
    }

    @GetMapping("/{id}")
    public Campaign getById(@PathVariable Long id) {
        return campaignService.getById(id);
    }

    @PostMapping
    public Campaign create(@Valid @RequestBody CampaignDTO dto) {
        return campaignService.create(dto);
    }

    @PutMapping("/{id}")
    public Campaign update(@PathVariable Long id, @Valid @RequestBody CampaignDTO dto) {
        return campaignService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        campaignService.delete(id);
    }
}
