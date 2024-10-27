package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.orders;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/update-order")
public class UpdateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateOrder() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());
        ServiceOrder so = soDao.getById(id);
        request.setAttribute("observation", so.getObservation());
        request.setAttribute("description", so.getDescription());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/orders/updateOrder.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String description = request.getParameter("description").trim();
        String observation = request.getParameter("observation").trim();

        ServiceOrder so = new ServiceOrder();
        so.setDescription(description);
        so.setObservation(observation);
        so.setId(id);

        ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());

        String url;

        if(soDao.update(so)) {
            request.setAttribute("result", "update");
            url = "all-orders";
        } else {
            request.setAttribute("result", "error");
            url = "updateOrder.jsp";
        }

        response.sendRedirect(url);
    }
}