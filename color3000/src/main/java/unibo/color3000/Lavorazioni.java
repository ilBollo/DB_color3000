package unibo.color3000;

import java.sql.Date;

public class Lavorazioni {
	private final int numeroLav;
	private final java.sql.Date dataInizio;
	private final java.sql.Date dataFine;
	private final double qtà;
	private final String stato;
	private final int nbadge;
	private final String commessa;
	private final int fase;

	public Lavorazioni(int numeroLav, Date dataInizio, Date dataFine, double qtà, String stato, int nbadge, String commessa, int fase) {
		this.numeroLav = numeroLav;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.qtà = qtà;
		this.stato = stato;
		this.nbadge = nbadge;
		this.commessa = commessa;
		this.fase = fase;
	}

	public int getNumeroLav() {
		return numeroLav;
	}

	public java.sql.Date getDataInizio() {
		return dataInizio;
	}

	public java.sql.Date getDataFine() {
		return dataFine;
	}

	public double getQtà() {
		return qtà;
	}

	public String getStato() {
		return stato;
	}

	public int getNbadge() {
		return nbadge;
	}

	public String getCommessa() {
		return commessa;
	}

	public int getFase() {
		return fase;
	}

	@Override
	public String toString() {
		return "Lavorazioni [numeroLav=" + numeroLav + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", qtà=" + qtà + ", stato=" + stato + ", nbadge=" + nbadge + ", commessa=" + commessa + ", fase="
				+ fase + "]";
	}

}
