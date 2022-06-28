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


public final class QueryrigheDDT implements Table<RigheDDT, Integer> {    
    public static final String TABLE_NAME = "riga_ddt";


    private final Connection connection; 
    
    public QueryrigheDDT(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }



    private ObservableList<RigheDDT> readQueryFromResultSet(final ResultSet resultSet) {
        final ObservableList<RigheDDT> result = FXCollections.observableArrayList();
        try {
            
            while (resultSet.next()) {
            	final int riga = resultSet.getInt("Riga");
            	final String commessa = resultSet.getString("Commessa");
            	final String codArticolo =resultSet.getString("CodArticolo");
            	final double qtà =resultSet.getDouble("Qtà");            	
            	final double prezzo =resultSet.getDouble("Prezzo");
            	final String ral =resultSet.getString("RAL");
            	final java.sql.Date dataConsegna = resultSet.getDate("DataConsegna");
            	final double qtàEvasa =resultSet.getDouble("QtàEvasa");
            	final String evaso =resultSet.getString("Evaso");
            	final int numeroDDT = resultSet.getInt("NumeroDDT");
            	final int annoDDT = resultSet.getInt("AnnoDDT");
            	final String serieDDT = resultSet.getString("SerieDDT");
            	final String tipoDDT = resultSet.getString("tipoDDT");
            	final String descRicetta = resultSet.getString("Descr");

                final RigheDDT querys = new RigheDDT(commessa, riga, qtà, prezzo, ral, dataConsegna, qtàEvasa, evaso, codArticolo, numeroDDT, annoDDT, serieDDT, tipoDDT, descRicetta);
                result.add(querys);
            }
        } catch (final SQLException e) {}
        return result;
    }
    //aggiunto filtro che non fa vedere quelle per le quali c'è già produzione
    @Override
    public ObservableList<RigheDDT> findAll() {
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT riga_ddt.Riga, riga_ddt.Commessa, riga_ddt.CodArticolo, riga_ddt.Qtà, Prezzo, RAL, DataConsegna, riga_ddt.QtàEvasa, riga_ddt.Evaso, NumeroDDT, AnnoDDT, SerieDDT, riga_ddt.tipoDDT, ri.Descr FROM " + "riga_ddt " +  "LEFT JOIN articoli ar on riga_ddt.CodArticolo = ar.CodArticolo"
            		+ " LEFT JOIN ricette ri on ar.CodRicetta = ri.CodRicetta" + " LEFT JOIN ordini_produzione op on riga_ddt.Commessa = op.Commessa"+
            		" WHERE riga_ddt.Commessa NOT IN (SELECT op.Commessa FROM riga_ddt LEFT JOIN articoli ar on riga_ddt.CodArticolo = ar.CodArticolo "
            		+ "LEFT JOIN ricette ri on ar.CodRicetta = ri.CodRicetta INNER JOIN ordini_produzione op on riga_ddt.Commessa = op.Commessa)" );
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public ObservableList<RigheDDT> findByPrimaryKey(final int numero) {
        final String query = "SELECT Riga, Commessa, riga_ddt.CodArticolo, Qtà, Prezzo, RAL, DataConsegna, QtàEvasa, Evaso, NumeroDDT, AnnoDDT, SerieDDT, tipoDDT, ri.Descr FROM " + "riga_ddt " +  "LEFT JOIN articoli ar on riga_ddt.CodArticolo = ar.CodArticolo"
        		+ " LEFT JOIN ricette ri on ar.CodRicetta = ri.CodRicetta" + " WHERE NumeroDDT = ?" + " order by Riga";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, numero);
            final ResultSet resultSet = statement.executeQuery();
            return readQueryFromResultSet(resultSet);
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }  
    
	public Integer searchNrigheDDTArrivo(final int numero, final int anno, final String Serie) {
		final String query = "select count(Riga) from riga_ddt\r\n"
				+ "where NumeroDDT = ? and AnnoDDT = ? and SerieDDT = ? and TipoDDT = 'A'";
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setInt(1, numero);
			statement.setInt(2, anno);
			statement.setString(3, Serie);
			final ResultSet resultSet = statement.executeQuery();
			int ris=0;
			while(resultSet.next())
			{
				ris =resultSet.getInt("count(Riga)");
			}
			return ris;
		} catch (final SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public Double searchPrezzo(final String commessa) {
		final String query = "select Prezzo from riga_ddt"
				+ " where Commessa = ?";
				try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
					statement.setString(1, commessa);
					final ResultSet resultSet = statement.executeQuery();
					Double ris=0.0;
					while(resultSet.next())
					{
						ris =resultSet.getDouble("Prezzo");
					}
					return ris;
				} catch (final SQLException e) {
					throw new IllegalStateException(e);
				}
			}
	
	public Integer searchNrigheDDTUscita(final int numero, final int anno, final String Serie) {
		final String query = "select count(Riga) from riga_ddt\r\n"
				+ "where NumeroDDT = ? and AnnoDDT = ? and SerieDDT = ? and TipoDDT = 'U'";
		try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
			statement.setInt(1, numero);
			statement.setInt(2, anno);
			statement.setString(3, Serie);
			final ResultSet resultSet = statement.executeQuery();
			int ris=1;
			while(resultSet.next())
			{
				ris =resultSet.getInt("count(Riga)");
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


	public boolean updateOrdProd(final Double qtà, final String commessa, final int fase) {
	final String query = "update ordini_produzione op left join riga_ddt on op.Commessa = riga_ddt.Commessa set op.QtàPronta = op.QtàPronta-?,"
			+ " op.QtàEvasa = op.QtàEvasa+?, riga_ddt.QtàEvasa = riga_ddt.QtàEvasa+?" + " where op.Commessa =? and op.Fase =?";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDouble(1, qtà);
            statement.setDouble(2, qtà);
            statement.setDouble(3, qtà);
            statement.setString(4, commessa );
            statement.setInt(5, fase);
            return statement.executeUpdate() > 0;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

	@Override
	public boolean update(RigheDDT updatedValue) {
		// TODO Auto-generated method stub
		return false;
	}


    
    @Override
    public boolean save(final RigheDDT rddt) {
        final String querys = "INSERT INTO " + TABLE_NAME + "(Commessa, Riga, Qtà, Prezzo, RAL, DataConsegna, QtàEvasa, Evaso, CodArticolo, NumeroDDT, AnnoDDT, SerieDDT, tipoDDT)"
        		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(querys)) {
            statement.setString(1, rddt.getCommessa());
            statement.setInt(2, rddt.getRiga());
            statement.setDouble(3, rddt.getQtà());
            statement.setDouble(4, rddt.getPrezzo());
            statement.setString(5, rddt.getRal());
            statement.setDate(6, rddt.getDataConsegna());
            statement.setDouble(7, rddt.getQtàEvasa());
            statement.setString(8, rddt.getEvaso());
            statement.setString(9, rddt.getCodArticolo());
            statement.setInt(10, rddt.getNumeroDDT());
            statement.setInt(11, rddt.getAnnoDDT());
            statement.setString(12, rddt.getSerieDDT());
            statement.setString(13, rddt.getTipoDDT());
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
    
    */
}
