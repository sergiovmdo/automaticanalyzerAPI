package com.aaa.automaticanalyzer.api.user.business;

import com.aaa.automaticanalyzer.api.user.rest.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private static final String SERVICENAME = "AAA";

    @Value("${oauthKey}")
    private String tokenKey;

    @Override
    public User createUser(UserRestInput input) {
        User user = UserMapper.createUserFromRestInput(input);
        user.generateAndSetDiseases();
        createAndAssociateToken(user);
        String hashedPassword = hashPassword(input.getPassword());
        if (hashedPassword != null)
            user.setPassword(hashedPassword);
        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> validateToken(String token)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .requireIssuer(SERVICENAME)
                .build()
                .parseClaimsJws(token);

        return getUserByDNI(jws.getBody().getSubject());
    }

    @Override
    public Optional<User> getUserByDNI(String dni) {
        return userRepository.findById(dni);
    }

    /**
     * Creates a user token and associates it with the user passed as parameter
     *
     * @param user The user we are going to add the token
     */
    private void createAndAssociateToken(User user) {
        user.setToken(Jwts.builder().setIssuer(SERVICENAME)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setSubject(user.getDni())
                .setId(UUID.randomUUID().toString())
                .signWith(getSecretKey())
                .compact());
    }

    /**
     * Hashes the password given by the user and stores it in the user object hashed with SHA-512
     *
     * @param password the given password
     */
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return the new secret key generated with tokenkey
     */
    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(tokenKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA384");
    }
}
