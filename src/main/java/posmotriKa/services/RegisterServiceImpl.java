package posmotriKa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posmotriKa.dto.RegisterDto;
import posmotriKa.models.User;
import posmotriKa.models.UserInfo;
import posmotriKa.repositories.UserInfoRepository;
import posmotriKa.repositories.UserRepository;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterDto form) {
        User user = User.builder()
                .email(form.getEmail())
                .hash(passwordEncoder.encode(form.getPassword()))
                .role("USER")
                .build();

        UserInfo userInfo = UserInfo.builder()
                .user(user)
                .username(form.getUsername())
                .build();
        userInfoRepository.save(userInfo);
    }
}

