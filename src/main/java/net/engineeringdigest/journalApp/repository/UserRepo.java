package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity , ObjectId> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
