package com.xjgv.apiservlet.webapp.headers.controllers;

import com.xjgv.apiservlet.webapp.headers.models.Producto;
import com.xjgv.apiservlet.webapp.headers.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html data-bs-theme=\"dark\">");
            out.println("     <head>");
            out.println("         <meta charset=\" UTF-8\">");
            out.println("         <title>Listado de productos</title>");
            out.println("         <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN' crossorigin='anonymous'>");
            out.println("         ");
            out.println("     </head>");
            out.println("     <body>");
            out.println("     <div class='container mt-5'>");
            out.println("         <h1>Listado de productos</h1>");
            if (usernameOptional.isPresent()){
                out.println("<h2 style='color: teal' class='mb-3'> Bienvenido " + usernameOptional.get() + "</h2>");
            }
            out.println("         <table class='table table-striped'>");
            out.println("         <tr>");
            out.println("           <th>ID</th>");
            out.println("           <th>Nombre</th>");
            out.println("           <th>Tipo</th>");
            if (usernameOptional.isPresent()){
                out.println("           <th>Precio</th>");
            }
            out.println("         </tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                }
                out.println("</tr>");
            });
            out.println("         </table>");
            out.println("   <a href='" + req.getContextPath() + "/index.html' class='btn btn-primary'>Volver</a>");
            out.println("     </div>");
            out.println("     </body>");
            out.println("</html>");
        }
    }
}
