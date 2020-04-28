package ru.avn.otus.hw.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.avn.otus.hw.users.entities.User;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select u from User u where u.id = :id")
    Optional<User> fetchByIdWithOptimisticLock(Long id);

}
