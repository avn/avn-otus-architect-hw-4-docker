package ru.avn.otus.hw.users.profiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.avn.otus.hw.users.profiles.entities.UserProfile;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select u from UserProfile u where u.id = :id and u.isDeleted = FALSE")
    Optional<UserProfile> fetchByIdWithOptimisticLock(Long id);

    Optional<UserProfile> findByIdAndIsDeletedFalse(Long id);
}
