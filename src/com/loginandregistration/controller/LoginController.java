package com.loginandregistration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String getLoginPage() {
        return "login.jsp";
    }

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if(emailId.equals("alekhya@gmail.com") && password.equals("Alekhya@1")) {
            session.setAttribute("emailId",emailId);
            response.sendRedirect("welcome.jsp");
        } else {
            session.setAttribute("message","Data Not Found");
            response.sendRedirect("login.jsp");
        }
    }

}
