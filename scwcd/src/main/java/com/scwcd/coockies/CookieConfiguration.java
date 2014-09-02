package com.scwcd.coockies;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CookieConfiguration extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String nome = request.getParameter("username");
        Cookie cookie = new Cookie("username", nome);

        cookie.setMaxAge(30*60);
        response.addCookie(cookie);

        RequestDispatcher view = request.getRequestDispatcher("/check-cookie.do");
        view.forward(request, response);
    }

}
