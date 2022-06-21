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


public final class QueryOrdiniProd implements Table<OrdiniProd, Integer> {    
    public static final String TABLE_NAME = "ordini_produzione";


    private final Connection connection; 
    
    public QueryOrdiniProd(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }



    private ObservableList<OrdiniProd> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<OrdiniProd> result = FXCollections.observableArrayList();
        try {
            
            while (resultSet.next()) {
            	final String idRAL = resultSet.getString("IdRAL");
            	final Integer codiceCentro = resultSet.getInt("CodiceCentro");
            	final String codCliente = resultSet.getString("CodCliente");
            	final Integer numero = resultSet.getInt("NumeroDDT");
            	final String descrRicetta = resultSet.getString("ri.Descr");
            	final String codArticolo = resultSet.getString("CodArticolo");
            	final Integer qtà = resultSet.getInt("QtàRes");
            	final java.sql.Date dataScad  = resultSet.getDate("DataScad");



        /*    	final String fase = resultSet.getString("Fase");
            	final Integer qtàPronta = resultSet.getInt("QtàPronta");
            	final Integer qtàEvasa = resultSet.getInt("QtàEvasa");
            	final String evaso = resultSet.getString("Evaso");
            	final String stato = resultSet.getString("Stato");
            	
            	final Integer nbadge = resultSet.getInt("Nbadge");
            	final String commessa = resultSet.getString("Commessa");
            	final String tipo = resultSet.getString("Tipo");
            	final Integer anno = resultSet.getInt("Anno");
            	final String serie = resultSet.getString("Serie");
            	final Integer riga = resultSet.getInt("Riga");            
          */  	
               // final OrdiniProd queryProd = new OrdiniProd(fase, codCliente, qtà, qtàPronta, qtàEvasa, evaso, dataScad, stato, idRAL, codiceCentro, nbadge, codArticolo, commessa, tipo, anno, serie, numero, riga);
            	
            	final OrdiniProd queryProd = new OrdiniProd(idRAL, codiceCentro, codCliente, numero, descrRicetta, codArticolo, qtà, dataScad);
                result.add(queryProd);
            }
        } catch (final SQLException e) {}
        return result;
    }

    @Override
    public ObservableList<OrdiniProd> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT IdRAL, CodiceCentro, CodCliente, NumeroDDT, ri.Descr,  op.CodArticolo, Qtà-QtàPronta as QtàRes, DataScad, QtàEvasa FROM " + "ordini_produzione op " + "LEFT JOIN articoli ar on op.CodArticolo=ar.CodArticolo " +" LEFT JOIN ricette ri on ar.CodRicetta= ri.CodRicetta");
                        
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public ObservableList<OrdiniProd> findByPrimaryKey(final String codCliente) {
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
	public boolean save(OrdiniProd value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(OrdiniProd updatedValue) {
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
