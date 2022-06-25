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

				final Integer numeroLav = resultSet.getInt("NumeroLav");
				final java.sql.Date dataInizio = resultSet.getDate("dataInizio");
				final java.sql.Date dataFine = resultSet.getDate("dataFine");
				final double qtà = resultSet.getInt("Quantità");
				final String stato = resultSet.getString("Stato");				
				final Integer nbadge = resultSet.getInt("Nbadge");
				final String commessa = resultSet.getString("Commessa");
				final Integer fase = resultSet.getInt("Fase");

				final Lavorazioni queryProd = new Lavorazioni(numeroLav, dataInizio, dataFine, qtà, stato, nbadge, commessa, fase);
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


	@Override
	public boolean update(Lavorazioni updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Lavorazioni value) {
		final String querys = "INSERT INTO " + TABLE_NAME + "(NumeroLav, dataInizio, dataFine, Quantità, Stato, Nbadge, Commessa, Fase)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
			statement.setInt(1, value.getNumeroLav());
			statement.setDate(2, value.getDataInizio());
			statement.setDate(3, value.getDataFine());
			statement.setDouble(4, value.getQtà());
			statement.setString(5, value.getStato());
			statement.setInt(6, value.getNbadge());
			statement.setString(7, value.getCommessa());
			statement.setInt(8, value.getFase());
			statement.executeUpdate();
			return true;
		} catch (final SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}

	}



	/*	@Override
	public boolean saveOrdP() {
		final String querys = "INSERT INTO" + TABLE_NAME + "(Commessa, Fase, CodCliente, Qtà, QtàPronta, QtàEvasa, Evaso, DataScad, Stato, IdRAL, Nbadge, "
				+ "CodArticolo, TipoDDT, AnnoDDT, SerieDDT, NumeroDDT, RigaDDT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
			statement.setString(1, commessa);
			statement.setInt(2, fase);
			statement.setString(3, codCl);
			statement.setInt(4, qtà);
			statement.setInt(5, qtàpr);
			statement.setInt(6, qtàev);
			statement.setLong(7, ev);
			statement.setDate(8, datascad);
			statement.setString(9, stato);
			statement.setString(10, idral);
			statement.setInt(11, nbadge);
			statement.setString(12, codart);
			statement.setString(13, tipoddt);
			statement.setInt(14, annoddt);
			statement.setString(15, serieddt);
			statement.setInt(16, rigaddt);
			return true;
		} catch (final SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	/*
    @Override
    public boolean saveOrdP() {

        final String querys = "INSERT INTO " + TABLE_NAME + "(id, firstName, lastName, birthday) VALUES (?,?,?,?)";
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
