package unibo.color3000;

import java.util.Objects;

import javafx.scene.control.cell.PropertyValueFactory;

public class Dipendente {
private final Integer nBadge;
private final String cod_fisc;
private final String nome;
private final String cognome;
private final String qualifica;
private final String stipendio;
private final Integer turno;

public Dipendente(final Integer nBadge,final String cod_fisc, final String nome, final String cognome, final String qualifica,final Integer turno,final String stipendio) {
	this.nBadge = nBadge;
	this.cod_fisc = Objects.requireNonNull(cod_fisc);
	this.nome = Objects.requireNonNull(nome);
	this.cognome = Objects.requireNonNull(cognome);
	this.qualifica = Objects.requireNonNull(qualifica);
	this.turno = Objects.requireNonNull(turno);
	this.stipendio = Objects.requireNonNull(stipendio);
	
}


public String getQualifica() {
	return qualifica;
}

public String getStipendio() {
	return stipendio;
}

public Integer getTurno() {
	return turno;
}

public Integer getnBadge() {
	return nBadge;
}

public String getNome() {
	return nome;
}

public String getCognome() {
	return cognome;
}


@Override
public String toString() {
	return "Dipendente [nBadge=" + nBadge + ", cod_fisc=" + cod_fisc + ", nome=" + nome + ", cognome=" + cognome
			+ ", qualifica=" + qualifica + ", stipendio=" + stipendio + ", turno=" + turno + "]";
}

public String getCod_fisc() {
	return cod_fisc;
}



}


