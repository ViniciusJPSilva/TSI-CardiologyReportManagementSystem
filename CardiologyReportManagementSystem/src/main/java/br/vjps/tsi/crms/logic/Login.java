package br.vjps.tsi.crms.logic;

import java.io.IOException;

import br.vjps.tsi.crms.dao.UserDAO;
import br.vjps.tsi.crms.models.Physician;
import br.vjps.tsi.crms.routines.CancelExams;
import br.vjps.tsi.crms.system.SystemSettings;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "login.jsp";

		Long login = Long.parseLong(request.getParameter("crm"));
		String passwd = request.getParameter("password");
		
		
		try (UserDAO userDao = new UserDAO()) {
			Physician physician = userDao.validate(login, passwd);
			
			if(physician != null) {
				//Recupera Sessão
				HttpSession sessao = request.getSession();
				
				//Define o tempo de atividade da sessão (em segundos)
				sessao.setMaxInactiveInterval(SystemSettings.INACTIVITY_TIMEOUT * 60);
				
				sessao.setAttribute("status", true);
				sessao.setAttribute("user", physician);
				
				CancelExams.execute();
				url = "menu.jsp";
			}
		}
		
		return url;
	}
	
}
