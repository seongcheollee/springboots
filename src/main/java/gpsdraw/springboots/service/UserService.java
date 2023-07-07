package gpsdraw.springboots.service;

import gpsdraw.springboots.domain.User;

public interface UserService {
    void signup(User user);

    User getUserById(Long id);
}
