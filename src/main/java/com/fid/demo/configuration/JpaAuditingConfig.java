package com.fid.demo.configuration;

import com.fid.demo.service.IUserService;
import com.fid.demo.service.dto.SimpleUser;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<Integer> auditorProvider() {
        return new Auditor();
    }

    private class Auditor implements AuditorAware<Integer> {

        @NotNull
        @Override
        public Optional<Integer> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication.getName() != null) {
                Integer id;
                try {
                    SimpleUser simpleUser = (SimpleUser) authentication.getPrincipal();
                    id = simpleUser.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                    id = -1;
                }

                return Optional.of(id);
            }

            return Optional.of(0);
        }
    }
}
