package uz.wh.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.entities.User;
import uz.wh.security.jwt.JwtUserFactory;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    public JwtUserDetailsService(@Lazy UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Fodalanuvchi topilmadi.");
        }

        return JwtUserFactory.create(user);
    }
}
