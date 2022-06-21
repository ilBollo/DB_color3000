package unibo.color3000;

import java.util.Date;

public class Ddt{
	private int numeroDDT;
	private int anno;
	private String serie;
	private String tipo;
	private java.sql.Date dataDDT;
	private String codCliente;
	private String denominazione;
	private String evaso;
	private int nbadge;

public Ddt(int numeroDDT, int anno, String serie, String tipo, java.sql.Date dataDDT, String codCliente, String denominazione, int nbadge) {
		this.numeroDDT = numeroDDT;
		this.anno = anno;
		this.serie = serie;
		this.tipo = tipo;
		this.dataDDT = dataDDT;
		this.codCliente = codCliente;
		this.denominazione = denominazione;
		this.nbadge = nbadge;
	}

public int getNumeroDDT() {
	return numeroDDT;
}

public int getAnno() {
	return anno;
}

public String getSerie() {
	return serie;
}

public String getTipo() {
	return tipo;
}

public java.sql.Date getDataDDT() {
	return dataDDT;
}

public String getCodCliente() {
	return codCliente;
}

public String getDenominazione() {
	return denominazione;
}

public String getEvaso() {
	return evaso;
}

public int getNbadge() {
	return nbadge;
}

@Override
public String toString() {
	return "Ddt [numeroDDT=" + numeroDDT + ", anno=" + anno + ", serie=" + serie + ", tipo=" + tipo + ", dataDDT="
			+ dataDDT + ", codCliente=" + codCliente + ", denominazione=" + denominazione + ", evaso=" + evaso
			+ ", nbadge=" + nbadge + "]";
}





}