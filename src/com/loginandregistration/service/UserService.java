package com.loginandregistration.service;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User getUserDeatils(String emailId,String password) {
        User user = userDAO.validateUser(emailId,password);
        return user;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean addUser(String userName, String emailId, String password) {
        if (userDAO.isEmailIdExist(emailId)==0)
            return userDAO.addUserToDataBase(userName,emailId,password);
        return false;
    }

}
