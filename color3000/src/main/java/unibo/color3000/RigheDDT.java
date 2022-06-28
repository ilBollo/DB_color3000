package unibo.color3000;


public class RigheDDT {

	private String commessa;
	private int riga;
	private double qtà;
	private double prezzo;
	private String ral;
	private java.sql.Date dataConsegna;
	private double qtàEvasa;
	private String evaso;
	private String codArticolo;
	private int numeroDDT;
	private int annoDDT;
	private String serieDDT;
	private String tipoDDT;
	private String descRicetta;

	public RigheDDT(String commessa, int riga, double qtà, double prezzo, String ral, java.sql.Date dataConsegna,
		double qtàEvasa, String evaso, String codArticolo, int numeroDDT, int annoDDT, String serieDDT,
		String tipoDDT, String descrRicetta) {
		this.commessa = commessa;
		this.riga = riga;
		this.qtà = qtà;
		this.prezzo = prezzo;
		this.ral = ral;
		this.dataConsegna = dataConsegna;
		this.qtàEvasa = qtàEvasa;
		this.evaso = evaso;
		this.codArticolo = codArticolo;
		this.numeroDDT = numeroDDT;
		this.annoDDT = annoDDT;
		this.serieDDT = serieDDT;
		this.tipoDDT = tipoDDT;
		this.descRicetta = descrRicetta;
	}

	public String getCommessa() {
		return commessa;
	}

	public void setCommessa(String commessa) {
		this.commessa = commessa;
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public double getQtà() {
		return qtà;
	}

	public void setQtà(double qtà) {
		this.qtà = qtà;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getRal() {
		return ral;
	}

	public void setRal(String ral) {
		this.ral = ral;
	}

	public java.sql.Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(java.sql.Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public double getQtàEvasa() {
		return qtàEvasa;
	}

	public void setQtàEvasa(double qtàEvasa) {
		this.qtàEvasa = qtàEvasa;
	}

	public String getEvaso() {
		return evaso;
	}

	public void setEvaso(String evaso) {
		this.evaso = evaso;
	}

	public String getCodArticolo() {
		return codArticolo;
	}

	public void setCodArticolo(String codArticolo) {
		this.codArticolo = codArticolo;
	}

	public int getNumeroDDT() {
		return numeroDDT;
	}

	public void setNumeroDDT(int numeroDDT) {
		this.numeroDDT = numeroDDT;
	}

	public int getAnnoDDT() {
		return annoDDT;
	}

	public void setAnnoDDT(int annoDDT) {
		this.annoDDT = annoDDT;
	}

	public String getSerieDDT() {
		return serieDDT;
	}

	public void setSerieDDT(String serieDDT) {
		this.serieDDT = serieDDT;
	}

	public String getTipoDDT() {
		return tipoDDT;
	}

	public void setTipoDDT(String tipoDDT) {
		this.tipoDDT = tipoDDT;
	}

	public String getDescRicetta() {
		return descRicetta;
	}

	public void setDescRicetta(String descRicetta) {
		this.descRicetta = descRicetta;
	}

	@Override
	public String toString() {
		return "RigheDDT [commessa=" + commessa + ", riga=" + riga + ", qtà=" + qtà + ", prezzo=" + prezzo + ", ral="
				+ ral + ", dataConsegna=" + dataConsegna + ", qtàEvasa=" + qtàEvasa + ", evaso=" + evaso
				+ ", codArticolo=" + codArticolo + ", numeroDDT=" + numeroDDT + ", annoDDT=" + annoDDT + ", serieDDT="
				+ serieDDT + ", tipoDDT=" + tipoDDT + ", descRicetta=" + descRicetta + "]";
	}
	

}	
	
