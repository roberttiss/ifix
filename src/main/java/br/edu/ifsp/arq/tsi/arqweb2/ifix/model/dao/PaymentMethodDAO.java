package br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.PaymentMethod;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO {

    private DataSource dataSource;

    public PaymentMethodDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        String sql = "SELECT id, name FROM payment_method WHERE id = ?";
        PaymentMethod paymentMethod = new PaymentMethod();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paymentMethod.setId(rs.getLong(1));
                    paymentMethod.setName(rs.getString(2));
                }
            }
            return paymentMethod;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting PaymentMethod", e);
        }
    }

    public List<PaymentMethod> getAllPaymentsMethods(){
        String sql = "SELECT id, name FROM payment_method";
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaymentMethod payment = new PaymentMethod();
                payment.setId(rs.getLong("id"));
                payment.setName(rs.getString("name"));
                paymentMethods.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting PaymentMethods", e);
        }
        return paymentMethods;
    }

    public PaymentMethod getPaymentMethodByName(String name) {
        String sql = "SELECT id, name FROM payment_method WHERE name = ?";
        PaymentMethod paymentMethod = new PaymentMethod();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paymentMethod.setId(rs.getLong(1));
                    paymentMethod.setName(rs.getString(2));
                }
            }
            return paymentMethod;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting PaymentMethod", e);
        }
    }
}
