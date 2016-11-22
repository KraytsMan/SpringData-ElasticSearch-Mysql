package com.kraytsman.api;

import com.kraytsman.entity.User;
import com.kraytsman.repository.elasticsearch.UserElasticRepository;
import com.kraytsman.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository jpaRepository;

    @Autowired
    private UserElasticRepository elasticRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        User saved = jpaRepository.save(user);
        User saved2 = elasticRepository.save(saved);
        return saved;
    }

}
