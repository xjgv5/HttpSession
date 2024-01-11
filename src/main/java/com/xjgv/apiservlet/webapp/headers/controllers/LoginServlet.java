package com.xjgv.apiservlet.webapp.headers.controllers;

import com.xjgv.apiservlet.webapp.headers.services.LoginService;
import com.xjgv.apiservlet.webapp.headers.services.LoginServiceCookieImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceCookieImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\" UTF-8\">");
                out.println("         <title>Hola</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Hola" + usernameOptional.get() + " ya has iniciado sesión con éxito </h1>");
                out.println("         <div>");
                out.println("           <a href='" + req.getContextPath() + "/index.html' class='btn btn-primary'>Volver</a> ");
                out.println("           <a href='" + req.getContextPath() + "/logout' class='btn btn-primary'>Cerrar sesión</a> ");
                out.println("         <div>");
                out.println("     </body>");
                out.println("</html>");
            }
        }else {

        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, String> errores = new HashMap<>();

        if(username == null || username.isBlank()){
            errores.put("username", "El usuario no debe estar vacio");
        }
        if (password == null || password.isBlank()){
            errores.put("password", "Debe ingresar la contraseña");
        }

        if(errores.isEmpty()) {
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {

                HttpSession session = req.getSession();
                session.setAttribute("username", username);

                resp.sendRedirect(req.getContextPath() + "/login.html");
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No está autorizado para ingresar a esta página");
            }
        }else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
