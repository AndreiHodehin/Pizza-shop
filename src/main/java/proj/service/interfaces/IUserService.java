package proj.service.interfaces;

import org.springframework.cache.annotation.CacheEvict;
import proj.domain.DTO.PizzaPojo;
import proj.domain.DTO.UserPojo;
import proj.domain.User;

import java.util.List;

public interface IUserService {
    void createUser(User user);
    UserPojo findUser(long id);
    List<UserPojo> findAllUser();
    void updateUser(User user);
    void deleteUser(long id);
    UserPojo getUserByUsername(String username) ;
    void fillInfoAboutUser(User user);
    List<PizzaPojo> findAllPizzaOfUserByUsername(String username);

    @CacheEvict(value = "userCache")
    void clearCache();
}
