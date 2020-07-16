package com.springcloud.user.service;

import com.springcloud.user.dao.UserDao;
import com.springcloud.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Long id){
        return userDao.findById(id);
    }
}
