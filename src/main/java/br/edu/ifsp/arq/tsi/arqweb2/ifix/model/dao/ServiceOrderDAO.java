package br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Status;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderDAO {

    private final DataSource dataSource;

    public ServiceOrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

        public boolean save(ServiceOrder serviceOrder) {
        String sql = "INSERT INTO service_order (description, issueDate, value, observation, status, paymentMethod, client_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, serviceOrder.getDescription());
            ps.setDate(2, Date.valueOf(serviceOrder.getIssueDate()));
            ps.setBigDecimal(3, serviceOrder.getValue());
            ps.setString(4, serviceOrder.getObservation());
            ps.setString(5, serviceOrder.getStatus().toString());
            ps.setString(6, serviceOrder.getPaymentMethod().getName());
            ps.setLong(7, serviceOrder.getClient().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ServiceOrder", e);
        }
    }

    public List<ServiceOrder> getOrderByClient(Long clientId) {
        String sql = "SELECT id, description, issueDate, completionDate, value, observation, status, paymentMethod FROM service_order WHERE client_id = ?";
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ServiceOrder serviceOrder = new ServiceOrder();
                    PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(dataSource);
                    serviceOrder.setId(rs.getLong(1));
                    serviceOrder.setDescription(rs.getString(2));
                    serviceOrder.setIssueDate(rs.getDate(3).toLocalDate());
                    if (rs.getDate(4) != null) {
                        serviceOrder.setCompletionDate(rs.getDate(4).toLocalDate());
                    } else {
                        serviceOrder.setCompletionDate(null);
                    }
                    serviceOrder.setValue(rs.getBigDecimal(5));
                    serviceOrder.setObservation(rs.getString(6));
                    serviceOrder.setStatus(Status.valueOf(rs.getString(7)));
                    serviceOrder.setPaymentMethod(paymentMethodDAO.getPaymentMethodByName(rs.getString(8)));
                    serviceOrders.add(serviceOrder);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting ServiceOrder", e);
        }
        return serviceOrders;

    }

    public ServiceOrder getById(Long id) {
        String sql = "SELECT id, description, issueDate, completionDate, value, observation, status, paymentMethod FROM service_order WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ServiceOrder serviceOrder = new ServiceOrder();
                    PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(dataSource);
                    serviceOrder.setId(rs.getLong(1));
                    serviceOrder.setDescription(rs.getString(2));
                    serviceOrder.setIssueDate(rs.getDate(3).toLocalDate());
                    if (rs.getDate(4) != null) {
                        serviceOrder.setCompletionDate(rs.getDate(4).toLocalDate());
                    } else {
                        serviceOrder.setCompletionDate(null);
                    }
                    serviceOrder.setValue(rs.getBigDecimal(5));
                    serviceOrder.setObservation(rs.getString(6));
                    serviceOrder.setStatus(Status.valueOf(rs.getString(7)));
                    serviceOrder.setPaymentMethod(paymentMethodDAO.getPaymentMethodByName(rs.getString(8)));
                    return serviceOrder;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting ServiceOrder", e);
        }
        return null;
    }

    public boolean update(ServiceOrder serviceOrder){
        String sql = "UPDATE service_order SET description = ?, observation = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, serviceOrder.getDescription());
            ps.setString(2, serviceOrder.getObservation());
            ps.setLong(3, serviceOrder.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating ServiceOrder", e);
        }
    }

    public boolean payOrder(Long id) {
        String sql = "UPDATE service_order SET status = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, Status.APPROVED.toString());
            ps.setLong(2, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error paying ServiceOrder", e);
        }
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM service_order WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ServiceOrder", e);
        }
    }
}
