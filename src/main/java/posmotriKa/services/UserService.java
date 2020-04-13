package posmotriKa.services;

import posmotriKa.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
