package br.vjps.tsi.crms.servlet.controller;

import java.io.IOException;

import br.vjps.tsi.crms.logic.Logic;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeClasse = String.format("br.vjps.tsi.crms.logic.%s", request.getParameter("logic"));
		String url = "error.jsp";
		
		try {
			@SuppressWarnings("deprecation")
			Logic logic = (Logic) Class.forName(nomeClasse).newInstance();
			url = logic.execute(request, response);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
