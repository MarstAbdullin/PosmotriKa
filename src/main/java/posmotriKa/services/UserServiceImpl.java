package posmotriKa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import posmotriKa.dto.UserDto;
import posmotriKa.repositories.UserRepository;
import posmotriKa.scope.TenantScoped;

import java.util.List;

@TenantScoped
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
