package ArthurCode.Campaign_management_app.service;

import ArthurCode.Campaign_management_app.model.Town;
import ArthurCode.Campaign_management_app.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownService {

    private final TownRepository townRepository;

    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public List<String> getAllTowns() {
        return townRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Town::getName)
                .toList();
    }
}

