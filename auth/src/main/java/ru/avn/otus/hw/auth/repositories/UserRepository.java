package ru.avn.otus.hw.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avn.otus.hw.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
