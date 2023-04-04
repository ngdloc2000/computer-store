package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.entity.Client;
import com.cdtn.computerstore.entity.Role;
import com.cdtn.computerstore.entity.User;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.client.ClientRepository;
import com.cdtn.computerstore.repository.role.RoleRepository;
import com.cdtn.computerstore.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public BaseResponseData registerClient(RegistrationForm registrationForm) {

        if (userRepository.findByUserName(registrationForm.getUsername()).isPresent()) {
            return new BaseResponseData(500, "User đã tồn tại", null);
        }

        User userEntity = User.builder()
                .userName(registrationForm.getUsername())
                .password(passwordEncoder.encode(registrationForm.getPassword()))
                .role("ROLE_CLIENT")
                .build();

        User user = userRepository.save(userEntity);

        Client client = Client.builder()
                .userId(user.getId())
                .fullName(registrationForm.getClientName())
                .phoneNumber(registrationForm.getPhoneNumber())
                .address(registrationForm.getAddress())
                .dob(registrationForm.getDob())
                .build();

        clientRepository.save(client);

        return new BaseResponseData(200, "Success", null);
    }
}
