package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String age = request.getParameter("age");
        PrintWriter out  = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>My name is :" + nom + " and my age is :"+age+ "</h1>");
        out.println("</body></html>");
    }
}