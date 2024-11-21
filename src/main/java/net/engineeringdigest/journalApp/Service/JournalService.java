package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.engineeringdigest.journalApp.repository.JournalRepo;

import java.util.List;

@Component
public class JournalService {
    @Autowired
    private JournalRepo journalRepo;
    @Autowired
    private UserService userService;

    public boolean saveJournal(JournalEntity journal , String username){
        try{
            JournalEntity res = journalRepo.save(journal);
            return userService.addJournalInUser(username , res);
        }
        catch (Exception e){
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    public boolean deleteJournal(ObjectId id , String username){
        try{
            journalRepo.deleteById(id);
            return userService.deleteJournalFromUser(username , id);
        }
        catch (Exception e){
            throw new RuntimeException("Internal Server Error", e);
        }
    }

    public List<JournalEntity> getAllEntries(){
        try{
            return journalRepo.findAll();
        }
        catch (Exception e){
            throw new RuntimeException("Internal Server Error", e);
        }
    }
}
