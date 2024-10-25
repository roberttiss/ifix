package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.orders;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pay-order")
public class PayOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PayOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());
        if(soDao.payOrder(id)){
            request.setAttribute("result", "paid");
        } else {
            request.setAttribute("result", "error");
        }

        response.sendRedirect("all-orders");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}