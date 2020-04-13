package posmotriKa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import posmotriKa.models.User;
import posmotriKa.repositories.UserRepository;

import java.util.Optional;


@Service(value = "myUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(email);
        if (user.isPresent()) {
            User user1 = user.get();
            return new UserDetailsImpl(user1);
        }
        throw new UsernameNotFoundException("User not found");
    }
}
