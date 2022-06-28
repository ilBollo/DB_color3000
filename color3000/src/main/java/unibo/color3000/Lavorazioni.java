package unibo.color3000;

public class Lavorazioni {
	private final java.util.Date dataInizio;
	private final java.util.Date dataFine;
	private final double qtà;
	private final String stato;
	private final int nbadge;
	private final String commessa;
	private final int fase;

	public Lavorazioni(java.util.Date dataInizio, java.util.Date dataFine, double qtà, String stato, int nbadge, String commessa, int fase) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.qtà = qtà;
		this.stato = stato;
		this.nbadge = nbadge;
		this.commessa = commessa;
		this.fase = fase;
	}


	public java.util.Date getDataInizio() {
		return dataInizio;
	}

	public java.util.Date getDataFine() {
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
		return "Lavorazioni [dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", qtà=" + qtà + ", stato=" + stato + ", nbadge=" + nbadge + ", commessa=" + commessa + ", fase="
				+ fase + "]";
	}

}
