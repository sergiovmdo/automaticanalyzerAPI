package com.aaa.automaticanalyzer.api.user.business;

import com.aaa.automaticanalyzer.api.user.rest.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private static final String ISSUER = "AAA";

    @Value("${oauthKey}")
    private String tokenKey;

    @Override
    public User createUser(UserRestInput input) {
        User user = UserMapper.createUserFromRestInput(input);
        user.generateAndSetDiseases();

        userRepository.save(user);

        return user;
    }

    /**
     * Creates a user token and associates it with the user passed as parameter
     *
     * @param user The user we are going to add the token
     */
    private void createAndAssociateToken(User user){
        user.setToken(Jwts.builder().setIssuer(ISSUER)
        .setIssuedAt(Calendar.getInstance().getTime())
        .setSubject(user.getDni())
        .setId(UUID.randomUUID().toString())
        .signWith(getSecretKey())
        .compact());
    }

    /**
     *
     * @return the new secret key generated with tokenkey
     */
    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(tokenKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA384");
    }
}
