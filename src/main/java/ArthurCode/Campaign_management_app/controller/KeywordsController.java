package ArthurCode.Campaign_management_app.controller;

import ArthurCode.Campaign_management_app.service.KeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
@CrossOrigin(origins = "http://localhost:5137", allowCredentials = "true")
public class KeywordsController {

    private final KeywordService keywordService;

    public KeywordsController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllKeywords() {
        List<String> keywords = keywordService.getAllKeywords();
        return ResponseEntity.ok(keywords);
    }
}
