package com.thanglong.chonlichthilai.taikhoan;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class UserController {
 
    // Annotation
    @Autowired private UserService userService;
 
    // Save operation
    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User User)  {
        return userService.saveUser(User);
    }
 
    // Read operation
    @GetMapping("/users")
    public List<User> fetchUserList()  {
        return userService.fetchUserList();
    }
    @GetMapping("/users/{id}")
    public User getUserId(@PathVariable("id") Long userId)  {
        return userService.findUserById(userId);
    }
    // Update operation
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        return userService.updateUser(user, userId);
    }
 
    // Delete operation
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId)  {
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }
}
