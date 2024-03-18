package uz.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import uz.redis.model.User;
import uz.redis.service.UserService;

@RestController
@RequestMapping("api")
public class RedisController {
    private final UserService userService;

    @Autowired
    public RedisController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findUser/{id}")
    @Cacheable(value = "User", key = "#id")
    public User getUser(@PathVariable("id") final Long id) {
        return userService.findById(id);
    }

    @PostMapping("/createUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


}
