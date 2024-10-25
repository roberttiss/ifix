package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.orders;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet("/all-orders")
public class RetrieveOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RetrieveOrder() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("client") == null){
            response.sendRedirect("login.jsp");
        } else {
            Client client = (Client) session.getAttribute("client");
            ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());
            List<ServiceOrder> serviceOrders = soDao.getOrderByClient(client.getId());
            request.setAttribute("orders", serviceOrders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/orders/orders.jsp");
            dispatcher.forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}