package ArthurCode.Campaign_management_app.exception;

public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException(Long id) {
        super("Campaign with id " + id + " not found");
    }
}

