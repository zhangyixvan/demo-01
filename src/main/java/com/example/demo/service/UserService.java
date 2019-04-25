package com.example.demo.service;

import com.example.demo.exception.DemoBusinessException;
import org.springframework.stereotype.Service;

public interface UserService {

    boolean logInIsSuccess(String username,String password,int roleId) throws DemoBusinessException;

}
