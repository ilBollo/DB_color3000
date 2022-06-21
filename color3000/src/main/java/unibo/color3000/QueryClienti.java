package unibo.color3000;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public final class QueryClienti implements Table<Clienti, Integer> {    
    public static final String TABLE_NAME = "clienti";


    private final Connection connection; 
    
    public QueryClienti(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }



    private ObservableList<Clienti> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<Clienti> result = FXCollections.observableArrayList();
        try {
            
            while (resultSet.next()) {
            	final String CodCliente = resultSet.getString("CodCliente");
            	final String PartitaIVA = resultSet.getString("PartitaIVA");
            	final String Denominazione = resultSet.getString("Denominazione");
            	final String Indirizzo = resultSet.getString("Indirizzo");
            	final Integer Telefono = resultSet.getInt("Telefono");
            	final String Mail = resultSet.getString("Mail");
            	final String Nazionalità = resultSet.getString("Nazionalità");            
            	
                final Clienti querys = new Clienti(CodCliente, PartitaIVA, Denominazione, Indirizzo, Telefono, Mail, Nazionalità);
                result.add(querys);
            }
        } catch (final SQLException e) {}
        return result;
    }

    @Override
    public ObservableList<Clienti> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public ObservableList<Clienti> findByPrimaryKey(final String codCliente) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE CodCliente = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codCliente);
            final ResultSet resultSet = statement.executeQuery();
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }  
    

	@Override
	public boolean delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Clienti value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Clienti updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}


    
/*    @Override
    public boolean save(final Query query) {
        final String query = "INSERT INTO " + TABLE_NAME + "(id, firstName, lastName, birthday) VALUES (?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, student.getBirthday().map(Utils::dateToSqlDate).orElse(null));
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public boolean delete(final Integer id) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public boolean update(final Student student) {
        final String query =
            "UPDATE " + TABLE_NAME + " SET " +
                "firstName = ?," + 
                "lastName = ?," +
                "birthday = ? " + 
            "WHERE id = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDate(3, student.getBirthday().map(Utils::dateToSqlDate).orElse(null));
            statement.setInt(4, student.getId());
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    */
}
