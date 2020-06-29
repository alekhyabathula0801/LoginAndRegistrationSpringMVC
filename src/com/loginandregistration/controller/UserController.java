package com.loginandregistration.controller;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login.jsp");
    }

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String loginProcess(@ModelAttribute("user") User user, Model model) {
        User users = userDAO.validateUser(user.getEmailId(),user.getPassword());
        if(users!=null) {
            user.setUserName(users.getUserName());
            model.addAttribute("userName",user.getUserName());
            return "welcome.jsp";
        } else {
            model.addAttribute("message","Data Not found");
            return "login.jsp";
        }
    }

    @RequestMapping(value = "/Logout",method = RequestMethod.POST)
    public ModelAndView logoutProcess(SessionStatus sessionStatus, Model model) {
        model.addAttribute("user",null);
        sessionStatus.setComplete();
        return new ModelAndView("login.jsp","message","Logout Successful");
    }

    @RequestMapping(value = "/Registration",method = RequestMethod.POST)
    public String registrationProcess(@ModelAttribute("user") User user, Model model) {
        if (userDAO.isEmailIdExist(user.getEmailId())==0){
            userDAO.addUserToDataBase(user.getUserName(),user.getEmailId(),user.getPassword());
            model.addAttribute("message","Registration Successfull...Login to continue");
            return "login.jsp";
        } else {
            model.addAttribute("message","Email Id exists");
            return "register.jsp";
        }
    }

}
