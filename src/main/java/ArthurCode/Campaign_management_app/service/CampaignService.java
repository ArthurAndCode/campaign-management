package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.dto.request.CampaignRequest;
import ArthurCode.Campaign_management_app.dto.response.CampaignResponse;
import ArthurCode.Campaign_management_app.exception.*;
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
    public CampaignResponse create(CampaignRequest request) {
        Product product = getProductOrThrow(request.getProductId());
        Owner owner = product.getOwner();

        validateOwnership(product.getOwner().getId(), owner.getId());
        validateSufficientFunds(owner, request.getCampaignFund());
        deductFunds(owner, request.getCampaignFund());

        Campaign campaign = buildCampaign(request, product);
        campaignRepository.save(campaign);
        return campaignMapper.toResponse(campaign);
    }

    @Transactional
    public CampaignResponse update(Long id, CampaignRequest request) {
        Campaign campaign = getCampaignById(id);
        Product product = campaign.getProduct();
        Owner owner = product.getOwner();

        validateOwnership(product.getOwner().getId(), owner.getId());
        adjustOwnerFunds(owner, campaign.getCampaignFund(), request.getCampaignFund());

        updateCampaignFields(campaign, request);
        campaignRepository.save(campaign);
        return campaignMapper.toResponse(campaign);
    }

    @Transactional
    public void delete(Long campaignId, Long ownerId) {
        Campaign campaign = getCampaignById(campaignId);
        Product product = campaign.getProduct();
        validateOwnership(product.getOwner().getId(), ownerId);
        refundUnusedCampaignFund(campaign);
        campaignRepository.deleteById(campaignId);
    }


    //helper methods
    private static void validateOwnership(Long productOwnerId, Long ownerId) {
        if (!ownerId.equals(productOwnerId)) {
            throw new UnauthorizedAccessException("You are not the owner of this product");
        }
    }

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


