package posmotriKa.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posmotriKa.dto.LoginDto;
import posmotriKa.models.User;
import posmotriKa.repositories.UserRepository;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

}
