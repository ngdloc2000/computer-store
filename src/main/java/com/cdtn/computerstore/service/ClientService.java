package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.auth.request.RegistrationForm;
import com.cdtn.computerstore.dto.client.response.ClientDetailResponse;
import com.cdtn.computerstore.entity.Client;
import com.cdtn.computerstore.entity.User;
import com.cdtn.computerstore.exception.StoreException;
import com.cdtn.computerstore.repository.client.ClientRepository;
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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerClient(RegistrationForm registrationForm) {

        if (userRepository.findByUserName(registrationForm.getUsername()).isPresent()) {
            throw new StoreException("User đã tồn tại");
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
    }

    public ClientDetailResponse findByUserId(Long userId) {

        Client client = clientRepository.findByUserId(userId)
                .orElseThrow(() -> new StoreException("Client not found by id: " + userId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new StoreException("Client not found by id: " + userId));

        ClientDetailResponse response = ClientDetailResponse.builder()
                .userId(client.getUserId())
                .email(user.getUsername())
                .fullName(client.getFullName())
                .phoneNumber(client.getPhoneNumber())
                .address(client.getAddress())
                .dob(client.getDob())
                .build();

        return response;
    }
}
