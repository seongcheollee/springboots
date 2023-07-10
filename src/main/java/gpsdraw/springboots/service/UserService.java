package gpsdraw.springboots.service;

import gpsdraw.springboots.domain.User;

import java.util.List;

public interface UserService {
    void signup(User user);
    User getUserById(Long id);
    List<User> findAll();
    void updateUser(User user);
    void deleteUser(Long id);
}
