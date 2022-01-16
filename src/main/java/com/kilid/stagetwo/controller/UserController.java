package com.kilid.stagetwo.controller;

import com.kilid.stagetwo.model.User;
import com.kilid.stagetwo.model.User_Role;
import com.kilid.stagetwo.model.User_Subscription;
import com.kilid.stagetwo.resource.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/userList")
    public ResponseEntity<List<User>> userList(){
        return ResponseEntity.ok(userService.userList()) ;
    }
    @RequestMapping("/user")
    public User userByUserName(@RequestBody String userName){
        return userService.finByUserName(userName);
    }

    @PostMapping("/addUserRole")
    public User_Role addUserRole(@RequestBody User_Role userRole){
        return userService.saveUserRole(userRole);
    }

    @PostMapping("/addUserSubscription")
    public User_Subscription addUserSubscription(@RequestBody User_Subscription userSubscription ){
        return userService.saveUserSubscription(userSubscription);
    }


}
