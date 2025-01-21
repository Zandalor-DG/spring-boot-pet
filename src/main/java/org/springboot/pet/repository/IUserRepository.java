package org.springboot.pet.repository;

import jakarta.annotation.Nonnull;
import org.springboot.pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT u.id, u.email, u.firstName, u.secondName " +
                    "FROM User u " +
                    "WHERE u.email = :email"
    )
    User findByEmail(String email);

    List<User> id(long id);
}
