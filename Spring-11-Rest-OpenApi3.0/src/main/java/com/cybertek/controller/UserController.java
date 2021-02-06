package com.cybertek.controller;

import com.cybertek.entity.User;
import com.cybertek.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@Tag(name = "User",description = "User API")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/read-all")
    @Operation(summary = "Read all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved"
                    ,content = {@Content(mediaType = "application/json",schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400",description = "Something went wrong",content = @Content),
            @ApiResponse(responseCode = "404",description = "User not found",content = @Content)
    })
    public List<User> readALL(){
        return userRepository.findAll();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));

        foundUser.setUsername(user.getUsername());
        foundUser.setAccount(user.getAccount());
        foundUser.setUsername(user.getUsername());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());

        return userRepository.save(foundUser);
    }

    @DeleteMapping("/delete/{id}")
    public List<User> deleteUser(@PathVariable("id") Long id){

        userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("This user does not exist"));

        userRepository.deleteById(id);

        return userRepository.findAll();
    }

}
