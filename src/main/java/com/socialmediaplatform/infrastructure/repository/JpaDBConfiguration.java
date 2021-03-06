package com.socialmediaplatform.infrastructure.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {JpaPostRepositoryImpl.class, JpaUserRepositoryImpl.class}, considerNestedRepositories = true)
@EntityScan(basePackageClasses = {JpaPostRepositoryImpl.class, JpaUserRepositoryImpl.class})
public class JpaDBConfiguration {

    @Bean
    JpaPostRepositoryImpl postRepository(JpaPostRepositoryImpl.JpaPostRepo jpaPostRepo) {
        return new JpaPostRepositoryImpl(jpaPostRepo);
    }

    @Bean
    JpaUserRepositoryImpl userRepository(JpaUserRepositoryImpl.JpaUserRepo jpaUserRepo) {
        return new JpaUserRepositoryImpl(jpaUserRepo);
    }
}
