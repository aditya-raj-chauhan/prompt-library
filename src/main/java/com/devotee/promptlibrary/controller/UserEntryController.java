package com.devotee.promptlibrary.controller;

import com.devotee.promptlibrary.dto.UserRequest;
import com.devotee.promptlibrary.dto.UserResponse;
import com.devotee.promptlibrary.model.User;
import com.devotee.promptlibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
public class UserEntryController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?>registerUser(@RequestBody UserRequest userRequest){
        log.info("inside the UserService -register ()");
       UserResponse response= userService.register(userRequest);
       return ResponseEntity.ok(response);


    }

    @GetMapping("/all-users")
    public ResponseEntity<?>getAllUsers(){
        List<UserResponse>users=userService.getAllUsers();
        return ResponseEntity.ok(users);

    }

    @GetMapping("user/{email}")
    public ResponseEntity<?>getUserByEmail(@PathVariable String email){

        User user=userService.getUserByEmail(email);
        return ResponseEntity.ok(user);


    }
}
