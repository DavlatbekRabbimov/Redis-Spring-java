package uz.redis.service;

import uz.redis.model.User;

public interface UserService {

    User saveUser(User user);
    User findById(long id);

}
