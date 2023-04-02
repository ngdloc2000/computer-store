package com.cdtn.computerstore.service;

import com.cdtn.computerstore.entity.Role;
import com.cdtn.computerstore.entity.User;
import com.cdtn.computerstore.repository.role.RoleRepository;
import com.cdtn.computerstore.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void addRoleToUser(String userName, String roleName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow();
        Role role = roleRepository.findByName(roleName);
        user.setRole(role.getName());
    }
}
