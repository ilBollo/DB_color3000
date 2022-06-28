package unibo.color3000;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public final class QueryLavorazioni implements Table<Lavorazioni, Integer> {    
	public static final String TABLE_NAME = "lavorazione";


	private final Connection connection; 

	public QueryLavorazioni(final Connection connection) {
		this.connection = Objects.requireNonNull(connection);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}



	private ObservableList<Lavorazioni> readQueryFromResultSet(final ResultSet resultSet) {
		final ObservableList<Lavorazioni> result = FXCollections.observableArrayList();
		try {

			while (resultSet.next()) {

				final java.util.Date dataInizio = resultSet.getDate("dataInizio");
				final java.util.Date dataFine = resultSet.getDate("dataFine");
				final double qtà = resultSet.getInt("Quantità");
				final String stato = resultSet.getString("Stato");				
				final Integer nbadge = resultSet.getInt("Nbadge");
				final String commessa = resultSet.getString("Commessa");
				final Integer fase = resultSet.getInt("Fase");

				final Lavorazioni queryProd = new Lavorazioni(dataInizio, dataFine, qtà, stato, nbadge, commessa, fase);
				result.add(queryProd);
			}
		} catch (final SQLException e) {}
		return result;
	}


	@Override
	public ObservableList<Lavorazioni> findAll() {
		try (final Statement statement = this.connection.createStatement()) {
			final ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
			return readQueryFromResultSet(resultSet);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public ObservableList<Lavorazioni> findByPrimaryKey(final String commessa) {
		final String query = "SELECT * FROM " + TABLE_NAME + " WHERE CodCliente = ?";
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setString(1, commessa);
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

	java.util.Date date = new Date();
	


	@Override
	public boolean save(Lavorazioni value) {
		final String querys = "INSERT INTO lavorazione (dataInizio, dataFine, Quantità, Stato, Nbadge, Commessa, Fase)"
				+ " VALUES (?,?,?,?,?,?,?)";
		try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
			statement.setTimestamp(1, new Timestamp(date.getTime()));
			statement.setTimestamp(2, null);
			statement.setDouble(3, value.getQtà());
			statement.setString(4, value.getStato());
			statement.setInt(5, value.getNbadge());
			statement.setString(6, value.getCommessa());
			statement.setInt(7, value.getFase());
			statement.executeUpdate();
			return true;
		} catch (final SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}

	}

	 

		@Override
		public boolean update(Lavorazioni updatedValue) {
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

   
	 */
}
