package com.mymoney.portfolio.service;

import com.mymoney.portfolio.models.User;
import com.mymoney.portfolio.repository.UserRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    static Logger logger = Logger.getLogger(UserService.class.getName());

    public static String createUser(String userName){
        User user = new User(userName);
        UserRepository.userMap.putIfAbsent(user.getId(), user);
        return user.getId();
    }

    public static User getUser(String userId){
        try{
            return  UserRepository.userMap.getOrDefault(userId, null);
        }catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
}
