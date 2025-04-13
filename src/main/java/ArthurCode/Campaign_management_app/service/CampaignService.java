package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.dto.request.CampaignRequest;
import ArthurCode.Campaign_management_app.dto.response.CampaignResponse;
import ArthurCode.Campaign_management_app.exception.CampaignNotFoundException;
import ArthurCode.Campaign_management_app.exception.FundsException;
import ArthurCode.Campaign_management_app.exception.OwnerNotFoundException;
import ArthurCode.Campaign_management_app.exception.ProductNotFoundException;
import ArthurCode.Campaign_management_app.mapper.CampaignMapper;
import ArthurCode.Campaign_management_app.model.Owner;
import ArthurCode.Campaign_management_app.model.Campaign;
import ArthurCode.Campaign_management_app.model.Product;
import ArthurCode.Campaign_management_app.repository.OwnerRepository;
import ArthurCode.Campaign_management_app.repository.CampaignRepository;
import ArthurCode.Campaign_management_app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;
    private final OwnerRepository ownerRepository;
    private final CampaignMapper campaignMapper;

    public CampaignService(CampaignRepository campaignRepository, ProductRepository productRepository, OwnerRepository ownerRepository, CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
        this.campaignMapper = campaignMapper;
    }

    public Page<Campaign> getAll(int page, int size) {
        return campaignRepository.findAll(PageRequest.of(page, size));
    }

    public Page<CampaignResponse> getByOwnerId(Long ownerId, int page, int size) {
        Owner owner = getOwnerOrThrow(ownerId);
        Page<Campaign> campaigns = campaignRepository.findAllByOwner(owner, PageRequest.of(page, size));
        return campaigns.map(campaignMapper::toResponse);
    }

    public CampaignResponse getCampaignResponseById(Long id) {
        return campaignMapper.toResponse(getCampaignById(id));
    }

    @Transactional
    public CampaignResponse create(CampaignRequest dto) {
        Product product = getProductOrThrow(dto.getProductId());
        Owner owner = product.getOwner();

        validateSufficientFunds(owner, dto.getCampaignFund());
        deductFunds(owner, dto.getCampaignFund());

        Campaign campaign = buildCampaign(dto, product);
        campaignRepository.save(campaign);
        return campaignMapper.toResponse(campaign);
    }

    @Transactional
    public CampaignResponse update(Long id, CampaignRequest dto) {
        Campaign campaign = getCampaignById(id);
        Product product = campaign.getProduct();
        Owner owner = product.getOwner();

        adjustOwnerFunds(owner, campaign.getCampaignFund(), dto.getCampaignFund());

        updateCampaignFields(campaign, dto);
        campaignRepository.save(campaign);
        return campaignMapper.toResponse(campaign);
    }

    @Transactional
    public void delete(Long id) {
        Campaign campaign = getCampaignById(id);
        refundUnusedCampaignFund(campaign);
        campaignRepository.deleteById(id);
    }


    //helper methods
    private Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new CampaignNotFoundException(id));
    }

    private Owner getOwnerOrThrow(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(ownerId));
    }

    private Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    private void validateSufficientFunds(Owner owner, BigDecimal amount) {
        if (owner.getEmeraldFunds().compareTo(amount) < 0) {
            throw new FundsException("Not enough emerald funds");
        }
    }

    private void deductFunds(Owner owner, BigDecimal amount) {
        owner.setEmeraldFunds(owner.getEmeraldFunds().subtract(amount));
        ownerRepository.save(owner);
    }

    private void addFunds(Owner owner, BigDecimal amount) {
        owner.setEmeraldFunds(owner.getEmeraldFunds().add(amount));
        ownerRepository.save(owner);
    }

    private void adjustOwnerFunds(Owner owner, BigDecimal previous, BigDecimal updated) {
        BigDecimal diff = updated.subtract(previous);
        if (diff.compareTo(BigDecimal.ZERO) > 0) {
            validateSufficientFunds(owner, diff);
            deductFunds(owner, diff);
        } else if (diff.compareTo(BigDecimal.ZERO) < 0) {
            addFunds(owner, diff.abs());
        }
    }

    private Campaign buildCampaign(CampaignRequest dto, Product product) {
        Campaign campaign = new Campaign();
        campaign.setProduct(product);
        updateCampaignFields(campaign, dto);
        return campaign;
    }

    private void updateCampaignFields(Campaign campaign, CampaignRequest dto) {
        campaign.setCampaignName(dto.getCampaignName());
        campaign.setKeywords(dto.getKeywords());
        campaign.setBidAmount(dto.getBidAmount());
        campaign.setCampaignFund(dto.getCampaignFund());
        campaign.setStatus(dto.getStatus());
        campaign.setTown(dto.getTown());
        campaign.setRadius(dto.getRadius());
        campaign.setOwner(campaign.getProduct().getOwner());
    }

    private void refundUnusedCampaignFund(Campaign campaign) {
        Product product = campaign.getProduct();
        Owner owner = product.getOwner();
        addFunds(owner, campaign.getCampaignFund());
    }
}


