package com.haphelon.auth.services;

import com.haphelon.auth.entities.User;
import com.haphelon.auth.jpa.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackOn = {PersistenceException.class})
    public ResponseEntity<HashMap<String, Object>> createUser(User user) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new PersistenceException("A user with that email already exists!");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            res.put("message", "User created successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (PersistenceException ex) {
            res.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }catch (Exception ex){
            ex.printStackTrace();
            res.put("message","An error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
