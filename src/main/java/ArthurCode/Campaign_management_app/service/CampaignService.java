package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.dto.CampaignDTO;
import ArthurCode.Campaign_management_app.exception.CampaignNotFoundException;
import ArthurCode.Campaign_management_app.exception.FundsException;
import ArthurCode.Campaign_management_app.exception.ProductNotFoundException;
import ArthurCode.Campaign_management_app.model.Campaign;
import ArthurCode.Campaign_management_app.model.Product;
import ArthurCode.Campaign_management_app.model.AppUser;
import ArthurCode.Campaign_management_app.repository.CampaignRepository;
import ArthurCode.Campaign_management_app.repository.ProductRepository;
import ArthurCode.Campaign_management_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CampaignService(CampaignRepository campaignRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Campaign> getAll() {
        return campaignRepository.findAll();
    }

    public Campaign getById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    @Transactional
    public Campaign create(CampaignDTO dto) {
        Product product = getProductOrThrow(dto.getProductId());
        AppUser appUser = product.getOwner();

        validateSufficientFunds(appUser, dto.getCampaignFund());
        deductFunds(appUser, dto.getCampaignFund());

        Campaign campaign = buildCampaign(dto, product);
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign update(Long id, CampaignDTO dto) {
        Campaign campaign = getById(id);
        Product product = campaign.getProduct();
        AppUser appUser = product.getOwner();

        adjustUserFunds(appUser, campaign.getCampaignFund(), dto.getCampaignFund());

        updateCampaignFields(campaign, dto);
        return campaignRepository.save(campaign);
    }

    @Transactional
    public void delete(Long id) {
        Campaign campaign = getById(id);
        refundUnusedCampaignFund(campaign);
        campaignRepository.deleteById(id);
    }

    //helper methods

    private Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private void validateSufficientFunds(AppUser appUser, BigDecimal amount) {
        if (appUser.getEmeraldFunds().compareTo(amount) < 0) {
            throw new FundsException("Not enough emerald funds");
        }
    }

    private void deductFunds(AppUser appUser, BigDecimal amount) {
        appUser.setEmeraldFunds(appUser.getEmeraldFunds().subtract(amount));
        userRepository.save(appUser);
    }

    private void addFunds(AppUser appUser, BigDecimal amount) {
        appUser.setEmeraldFunds(appUser.getEmeraldFunds().add(amount));
        userRepository.save(appUser);
    }

    private void adjustUserFunds(AppUser appUser, BigDecimal previous, BigDecimal updated) {
        BigDecimal diff = updated.subtract(previous);
        if (diff.compareTo(BigDecimal.ZERO) > 0) {
            validateSufficientFunds(appUser, diff);
            deductFunds(appUser, diff);
        } else if (diff.compareTo(BigDecimal.ZERO) < 0) {
            addFunds(appUser, diff.abs());
        }
    }

    private Campaign buildCampaign(CampaignDTO dto, Product product) {
        Campaign campaign = new Campaign();
        campaign.setProduct(product);
        updateCampaignFields(campaign, dto);
        return campaign;
    }

    private void updateCampaignFields(Campaign campaign, CampaignDTO dto) {
        campaign.setCampaignName(dto.getCampaignName());
        campaign.setKeywords(dto.getKeywords());
        campaign.setBidAmount(dto.getBidAmount());
        campaign.setCampaignFund(dto.getCampaignFund());
        campaign.setStatus(dto.getStatus());
        campaign.setTown(dto.getTown());
        campaign.setRadius(dto.getRadius());
    }

    private void refundUnusedCampaignFund(Campaign campaign) {
        Product product = campaign.getProduct();
        AppUser appUser = product.getOwner();
        addFunds(appUser, campaign.getCampaignFund());
    }
}


