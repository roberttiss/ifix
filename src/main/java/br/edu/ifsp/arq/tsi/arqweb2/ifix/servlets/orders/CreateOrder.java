package br.edu.ifsp.arq.tsi.arqweb2.ifix.servlets.orders;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Status;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.PaymentMethodDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao.ServiceOrderDAO;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/create-order")
public class CreateOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        String observation = request.getParameter("observation");
        String paymentMethod = request.getParameter("paymentMethod");

        BigDecimal value = new BigDecimal(request.getParameter("price"));
        HttpSession session = request.getSession(false);
        Client client = (Client) session.getAttribute("client");

        PaymentMethodDAO pmDao = new PaymentMethodDAO(DataSourceSearcher.getInstance().getDataSource());
        PaymentMethod pm = pmDao.getPaymentMethodByName(paymentMethod);

        ServiceOrder so = new ServiceOrder();
        so.setDescription(description);
        so.setIssueDate(Date.valueOf(LocalDate.now()).toLocalDate());
        so.setValue(value);
        so.setObservation(observation);
        so.setStatus(Status.PENDING_APPROVAL);
        so.setPaymentMethod(pm);
        so.setClient(client);

        String url;

        ServiceOrderDAO soDao = new ServiceOrderDAO(DataSourceSearcher.getInstance().getDataSource());

        if(soDao.save(so)) {
            request.setAttribute("result", "registered");
            url = "all-orders";
        } else {
            request.setAttribute("result", "error");
            url = "createOrder.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
}