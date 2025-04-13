package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.model.Keyword;
import ArthurCode.Campaign_management_app.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<String> getAllKeywords() {
        return keywordRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Keyword::getName)
                .toList();
    }
}
