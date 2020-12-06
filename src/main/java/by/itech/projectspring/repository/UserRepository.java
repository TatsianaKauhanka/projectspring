package by.itech.projectspring.repository;

import by.itech.projectspring.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
