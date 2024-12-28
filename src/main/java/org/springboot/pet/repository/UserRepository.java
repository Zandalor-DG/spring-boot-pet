package org.springboot.pet.repository;

import jakarta.annotation.Nonnull;
import org.springboot.pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT u.id, u.email, u.first_name, u.second_name " +
            "FROM User u " +
            "WHERE u.id = :userId"
    )
    @Nonnull
    Optional<User> findById(@Nonnull Long userId);

    @Query(value =
            "SELECT u.id, u.email, u.first_name, u.second_name " +
                    "FROM User u " +
                    "WHERE u.email = :email"
    )
    @Nonnull
    Optional<User> findByEmail(String email);
}
