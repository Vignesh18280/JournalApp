package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalService journalService;

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntity> postJournalController(@RequestBody JournalEntity journal , @PathVariable String username){
        try{
            journal.setDate(LocalDateTime.now());
            boolean value =  journalService.saveJournal(journal , username);
            if(value) return new ResponseEntity<>(journal , HttpStatus.CREATED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delet/{jId}/{username}")
    public ResponseEntity<?> JournalDeleteController(@PathVariable ObjectId jId , @PathVariable String username){
        try{
            boolean value = journalService.deleteJournal(jId , username);
            if(value) return new ResponseEntity<>(true , HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntity>> getALlJournalController(){
        try{
            List<JournalEntity> data = journalService.getAllEntries();
            return new ResponseEntity<>(data , HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
