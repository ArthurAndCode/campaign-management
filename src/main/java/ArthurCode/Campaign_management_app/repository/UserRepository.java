package ArthurCode.Campaign_management_app.repository;

import ArthurCode.Campaign_management_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
