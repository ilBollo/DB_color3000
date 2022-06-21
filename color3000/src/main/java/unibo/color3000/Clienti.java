package unibo.color3000;

import java.util.Objects;


public class Clienti {
private final String CodCliente ;
private final String PartitaIVA;
private final String Denominazione;
private final String Indirizzo;
private final Integer Telefono;
private final String Mail;
private final String Nazionalità;

public Clienti(final String CodCliente,final String PartitaIVA, final String Denominazione, final String Indirizzo, final Integer Telefono,final String Mail,final String Nazionalità) {
	this.CodCliente = CodCliente;
	this.PartitaIVA = Objects.requireNonNull(PartitaIVA);
	this.Denominazione = Objects.requireNonNull(Denominazione);
	this.Indirizzo = Objects.requireNonNull(Indirizzo);
	this.Telefono = Objects.requireNonNull(Telefono);
	this.Mail = Objects.requireNonNull(Mail);
	this.Nazionalità = Objects.requireNonNull(Nazionalità);
	
}

public String getCodCliente() {
	return CodCliente;
}

public String getPartitaIVA() {
	return PartitaIVA;
}

public String getDenominazione() {
	return Denominazione;
}

public String getIndirizzo() {
	return Indirizzo;
}

public Integer getTelefono() {
	return Telefono;
}

public String getMail() {
	return Mail;
}

public String getNazionalità() {
	return Nazionalità;
}




}


