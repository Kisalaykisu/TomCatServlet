package com.myfirstservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Kisalay"),
                @WebInitParam(name = "password", value="Password@12345")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user= request.getParameter("user");
        String pwd= request.getParameter("pwd");

        String userID=getServletConfig().getInitParameter( "user");
        String password=getServletConfig().getInitParameter("password");

        String nameValidate = "^[A-Z]{1}[a-z]{2,}";
        String passwordValidate = "^(?=.*[A-z])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$";

        if(userID.equals(user) && password.equals(pwd)) {
            request.setAttribute( "user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward (request,response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request,response);
        }
    }
}
