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


public final class QueryArticoli implements Table<Articoli, Integer> {    
    public static final String TABLE_NAME = "articoli";


    private final Connection connection; 
    
    public QueryArticoli(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }


    private ObservableList<Articoli> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<Articoli> result = FXCollections.observableArrayList();
        try {
            
            while (resultSet.next()) {
            	final String codArticolo = resultSet.getString("CodArticolo");
            	final String descr =resultSet.getString("Descr");
            	final Double peso =resultSet.getDouble("Peso");
            	final String codRicetta = resultSet.getString("CodRicetta");
            	
                final Articoli querys = new Articoli(codArticolo, descr, peso, codRicetta);
                result.add(querys);
            }
        } catch (final SQLException e) {}
        return result;
    }

    @Override
    public ObservableList<Articoli> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT CodArticolo, Descr, Peso, CodRicetta FROM articoli");
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    
    
    public ObservableList<Articoli> findByPrimaryKey(final String codarticolo) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE CodArticolo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codarticolo);
            final ResultSet resultSet = statement.executeQuery();
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }  
    
    public boolean deleteArt(final String codArt) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE CodArticolo = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codArt);
            return statement.executeUpdate() > 0;
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
	public boolean update(Articoli updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}

	    
    @Override
    public boolean save(final Articoli articolo) {
        final String querys = "INSERT INTO " + TABLE_NAME + "(CodArticolo, Descr, Peso, CodRicetta) VALUES (?,?,?,?)";
        
        try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
            statement.setString(1, articolo.getCodArticolo());
            statement.setString(2, articolo.getDescr());
            statement.setDouble(3, articolo.getPeso());
            statement.setString(4, articolo.getCodRicetta());
 
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
