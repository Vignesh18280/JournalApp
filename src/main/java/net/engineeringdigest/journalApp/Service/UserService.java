package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserEntity saveUser(UserEntity user){
        try{
           return userRepo.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }

    public List<UserEntity> findAllUsers(){
        try{
            return userRepo.findAll();
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }

    public UserEntity findUser(String username){
        try{
            return userRepo.findByUsername(username);
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }

    public boolean deleteUser(String  username){
        try{
            userRepo.deleteByUsername(username);
            return true;
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }

    public boolean addJournalInUser(String username, JournalEntity journal){
        try{
            System.out.println(username);
            UserEntity user = userRepo.findByUsername(username);
            user.getJournals().add(journal);
            userRepo.save(user);
            return true;
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }

    public boolean deleteJournalFromUser(String username, ObjectId id){
        try{
            UserEntity user = userRepo.findByUsername(username);
            user.getJournals().removeIf(e -> e.getId().equals(id));
            userRepo.save(user);
            return true;
        }
        catch (Exception e){
            throw new RuntimeException("Internal server Error" , e);
        }
    }
}
