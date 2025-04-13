package ArthurCode.Campaign_management_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private Map<String, String> details;
}
