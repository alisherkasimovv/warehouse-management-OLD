package uz.wh.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import uz.wh.db.dao.interfaces.UserDAO;
import uz.wh.db.dto.LoginCredentialsDto;
import uz.wh.db.dto.RoleAssignmentDTO;
import uz.wh.db.entities.User;
import uz.wh.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDAO userDAO;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDAO userDAO) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDAO = userDAO;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginCredentialsDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userDAO.getByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("roles", user.getRoles());


            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userDAO.register(user), HttpStatus.OK);
    }

    @GetMapping("/current-auth-info")
    public ResponseEntity getInfoAboutAuthenticatedUser(@RequestHeader (name="Authorization") String token) {
        return ResponseEntity.ok(jwtTokenProvider.getAuthentication(token.substring(7)));
    }

    @PostMapping("/update-roles")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_DIRECTOR')")
    public ResponseEntity<User> updateRolesForUser(@RequestBody RoleAssignmentDTO dto) {
        return new ResponseEntity<>(userDAO.assignRolesForUserById(dto.getUserId(), dto.getRole()), HttpStatus.OK);
    }

}
