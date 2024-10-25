package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.customer;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ClientDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-customer")
public class UpdateCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateCustomer() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setPhone(phone);

        ClientDAO clientDAO = new ClientDAO(DataSourceSearcher.getInstance().getDataSource());

        String url;

        if(clientDAO.update(client)) {
            request.setAttribute("result", "registered");
            url = "/views/customer.jsp";
        } else {
            request.setAttribute("result", "notRegistered");
            url = "/views/updateCustomer.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}