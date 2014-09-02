package com.scwcd.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrimeiroController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println(config.getInitParameter("initParamServlet"));
		
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("SERVLET CALL");
		
		
		PrintWriter writer = resp.getWriter();
		
		writer.append("<table>");
		writer.append("<thead>");
		writer.append("<tr>");
		writer.append("<td>");
		writer.append("NOME");
		writer.append("</td>");
		writer.append("<td>");
		writer.append("NUMERO");
		writer.append("</td>");
		writer.append("</tr>");
		writer.append("</thead>");
		writer.append("<tr>");
		writer.append("<td>");
		writer.append("");
		writer.append("</td>");
		writer.append("<td>");
		writer.append("NUMERO");
		writer.append("</td>");
		writer.append("</tr>");
		writer.append("</table>");
		
		throw new IllegalStateException();
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/home");
		//dispatcher.forward(req, resp);
	}
	
}
