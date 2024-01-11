<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Map" %>

<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>
<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">
<head>
    <meta charset="UTF-8">
    <title>Formulario login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container mt-5 pt-5 d-flex flex-column align-items-center justify-content-center">
    <h1>Iniciar sesión</h1>
    <form action="/webapp-session/login" method="post" style="width: 100%">
        <div class="col-sm-8">
            <label for="username" class="form-label">Usuario</label>
            <div class="input-group mb-3">
                <input type="text" name="username" id="username" class="form-control <% if (errores != null && errores.containsKey("username")) out.println("is-invalid");%>">
            <%
                if (errores!= null && errores.containsKey("username")){
                    out.println("<div class='invalid-feedback'>El usuario no puede estar vacio</div>");
                }
            %>
            </div>
        </div>
        <div class="col-sm-8">
            <label for="password" class="form-label"> Contraseña </label>
            <div class="input-group mb-3">
                <input type="password" name="password" id="password" class="form-control <% if(errores != null && errores.containsKey("password")) out.println("is-invalid"); %>">
                <%
                    if(errores != null && errores.containsKey("password")){
                        out.println("<div class='invalid-feedback'>Ingresa una contraseña</div>");
                    }
                %>
            </div>
        </div>
        <div>
            <input type="submit" value="Iniciar sesión" class="btn btn-primary">
        </div>
    </form>
</div>
</body>
</html>