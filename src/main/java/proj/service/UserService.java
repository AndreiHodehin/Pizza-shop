package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.PizzaPojo;
import proj.domain.DTO.UserPojo;
import proj.domain.Order;
import proj.domain.User;
import proj.exceptions.EmptyDataException;
import proj.repository.UserRepository;
import proj.service.interfaces.IUserService;
import proj.utils.Convertor;

import java.util.*;


@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final Convertor convertor;
    @Autowired
    public UserService(UserRepository userRepository, Convertor convertor) {
        this.userRepository = userRepository;
        this.convertor = convertor;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserPojo findUser(long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
        return convertor.userToPojo(user.get());}
        else {
            throw new EmptyDataException("user doesnt exist");
        }
    }

    @Override
    @Transactional
    public List<UserPojo> findAllUser() {
        List<UserPojo> userPojos = new ArrayList<>();
        userRepository.findAll().forEach(u->userPojos.add(convertor.userToPojo(u)));
        return userPojos;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if(optional.isPresent()) {
            User target = optional.get();
            target.setUsername(user.getUsername());
            target.setEmail(user.getEmail());
            target.setPassword(user.getPassword());
            target.setSurname(user.getSurname());
            target.setPhoneNumber(user.getPhoneNumber());
            target.setRole(user.getRole());
            userRepository.save(target);
        } else {
            throw new EmptyDataException("no such element");
        }
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserPojo getUserByUsername (String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()) {
            return convertor.userToPojo(user.get());
        } else { return null;}
    }


    @Override
    @Transactional
    public void fillInfoAboutUser(User user) {
        Optional<User> optional = userRepository.findUserByUsername(user.getUsername());
        if(optional.isPresent()) {
            User target = optional.get();
            target.setEmail(user.getEmail());
            target.setSurname(user.getSurname());
            target.setPhoneNumber(user.getPhoneNumber());
            target.setAddress(user.getAddress());
            userRepository.save(target);
        } else {
            throw new EmptyDataException("no such element");
        }
    }

    @Override
    @Transactional
    @Cacheable(value = "userCache")
    public List<PizzaPojo> findAllPizzaOfUserByUsername(String username) {
        Optional<User> optional = userRepository.findUserByUsername(username);
        if(optional.isPresent()){
            List<PizzaPojo> list = new ArrayList<>();
            List<Order> orders = optional.get().getOrders();
            for (Order o:orders) {
                o.getOrderedPizza().forEach(p->list.add(convertor.pizzaToPojo(p)));
            }
            return list;
        } else {
            throw new EmptyDataException("no user in database");
        }
    }
    @Override
    @CacheEvict(value = "userCache")
    public void clearCache() {
        System.out.println("user cache cleared");
    }
}
