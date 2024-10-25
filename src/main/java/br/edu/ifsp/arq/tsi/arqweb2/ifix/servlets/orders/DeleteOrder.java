package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.orders;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/delete-order")
public class DeleteOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteOrder() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());
        soDao.delete(id);
        response.sendRedirect("all-orders");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}