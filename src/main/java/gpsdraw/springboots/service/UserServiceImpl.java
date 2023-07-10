package gpsdraw.springboots.service;

import gpsdraw.springboots.domain.User;
import gpsdraw.springboots.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signup(User user) {
        // 회원 가입 로직을 구현합니다.
        userRepository.save(user);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }
    @Override
    public void updateUser(User user) {
        // 사용자 업데이트 로직을 구현합니다.
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            // 필요한 다른 사용자 정보 업데이트 로직을 추가합니다.

            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(Long id) {
        // 사용자 삭제 로직을 구현합니다.
        userRepository.deleteById(id);
    }



}
