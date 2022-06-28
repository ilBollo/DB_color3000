package unibo.color3000;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Calendar;
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
				final String centro = resultSet.getString("Descrizione");
				final String commessa = resultSet.getString("Commessa");
				final String cliente = resultSet.getString("Denominazione");
				final Integer fase = resultSet.getInt("Fase");
				final Integer qtà = resultSet.getInt("QtàRes");
				final Integer qtàpr = resultSet.getInt("QtàPronta");
				final Double qtàev = resultSet.getDouble("QtàEvasa");
				final String ev = resultSet.getString("Evaso");
				final java.sql.Date dataScad = resultSet.getDate("DataScad");
				final String stato = resultSet.getString("Stato");
				final String idRAL = resultSet.getString("IdRAL");
				final Integer nbadge = resultSet.getInt("Nbadge");
				final String codArticolo = resultSet.getString("CodArticolo");
				final Integer riga = resultSet.getInt("Riga");

				final OrdiniProd queryProd = new OrdiniProd(centro, commessa, cliente, fase, qtà, qtàpr, qtàev, ev, dataScad, stato, idRAL, nbadge, codArticolo, riga);
				result.add(queryProd);
			}
		} catch (final SQLException e) {}
		return result;
	}

	
	@Override
	public ObservableList<OrdiniProd> findAll() {
		try (final Statement statement = this.connection.createStatement()) {
			final ResultSet resultSet = statement.executeQuery("SELECT ce.Descrizione, op.Commessa, cli.Denominazione, Fase, op.Qtà-op.QtàPronta as QtàRes, op.QtàPronta, op.QtàEvasa, op.Evaso, DataScad, Stato, IdRAL, op.Nbadge, op.CodArticolo, op.Riga FROM " + "ordini_produzione op " + "LEFT JOIN articoli ar on op.CodArticolo=ar.CodArticolo " +"LEFT JOIN ricette ri on ar.CodRicetta= ri.CodRicetta LEFT JOIN fasi fa on op.fase = fa.NumFase and ar.CodRicetta = fa.CodRicetta "
					+ "LEFT JOIN centro ce on fa.CodiceCentro = ce.CodiceCentro LEFT JOIN riga_ddt riga on op.Commessa = riga.Commessa "
					+ "LEFT JOIN ddt on riga.NumeroDDT =ddt.NumeroDDT and riga.SerieDDT = ddt.SerieDDT and riga.AnnoDDT = ddt.AnnoDDT and riga.TipoDDT = ddt.TipoDDT LEFT JOIN clienti cli on ddt.CodCliente = cli.CodCliente "
					+ "WHERE op.Qtà-op.QtàPronta-op.QtàEvasa > 0 "
					+ "order by ce.Descrizione, IdRal, cli.Denominazione");
			
			return readQueryFromResultSet(resultSet);
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
	//cerco solo quelli pronti
	public ObservableList<OrdiniProd> findPronti() {
		try (final Statement statement = this.connection.createStatement()) {
			final ResultSet resultSet = statement.executeQuery("SELECT ce.Descrizione, op.Commessa, cli.Denominazione, Fase, op.Qtà-op.QtàPronta as QtàRes, op.QtàPronta, op.QtàEvasa, op.Evaso, DataScad, Stato, IdRAL, op.Nbadge, op.CodArticolo, op.Riga FROM " + "ordini_produzione op " + "LEFT JOIN articoli ar on op.CodArticolo=ar.CodArticolo " +"LEFT JOIN ricette ri on ar.CodRicetta= ri.CodRicetta LEFT JOIN fasi fa on op.fase = fa.NumFase and ar.CodRicetta = fa.CodRicetta "
					+ "LEFT JOIN centro ce on fa.CodiceCentro = ce.CodiceCentro LEFT JOIN riga_ddt riga on op.Commessa = riga.Commessa "
					+ "LEFT JOIN ddt on riga.NumeroDDT =ddt.NumeroDDT and riga.SerieDDT = ddt.SerieDDT and riga.AnnoDDT = ddt.AnnoDDT and riga.TipoDDT = ddt.TipoDDT LEFT JOIN clienti cli on ddt.CodCliente = cli.CodCliente "
					+ "WHERE op.QtàPronta > 0 "
					+ "order by ce.Descrizione, IdRal, cli.Denominazione");
			
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

	public int searchFase(final String codArt, final int fase) {
		final String query = "SELECT NumFase FROM " + "fasi " +  "LEFT JOIN articoli ar on fasi.CodRicetta = ar.CodRicetta" + " WHERE CodArticolo = ? and NumFase > ?" + " limit 1";
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setString(1, codArt);
			statement.setInt(2, fase);
			final ResultSet resultSet = statement.executeQuery();
			int ris=-1;
			while(resultSet.next())
			{
				ris =resultSet.getInt("NumFase");
			}
			return ris;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);

		}
	}


	public String searchCentro(final String codArt, final int fase) {
		final String query = "SELECT ce.Descrizione FROM " + "articoli ar " +  "LEFT JOIN ricette ri on ar.CodRicetta = ri.CodRicetta LEFT JOIN fasi fa on ri.CodRicetta = fa.CodRicetta LEFT JOIN centro ce on fa.CodiceCentro = ce.CodiceCentro"
				+ " WHERE ar.CodArticolo = ? and fa.NumFase = ?";
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setString(1, codArt);
			statement.setInt(2, fase);
			final ResultSet resultSet = statement.executeQuery();
			String ris="aa";
			while(resultSet.next())
			{
				ris =resultSet.getString("Descrizione");
			}
			return ris;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);

		}
	}
	
	public String searchCliente(final String commessa) {
		final String query = "SELECT cli.Denominazione FROM " + "riga_ddt riga " +  "LEFT JOIN ddt on riga.NumeroDDT =ddt.NumeroDDT and riga.SerieDDT = ddt.SerieDDT and riga.AnnoDDT = ddt.AnnoDDT and riga.TipoDDT = ddt.TipoDDT LEFT JOIN clienti cli on ddt.CodCliente = cli.CodCliente"
				+ " WHERE riga.Commessa = ?";
		
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setString(1, commessa);
			final ResultSet resultSet = statement.executeQuery();
			String ris="aa";
			while(resultSet.next())
			{
				ris =resultSet.getString("Denominazione");
			}
			return ris;
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
	public boolean update(OrdiniProd updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(OrdiniProd value) {
		final String querys = "INSERT INTO " + TABLE_NAME + "(Commessa, Fase, Qtà, QtàPronta, QtàEvasa, Evaso, DataScad, Stato, IdRAL, Nbadge, CodArticolo, Riga)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
			statement.setString(1, value.getCommessa());
			statement.setInt(2, value.getFase());
			statement.setDouble(3, value.getQtà());
			statement.setDouble(4, value.getQtàev());
			statement.setDouble(5, value.getQtàpr());
			statement.setString(6, value.getEv());
			statement.setDate(7, value.getDatascad());
			statement.setString(8, value.getStato());
			statement.setString(9, value.getIdRAL());
			statement.setInt(10, value.getNbadge());
			statement.setString(11, value.getCodArticolo());
			statement.setInt(12, value.getRigaDDT());
			statement.executeUpdate();
			return true;
		} catch (final SQLIntegrityConstraintViolationException e) {
			return false;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}

	}

    public boolean updateStart(final String commessa, final int fase) {
        final String query =
            "UPDATE ordini_produzione SET " +
                "Stato = 'A' " + 
            "WHERE Commessa = ? and Fase = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, commessa);
            statement.setInt(2, fase);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public boolean updateStop(final String commessa, final int fase) {
        final String query =
            "UPDATE ordini_produzione LEFT JOIN lavorazione lav on ordini_produzione.Commessa = lav.Commessa and ordini_produzione.Fase = lav.Fase" + " SET " +
                "ordini_produzione.Stato = 'S', " +
                "lav.Stato = 'S', " +
                "dataFine = now() " +
            "WHERE ordini_produzione.Stato = 'A' and lav.Stato = 'A' and ordini_produzione.Commessa = ? and ordini_produzione.Fase = ?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, commessa);
            statement.setInt(2, fase);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean updateVersa(final String commessa, final int fase, final int qtà) {
        final String query =
            "UPDATE ordini_produzione LEFT JOIN lavorazione lav on ordini_produzione.Commessa = lav.Commessa and ordini_produzione.Fase = lav.Fase" + " SET " +
                "ordini_produzione.Stato = 'N', " +
                "lav.Stato = 'C', " +
                "dataFine = current_date(), " +
                "ordini_produzione.QtàPronta = ordini_produzione.QtàPronta + ? " +
            "WHERE lav.Stato = 'A' and ordini_produzione.Commessa = ? and ordini_produzione.Fase = ? and Qtà-QtàPronta>=?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
        	statement.setInt(1, qtà);
        	statement.setString(2, commessa);
            statement.setInt(3, fase);
            statement.setInt(4, qtà);
            return statement.executeUpdate() > 0;
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
