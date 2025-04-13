package ArthurCode.Campaign_management_app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/towns")
@CrossOrigin(origins = "http://localhost:5137", allowCredentials = "true")
public class TownController {
}
