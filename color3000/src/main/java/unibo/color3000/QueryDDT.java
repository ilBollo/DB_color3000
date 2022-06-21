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


public final class QueryDDT implements Table<Ddt, Integer> {    
    public static final String TABLE_NAME = "ddt";


    private final Connection connection; 
    
    public QueryDDT(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }



    private ObservableList<Ddt> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<Ddt> result = FXCollections.observableArrayList();
        try {
            
            while (resultSet.next()) {
            	final int numeroDDT = resultSet.getInt("NumeroDDT");
            	final int anno = resultSet.getInt("AnnoDDT");
            	final String serie =resultSet.getString("SerieDDT");
            	final String tipo =resultSet.getString("TipoDDT");
            	final java.sql.Date dataDDT = resultSet.getDate("DataDDT");
            	final String codCliente =resultSet.getString("CodCliente");
            	final String decrCl = resultSet.getString("Denominazione");
            	final int nbadge = resultSet.getInt("Nbadge");
            	
                final Ddt querys = new Ddt(numeroDDT, anno, serie, tipo, dataDDT, codCliente, decrCl, nbadge);
                result.add(querys);
            }
        } catch (final SQLException e) {}
        return result;
    }

    @Override
    public ObservableList<Ddt> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT ddt.NumeroDDT, ddt.AnnoDDT, ddt.SerieDDT, ddt.TipoDDT, ddt.DataDDT, ddt.CodCliente, cli.Denominazione, Nbadge FROM " + "ddt " +"LEFT JOIN clienti cli on ddt.CodCliente=cli.CodCliente");
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public ObservableList<Ddt> findByPrimaryKey(final String codCliente) {
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
	public boolean update(Ddt updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}



	    
    @Override
    public boolean save(final Ddt ddt) {
        final String querys = "INSERT INTO " + TABLE_NAME + "(NumeroDDT, AnnoDDT, SerieDDT, TipoDDT, DataDDT, CodCliente, Nbadge) VALUES (?,?,?,?,?,?,?)";
        
        try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
            statement.setInt(1, ddt.getNumeroDDT());
            statement.setInt(2, ddt.getAnno());
            statement.setString(3, ddt.getSerie());
            statement.setString(4, ddt.getTipo());
            statement.setDate(5, ddt.getDataDDT());
            statement.setString(6, ddt.getCodCliente());
            statement.setInt(7, ddt.getNbadge());
            statement.executeUpdate();
            return true;
        } catch (final SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
/*
	@Override
	public boolean update(Ddt updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}
  
    /*
    
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
