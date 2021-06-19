package com.fid.demo.service.impl;

import com.fid.demo.aspect.annotation.CreatedByFilter;
import com.fid.demo.controller.request.AddUserRequest;
import com.fid.demo.entity.Authority;
import com.fid.demo.entity.User;
import com.fid.demo.entity.Verification;
import com.fid.demo.exception.StockManagementException;
import com.fid.demo.repository.UserRepository;
import com.fid.demo.service.IUserService;
import com.fid.demo.service.IVerificationService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final JavaMailSender javaMailSender;
    private final IVerificationService verificationService;
    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    @Override
    @CreatedByFilter("User")
    public List<User> findAllByCreatedBy() {
        SimpleUser simpleUser = (SimpleUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findAllByCreatedBy(simpleUser.getId());
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void validateExistUser(AddUserRequest request) {
        List<User> userList = userRepository.findAllByUsername(request.getUsername());
        if (!userList.isEmpty()) {
            throw new StockManagementException("DUPLICATE_USER");
        }
    }

    @Override
    @Transactional
    public void signUp(AddUserRequest request) {
        validateExistUser(request);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setEnabled(true);
        save(user);

        Authority authority = new Authority();
        authority.setUsername(request.getUsername());
        authority.setAuthority("ROLE_USER");
        authorityService.save(authority);

        Verification verification = new Verification();
        verification.setUsername(request.getUsername());
        verification.setExpiredDateTime(Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)));
        verification.setVerificationCode(generateVerificationCode());
        verificationService.save(verification);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("abc@gmail.com");
        mailMessage.setTo(user.getUsername());
        mailMessage.setSubject("Hoşgeldin");
        mailMessage.setText("Doprulama kodu " + verification.getVerificationCode());

        javaMailSender.send(mailMessage);
    }

    private String generateVerificationCode() {
        Random r = new Random();
        int n1 = r.nextInt(10);
        int n2 = r.nextInt(10);
        int n3 = r.nextInt(10);
        int n4 = r.nextInt(10);
        int n5 = r.nextInt(10);
        int n6 = r.nextInt(10);
        return String.valueOf(n1) + n2 + n3 + n4 + n5 + n6;
    }

    @Override
    public Integer inquireUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
