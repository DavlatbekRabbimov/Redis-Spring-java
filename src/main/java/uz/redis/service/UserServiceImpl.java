package uz.redis.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import uz.redis.model.User;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private static final String USER = "USER";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, User> hashOperations;

    @Autowired
    public UserServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public User saveUser(User user) {
        hashOperations.put(USER, user.getId(), user);
        return user;
    }

    @Override
    public User findById(long id) {
        return hashOperations.get(USER, id);
    }
}
