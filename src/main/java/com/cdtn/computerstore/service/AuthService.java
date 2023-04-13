package com.cdtn.computerstore.service;

import com.cdtn.computerstore.dto.auth.request.AuthenticationRequest;
import com.cdtn.computerstore.dto.auth.response.AuthenticationResponse;
import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.entity.User;
import com.cdtn.computerstore.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public BaseResponseData authenticate(AuthenticationRequest request) {

        Optional<User> user = userRepository.findByUserName(request.getUsername());

        if (user.isPresent()) {
            boolean checkPassword = passwordEncoder.matches(request.getPassword(), user.get().getPassword());
            if (!checkPassword) {
                return new BaseResponseData(500, "Mật khẩu nhập không chính xác", null);
            }

            String role = userRepository.findUserRoleByUsername(user.get().getUsername());

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
            user.get().setRole(role);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            String jwtToken = jwtService.createToken(authentication);
            String jwtRefreshToken = jwtService.refreshToken(authentication);

            AuthenticationResponse response = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .refreshToken(jwtRefreshToken)
                    .userId(user.get().getId())
                    .build();

            return new BaseResponseData(200, "Success", response);
        } else {
            return new BaseResponseData(500, "Tài khoản nhập không chính xác", null);
        }
    }
}
