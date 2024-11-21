package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        try{
            UserEntity user = userService.findUser(username);
            return new ResponseEntity<>(user , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" + e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UserEntity> users =  userService.findAllUsers();
            return new ResponseEntity<>(users , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" + e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user){
        try{
            UserEntity response = userService.saveUser(user);
            if(response == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(response , HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" + e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        try{
            userService.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" + e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
