package unibo.color3000;

import java.sql.Date;
import java.util.Objects;


public class OrdiniProd {

	private final String centro;
	private final String commessa;
	private final String cliente;
	private final Integer fase;
	private final double qtà;
	private final double qtàpr;
	private final double qtàev;
	private final String ev;
	private final java.sql.Date datascad;
	private final String stato;
	private final String idRAL;
	private final Integer nbadge;
	private final String codArticolo;
	private final Integer rigaDDT;
	
	public OrdiniProd(String centro, String commessa, String cliente, Integer fase, double qtà, double qtàpr, double qtàev,
			String ev, java.sql.Date datascad, String stato, String idRAL, Integer nbadge, String codArticolo, Integer rigaDDT) {

		this.centro = centro;
		this.commessa = commessa;
		this.cliente = cliente;
		this.fase = fase;
		this.qtà = qtà;
		this.qtàpr = qtàpr;
		this.qtàev = qtàev;
		this.ev = ev;
		this.datascad = datascad;
		this.stato = stato;
		this.idRAL = idRAL;
		this.nbadge = nbadge;
		this.codArticolo = codArticolo;
		this.rigaDDT = rigaDDT;
	}

	public String getCentro() {
		return centro;
	}

	public String getCommessa() {
		return commessa;
	}

	public String getCliente() {
		return cliente;
	}

	public Integer getFase() {
		return fase;
	}

	public double getQtà() {
		return qtà;
	}

	public double getQtàpr() {
		return qtàpr;
	}

	public double getQtàev() {
		return qtàev;
	}

	public String getEv() {
		return ev;
	}

	public java.sql.Date getDatascad() {
		return datascad;
	}

	public String getStato() {
		return stato;
	}

	public String getIdRAL() {
		return idRAL;
	}

	public Integer getNbadge() {
		return nbadge;
	}

	public String getCodArticolo() {
		return codArticolo;
	}

	public Integer getRigaDDT() {
		return rigaDDT;
	}

	@Override
	public String toString() {
		return "OrdiniProd [centro=" + centro + ", commessa=" + commessa + ", cliente=" + cliente + ", fase=" + fase
				+ ", qtà=" + qtà + ", qtàpr=" + qtàpr + ", qtàev=" + qtàev + ", ev=" + ev + ", datascad=" + datascad
				+ ", stato=" + stato + ", idRAL=" + idRAL + ", nbadge=" + nbadge + ", codArticolo=" + codArticolo
				+ ", rigaDDT=" + rigaDDT + "]";
	}


}


