package com.loginandregistration.controller;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@SessionAttributes("users")
public class UserController {

    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login.jsp");
    }

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String loginProcess(@ModelAttribute("user") User user, Model model) {
        User users = userDAO.validateUser(user.getEmailId(),user.getPassword());
        if(users!=null) {
            model.addAttribute("users",users);
            return "welcome.jsp";
        } else {
            model.addAttribute("message","Data Not found");
            return "login.jsp";
        }
    }

    @RequestMapping(value = "/Logout",method = RequestMethod.POST)
    public void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("users");
        session.invalidate();
        request.getSession().setAttribute("message","Logout Succussfull");
        response.sendRedirect("login.jsp");
    }

    @RequestMapping(value = "/Registration",method = RequestMethod.POST)
    public String registrationProcess(@ModelAttribute("user") User user, Model model) throws IOException {
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
