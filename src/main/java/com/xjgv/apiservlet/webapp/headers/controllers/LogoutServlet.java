package com.xjgv.apiservlet.webapp.headers.controllers;

import com.xjgv.apiservlet.webapp.headers.services.LoginService;
import com.xjgv.apiservlet.webapp.headers.services.LoginServiceCookieImpl;
import com.xjgv.apiservlet.webapp.headers.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> username = auth.getUsername(req);

        if (username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
