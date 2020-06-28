package com.loginandregistration.controller;

import com.loginandregistration.dao.UserDAO;
import com.loginandregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
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
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
        User user = userDAO.validateUser(emailId,password);
        HttpSession session = request.getSession();
        if(user!=null) {
            session.setAttribute("emailId",emailId);
            session.setAttribute("userName",user.getUserName());
            response.sendRedirect("welcome.jsp");
        } else {
            session.setAttribute("message","Data Not Found... Click on Registration");
            response.sendRedirect("login.jsp");
        }
    }

    @RequestMapping(value = "/Logout",method = RequestMethod.POST)
    public void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("emailId");
        session.invalidate();
        request.getSession().setAttribute("message","Logout Succussfull");
        response.sendRedirect("login.jsp");
    }

    @RequestMapping(value = "/Registration",method = RequestMethod.POST)
    public void registrationProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (userDAO.isEmailIdExist(emailId)==0){
            userDAO.addUserToDataBase(userName,emailId,password);
            session.setAttribute("message","Registration Successfull...Login to continue");
            response.sendRedirect("login.jsp");
        } else {
            session.setAttribute("message","Email Id exists");
            response.sendRedirect("register.jsp");
        }
    }

}