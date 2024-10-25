package br.edu.ifsp.arq.tsi.arqweb2.ifix.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.ifix.model.entity.Client;

public class ClientDAO {
	
	private DataSource dataSource;

	public ClientDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
public Boolean save(Client client) {
    Optional<Client> optional = getUserByCpf(client.getCpf());
    if (optional.isPresent()) {
        return false;
    }
    String userSql = "insert into client (name, email, phone, cpf, active, address_id) values (?,?,?,?,?,?)";
    String addressSql = "insert into address (street, number, complement, neighborhood, zipCode, city, state) values (?,?,?,?,?,?,?)";
    try (
        Connection conn = dataSource.getConnection();
        var psAddress = conn.prepareStatement(addressSql, PreparedStatement.RETURN_GENERATED_KEYS);
        var psUser = conn.prepareStatement(userSql)
    ) {
        // Insert address
        var address = client.getAddress();
        psAddress.setString(1, address.getStreet());
        psAddress.setString(2, address.getNumber());
        psAddress.setString(3, address.getComplement());
        psAddress.setString(4, address.getNeighborhood());
        psAddress.setString(5, address.getZipCode());
        psAddress.setString(6, address.getCity());
        psAddress.setString(7, address.getState());
        psAddress.executeUpdate();

        // Retrieve generated address ID
        var rsAddress = psAddress.getGeneratedKeys();
        if (rsAddress.next()) {
            address.setId(rsAddress.getLong(1));
        } else {
            throw new SQLException("Failed to retrieve address ID.");
        }

        // Insert client with address ID
        psUser.setString(1, client.getName());
        psUser.setString(2, client.getEmail());
        psUser.setString(3, client.getPhone());
        psUser.setString(4, client.getCpf());
        psUser.setBoolean(5, client.isActive());
        psUser.setLong(6, address.getId());
        psUser.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erro durante a escrita no BD", e);
    }
    return true;
}

	public Optional<Client> getUserByCpf(String cpf) {
	    String sql = "SELECT id, name, email FROM client WHERE cpf = ?";
	    Optional<Client> optional = Optional.empty();
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, cpf);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Client client = new Client();
	                client.setId(rs.getLong(1));
	                client.setName(rs.getString(2));
	                client.setEmail(rs.getString(3));
	                optional = Optional.of(client);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro durante a consulta no BD", e);
	    }
	    return optional;
	}

	public boolean update(Client client) {
		String sql = "UPDATE client SET name = ?, phone = ? WHERE cpf = ?";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, client.getName());
			ps.setString(2, client.getPhone());
			ps.setLong(3,client.getId());
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a atualização no BD", e);
		}
	}



	public boolean delete(String cpf) {
		String sql = "UPDATE client SET active = false WHERE cpf = ?";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cpf);
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Erro durante a exclusão no BD", e);
		}

    }
}
