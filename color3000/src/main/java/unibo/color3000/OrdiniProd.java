package unibo.color3000;

import java.sql.Date;
import java.util.Objects;

import javafx.scene.control.cell.PropertyValueFactory;

public class OrdiniProd {
/*	private final String fase;
	private final Integer qtàPronta;
	private final Integer qtàEvasa;
	private final String evaso;
	private final String stato;
	private final Integer nbadge;
	private final String commessa;
	private final String tipo;
	private final Integer anno;
	private final String serie;
	private final Integer riga;
/*	public OrdiniProd(String fase, String codCliente, Integer qtà, Integer qtàPronta, Integer qtàEvasa, String evaso,
			Date dataScad, String stato, String idRAL, Integer codiceCentro, Integer nbadge, String codArticolo,
			String commessa, String tipo, Integer anno, String serie, Integer numero, Integer riga) {
		this.fase = fase;
		this.qtàPronta = qtàPronta;
		this.qtàEvasa = qtàEvasa;
		this.evaso = evaso;
		this.stato = stato;
		this.codiceCentro = codiceCentro;
		this.nbadge = nbadge;
		this.commessa = commessa;
		this.tipo = tipo;
		this.anno = anno;
		this.serie = serie;
		this.riga = riga;
	}
	*/

	private final String idRAL;
	private final Integer codiceCentro;
	private final String codCliente;
	private final Integer numero;
	private final String descrRicetta;
	private final String codArticolo;
	private final Integer qtà;
	public java.sql.Date getDataScad() {
		return dataScad;
	}



	private final java.sql.Date dataScad;


	
	public OrdiniProd(String idRAL, Integer codCentro, String codCliente, Integer numero, String descrRicetta,
			String codArticolo, Integer qtà, java.sql.Date dataScad) {
		this.idRAL = idRAL;
		this.codiceCentro = codCentro;
		this.codCliente = codCliente;
		this.numero = numero;
		this.descrRicetta = descrRicetta;
		this.codArticolo = codArticolo;
		this.qtà = qtà;
		this.dataScad = dataScad;


		
	}



	public Integer getQtà() {
		return qtà;
	}



	public String getCodArticolo() {
		return codArticolo;
	}



	public String getIdRAL() {
		return idRAL;
	}



	public Integer getCodiceCentro() {
		return codiceCentro;
	}



	public String getCodCliente() {
		return codCliente;
	}



	public Integer getNumero() {
		return numero;
	}



	public String getDescrRicetta() {
		return descrRicetta;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codArticolo, codCliente, codiceCentro, descrRicetta, idRAL, numero);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdiniProd other = (OrdiniProd) obj;
		return Objects.equals(codArticolo, other.codArticolo) && Objects.equals(codCliente, other.codCliente)
				&& Objects.equals(codiceCentro, other.codiceCentro) && Objects.equals(descrRicetta, other.descrRicetta)
				&& Objects.equals(idRAL, other.idRAL) && Objects.equals(numero, other.numero);
	}






	
	


}


