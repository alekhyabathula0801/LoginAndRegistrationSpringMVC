package com.loginandregistration.service;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User getUserDeatils(String emailId,String password) {
        User user = userDAO.validateUser(emailId,password);
        return user;
    }

    public boolean addUser(String userName, String emailId, String password) {
        if (userDAO.isEmailIdExist(emailId)==0)
            return userDAO.addUserToDataBase(userName,emailId,password);
        return false;
    }

}
