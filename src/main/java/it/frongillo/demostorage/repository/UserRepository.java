package it.frongillo.demostorage.repository;

import it.frongillo.demostorage.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
