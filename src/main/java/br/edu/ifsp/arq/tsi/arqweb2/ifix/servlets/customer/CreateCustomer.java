package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.customer;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Address;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ClientDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-customer")
public class CreateCustomer extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public CreateCustomer() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String cpf = req.getParameter("cpf");

		String street = req.getParameter("street");
		String number = req.getParameter("number");
		String complement = req.getParameter("complement");
		String neighborhood = req.getParameter("neighborhood");
		String zipCode = req.getParameter("zipCode");
		String city = req.getParameter("city");
		String state = req.getParameter("state");

		Address address = new Address();
		address.setStreet(street);
		address.setNumber(number);
		address.setComplement(complement);
		address.setNeighborhood(neighborhood);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setState(state);

		Client client = new Client();
		client.setName(name);
		client.setEmail(email);
		client.setPhone(phone);
		client.setCpf(cpf);
		client.setActive(true);
		client.setAddress(address);

		RequestDispatcher dispatcher = null;

		ClientDAO clientDao = new ClientDAO(DataSourceSearcher.getInstance().getDataSource());

		if(clientDao.save(client)) {
			req.setAttribute("result", "registered");
			resp.sendRedirect(req.getContextPath() + "/views/login.jsp");
		}else {
			req.setAttribute("result", "notRegistered");
			dispatcher = req.getRequestDispatcher("createCustomer.jsp");
			dispatcher.forward(req, resp);
		}

	}

}







