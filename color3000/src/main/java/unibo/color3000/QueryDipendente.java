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


public final class QueryDipendente implements Table<Dipendente, Integer> {    
    public static final String TABLE_NAME = "dipendente";


    private final Connection connection; 
    
    public QueryDipendente(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public boolean createTable() {
        // 1. Create the statement from the open connection inside a try-with-resources
        try (final Statement statement = this.connection.createStatement()) {
            // 2. Execute the statement with the given query
            statement.executeUpdate(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "id INT NOT NULL PRIMARY KEY," +
                        "firstName CHAR(40)," + 
                        "lastName CHAR(40)," + 
                        "birthday DATE" + 
                    ")");
            return true;
        } catch (final SQLException e) {
            // 3. Handle possible SQLExceptions
            return false;
        }
    }

    @Override
    public boolean dropTable() {
        // 1. Create the statement from the open connection inside a try-with-resources
        try (final Statement statement = this.connection.createStatement()) {
            // 2. Execute the statement with the given query
            statement.executeUpdate("DROP TABLE " + TABLE_NAME);
            return true;
        } catch (final SQLException e) {
            // 3. Handle possible SQLExceptions
            return false;
        }
    }



    /**
     * Given a ResultSet read all the students in it and collects them in a List
     * @param resultSet a ResultSet from which the Student(s) will be extracted
     * @return a List of all the students in the ResultSet
     */
    private ObservableList<Dipendente> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<Dipendente> result = FXCollections.observableArrayList();
        try {
            // ResultSet encapsulate a pointer to a table with the results: it starts with the pointer
            // before the first row. With next the pointer advances to the following row and returns 
            // true if it has not advanced past the last row
            while (resultSet.next()) {
                // To get the values of the columns of the row currently pointed we use the get methods 
            
            	final Integer nBadge = resultSet.getInt("Nbadge");
            	final String cod_fisc = resultSet.getString("Cod_fisc");
                final String nome = resultSet.getString("Nome");
                final String cognome = resultSet.getString("Cognome");
                final String qualifica = resultSet.getString("Qualifica");
                final String turno = resultSet.getString("Turno");
                final Integer stipendio = resultSet.getInt("Stipendio");
                final Dipendente querys = new Dipendente(nBadge,cod_fisc, nome, cognome, qualifica, stipendio, turno);
                result.add(querys);
            }
        } catch (final SQLException e) {}
        return result;
    }

    @Override
    public ObservableList<Dipendente> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public ObservableList<Dipendente> findByPrimaryKey(final String nome) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE Nome = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, nome);
            final ResultSet resultSet = statement.executeQuery();
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }  
    

	@Override
	public boolean save(Dipendente value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Dipendente updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer primaryKey) {
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
