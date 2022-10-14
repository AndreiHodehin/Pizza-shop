package proj.repository;

import org.springframework.data.repository.CrudRepository;
import proj.domain.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

}
