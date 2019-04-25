package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.exception.DemoBusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean logInIsSuccess(String username, String password, int roleId) throws DemoBusinessException {
        User user = userMapper.findUserByUserName(username);
        if(Objects.isNull(user)){
            throw new DemoBusinessException("用户不存在");
        }
        if(user.getPassword().equals(password)&&user.getRoleId() == roleId){
            return true;
        }
        return false;
    }
}
