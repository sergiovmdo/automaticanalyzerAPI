package com.aaa.automaticanalyzer.api.user.business;

import com.aaa.automaticanalyzer.api.user.domain.PasswordRestInput;
import com.aaa.automaticanalyzer.api.user.rest.mapping.UserMapper;
import com.aaa.automaticanalyzer.model.*;
import com.aaa.automaticanalyzer.api.user.domain.UserRestInput;
import com.aaa.automaticanalyzer.processingengine.HyperCholersterolemiaEngine;
import com.aaa.automaticanalyzer.processingengine.HypothyroidismEngine;
import com.aaa.automaticanalyzer.repository.MedicationRepository;
import com.aaa.automaticanalyzer.repository.MedicineRepository;
import com.aaa.automaticanalyzer.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;
    private final MedicationRepository medicationRepository;
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
        user.setMedications(getUserMedication(user));
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
    public Optional<SimplifiedUser> getSimplifiedUserByDNI(String dni) {
        User user = userRepository.findById(dni).get();
        if (user != null){
            return Optional.of(SimplifiedUser.fromUser(user));
        }

        return null;
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

    @Override
    public ResponseEntity<Void> changePassword(PasswordRestInput password, String dni) {
        Optional<User> user = userRepository.findById(dni);
        if (user.isPresent()) {
            //TODO: Treat all the API errors
            final User u = user.get();
            if (hashPassword(password.getCurrentPassword()).equals(u.getPassword())) {
                String hashedPassword = hashPassword(password.getPassword());
                u.setPassword(hashedPassword);
                userRepository.save(u);
                return ResponseEntity.ok(null);
            } else {
                //TODO: La contraseña no es correcta
            }
        }


        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> setFCMToken(FCMToken token, final User user) {
        user.setFirebaseToken(token.getToken());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Medication> getUserMedication(User user) {
        ArrayList<Medication> medications = new ArrayList<>();
        for (Disease disease : user.getDiseases()) {
            Medication medication = null;
            if (disease.equals(Disease.HYPOTHYROIDISM)) {
                medication = getHypothyroidismMedication();

                medications.add(medication);
            } else if (disease.equals(Disease.HYPERCHOLESTEROLEMIA)) {
                medication = getHypercholesterolemiaMedication();
                medications.add(medication);
            }

        }

        return medications;
    }

    /* These methods are only used for testing purposes, */
    @Override
    public Medication getHypothyroidismMedication() {
        Medication medication = new Medication();
        medication.setDisease(Disease.HYPOTHYROIDISM);

        Medicine medicine = new Medicine();
        medicine.setName(HypothyroidismEngine.EUTIROX);

        Random random = new Random();
        int dose = random.nextInt(HypothyroidismEngine.doses.length);
        medicine.setDose(Double.valueOf(HypothyroidismEngine.doses[dose]));
        medication.setMedicines(Arrays.asList(medicine));
        medicineRepository.save(medicine);
        medicationRepository.save(medication);

        return medication;
    }

    @Override
    public Medication getHypercholesterolemiaMedication() {
        Medication medication = new Medication();
        medication.setDisease(Disease.HYPERCHOLESTEROLEMIA);

        Medicine medicine = new Medicine();

        Random random = new Random();
        medicine.setName(HyperCholersterolemiaEngine.HyperCholersterolemiaMedicines.values()[random.nextInt(HyperCholersterolemiaEngine.HyperCholersterolemiaMedicines.values().length)].getName());

        switch (medicine.getName()){
            case "Pravastatina":
                switch (random.nextInt(2)){
                    case 0:
                        medicine.setDose(20d);
                        break;
                    case 1:
                        medicine.setDose(40d);
                        break;
                }
                break;
            case "Lovastatina":
                switch (random.nextInt(2)){
                    case 0:
                        medicine.setDose(20d);
                        break;
                    case 1:
                        medicine.setDose(40d);
                        break;
                }
                break;
            case "Simvastatina":
                switch (random.nextInt(3)){
                    case 0:
                        medicine.setDose(10d);
                        break;
                    case 1:
                        medicine.setDose(20d);
                        break;
                    case 2:
                        medicine.setDose(40d);
                }
                break;
            case "Atrovastatina":
                switch (random.nextInt(2)){
                    case 0:
                        medicine.setDose(40d);
                        break;
                    case 1:
                        medicine.setDose(80d);
                        break;
                }
                break;
        }

        medication.setMedicines(Arrays.asList(medicine));
        medicineRepository.save(medicine);
        medicationRepository.save(medication);

        return medication;
    }

    /**
     * @return the new secret key generated with tokenkey
     */
    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(tokenKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HMACSHA384");
    }
}
