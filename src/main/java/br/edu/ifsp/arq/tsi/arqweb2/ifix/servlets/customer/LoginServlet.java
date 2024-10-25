package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.customer;

import java.io.IOException;
import java.util.Optional;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ClientDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		
		String url;
		
		ClientDAO clientDao = new ClientDAO(DataSourceSearcher.getInstance().getDataSource());
		
		Optional<Client> optional = clientDao.getUserByCpf(cpf);
		if(optional.isPresent()) {
			Client client = optional.get();
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("client", client);
			resp.sendRedirect(req.getContextPath() + "/views/index.jsp");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/login.jsp");
            dispatcher.forward(req, resp);
		}
	}

}





