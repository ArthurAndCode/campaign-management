package ArthurCode.Campaign_management_app.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Long id) {
        super("Owner with id " + id + " not found");
    }
}
