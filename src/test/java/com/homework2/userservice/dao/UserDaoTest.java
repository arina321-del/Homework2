package com.homework2.userservice.dao;

import com.homework2.userservice.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class UserDaoTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(
                    DockerImageName.parse("homework2-postgres")
                            .asCompatibleSubstituteFor("postgres"))
                    .withDatabaseName("Homework2")
                    .withUsername("postgres")
                    .withPassword("password");

    @Test
    void saveAndFindAll_shouldWork() {

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.addAnnotatedClass(User.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            UserDaoImpl userDao = new UserDaoImpl(sessionFactory);

            User user = new User();
            user.setName("John");
            user.setEmail("john@test.com");
            user.setAge(30);
            user.setCreatedAt(LocalDateTime.now());

            userDao.save(user);

            List<User> users = userDao.findAll();
            assertEquals(1, users.size());
        }
    }
}