package unibo.color3000;

public class Articoli {
private String codArticolo;
private String descr;
private Double peso;
private String codRicetta;

public Articoli(String codArticolo, String descr, Double peso, String codRicetta) {
	this.codArticolo = codArticolo;
	this.descr = descr;
	this.peso = peso;
	this.codRicetta = codRicetta;
}

public String getCodArticolo() {
	return codArticolo;
}

public void setCodArticolo(String codArticolo) {
	this.codArticolo = codArticolo;
}

public String getDescr() {
	return descr;
}

public void setDescr(String descr) {
	this.descr = descr;
}

public Double getPeso() {
	return peso;
}

public void setPeso(Double peso) {
	this.peso = peso;
}

public String getCodRicetta() {
	return codRicetta;
}

public void setCodRicetta(String codRicetta) {
	this.codRicetta = codRicetta;
}


}
