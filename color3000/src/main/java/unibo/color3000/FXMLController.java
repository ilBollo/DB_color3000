package unibo.color3000;

import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class FXMLController implements Initializable {

	private final ConnectionProvider connection = new ConnectionProvider("root", "Rimini88", "color3000");
	private final Connection con = connection.getMySQLConnection();
	ObservableList<Clienti> ossClie;
	private final QueryClienti queryCl = new QueryClienti(con);
	ObservableList<OrdiniProd> ossProd;
	private final QueryOrdiniProd queryOrdProd = new QueryOrdiniProd(con);
	ObservableList<Ddt> ossDdt;
	private final QueryDDT queryDdt = new QueryDDT(con);
	ObservableList<RigheDDT> ossrigheDdt;
	private final QueryrigheDDT queryrigheDdt = new QueryrigheDDT(con);
	private final QueryLavorazioni queryLavorazioni = new QueryLavorazioni(con);
	private final QueryArticoli queryArticoli = new QueryArticoli(con);
	ObservableList<Articoli> ossArticoli;

	private int index=-1;
	int riga =1;

	//cliente
	@FXML
	private TextField txtCliente;

	@FXML
	private TableColumn<Clienti,String> codCliente;

	@FXML
	private TableColumn<Clienti,String> denominazione;

	@FXML
	private TableColumn<Clienti,String> partitaIVA;

	@FXML
	private TableView<Clienti> cliTab;

	@FXML
	private TableColumn<Clienti,String> fattClie;

	@FXML
	private TableColumn<Clienti, Double> fattFatt;

	@FXML
	private TableView<Clienti> fatTab;

	@FXML
	void handleFatt(ActionEvent event) {
		// ossClie =  queryCl.findAllPartenze();
		//ddtE_num.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("numeroDDT"));
		// ddtE_anno.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("anno"));

		fatTab.setItems(ossClie);
	}

	@FXML
	void handleControlla(ActionEvent event) {
		try {
			String search = txtCliente.getText();
			ossClie = queryCl.findByPrimaryKey(search);
			codCliente.setCellValueFactory(new PropertyValueFactory<Clienti, String>("CodCliente"));
			partitaIVA.setCellValueFactory(new PropertyValueFactory<Clienti, String>("PartitaIVA"));
			denominazione.setCellValueFactory(new PropertyValueFactory<Clienti, String>("Denominazione"));

			cliTab.setItems(ossClie);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}

	//articoli
	@FXML
	private TextField insArtcod;

	@FXML
	private TextField insArtdescr;

	@FXML
	private TextField insArtpeso;

	@FXML
	private TextField insArtricetta;

	@FXML
	private TableColumn<Articoli, String> codArt_cod;

	@FXML
	private TableColumn<Articoli, String> codArt_codRicetta;

	@FXML
	private TableColumn<Articoli, String> codArt_descr;

	@FXML
	private TableColumn<Articoli, Double> codArt_peso;

	@FXML
	private TableView<Articoli> tabArt;


	@FXML
	void insertArt(ActionEvent event) throws NumberFormatException, ParseException {
		try {
			Articoli nuovoArticolo = new Articoli(insArtcod.getText(), insArtdescr.getText(), Double.parseDouble(insArtpeso.getText()), insArtricetta.getText()); 

			if(queryArticoli.save(nuovoArticolo)) {
				JOptionPane.showMessageDialog(null, "inserito");
				insArtcod.setText(null);
				insArtpeso.setText(null);
				insArtricetta.setText(null);
				insArtdescr.setText(null);
				ossArticoli = queryArticoli.findAll();
				tabArt.setItems(ossArticoli);
			}
			else {
				JOptionPane.showMessageDialog(null, "non inserito");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}
	@FXML
	void deleteArt(ActionEvent event) {
		try {
			if(queryArticoli.deleteArt(cancArtcod.getText())) {
				JOptionPane.showMessageDialog(null, "cancellato");
				cancArtcod.setText(null);
				ossArticoli = queryArticoli.findAll();
				tabArt.setItems(ossArticoli);
			}
			else {
				JOptionPane.showMessageDialog(null, "non cancellato");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}

	@FXML
	private TextField cancArtcod;



	//ddtRicevuti

	@FXML
	private TableColumn<Ddt, String> ddtR_CodClie;

	@FXML
	private TableColumn<Ddt, Integer> ddtR_anno;

	@FXML
	private TableColumn<Ddt, java.util.Date> ddtR_data;

	@FXML
	private TableColumn<Ddt, String> ddtR_descrClie;

	@FXML
	private TableColumn<Ddt, String> ddtR_evaso;

	@FXML
	private TableColumn<Ddt, Integer> ddtR_num;

	@FXML
	private TableColumn<Ddt, String> ddtR_serie;

	@FXML
	private TableView<Ddt> ddtTab;


	//righe ddt
	@FXML
	private TableColumn<RigheDDT, Integer> rddtR_riga;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_codArt;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_commessa;

	@FXML
	private TableColumn<RigheDDT, Double> rddtR_qtà;

	@FXML
	private TableColumn<RigheDDT, Double> rddtR_prezzo;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_ral;

	@FXML
	private TableColumn<RigheDDT, java.util.Date> rddtR_datacons;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_ricetta;


	@FXML
	private TableView<RigheDDT> ddtRtab;


	int risultato;

	@FXML
	void mouseddtProd(MouseEvent event) {

		index =ddtTab.getSelectionModel().getSelectedIndex();    				
		risultato = ddtR_num.getCellObservableValue(index).getValue();  	
		ossrigheDdt = queryrigheDdt.findByPrimaryKey(risultato);
		rddtR_riga.setCellValueFactory(new PropertyValueFactory<RigheDDT, Integer>("riga"));
		rddtR_commessa.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("commessa"));
		rddtR_qtà.setCellValueFactory(new PropertyValueFactory<RigheDDT, Double>("qtà"));
		rddtR_prezzo.setCellValueFactory(new PropertyValueFactory<RigheDDT, Double>("prezzo"));
		rddtR_codArt.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("codArticolo"));
		rddtR_ral.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("ral"));
		rddtR_datacons.setCellValueFactory(new PropertyValueFactory<RigheDDT, java.util.Date>("dataConsegna"));
		rddtR_ricetta.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("descRicetta"));

		ddtRtab.setItems(ossrigheDdt);

	}

	//inserimento DDT

	@FXML
	private AnchorPane insDDT;

	@FXML
	private TextField insDDTanno;

	@FXML
	private TextField insDDTbadge;

	@FXML
	private TextField insDDTcodcliente;

	@FXML
	private DatePicker insDDTdata;

	@FXML
	private TextField insDDTnum;

	@FXML
	private TextField insDDTserie;


	@FXML
	void insertDDT(ActionEvent event) throws NumberFormatException, ParseException {
		try {
			Ddt nuovoDdt = new Ddt(Integer.parseInt(insDDTnum.getText()),Integer.parseInt(insDDTanno.getText()), insDDTserie.getText(), "a", java.sql.Date.valueOf(insDDTdata.getValue()), insDDTcodcliente.getText(), "ao", Integer.parseInt(insDDTbadge.getText())); 

			if(queryDdt.save(nuovoDdt)) {
				JOptionPane.showMessageDialog(null, "inserito");

				insDDTanno.setText(null);
				insDDTbadge.setText(null);
				insDDTcodcliente.setText(null);
				insDDTdata.setValue(null);  
				insDDTnum.setText(null);
				insDDTserie.setText(null);
				ossDdt =  queryDdt.findAllArrivi();
				ddtTab.setItems(ossDdt);

			}
			else {
				JOptionPane.showMessageDialog(null, "non inserito");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}

	//insRigheDDT

	@FXML
	private TextField insRDDTcodart;

	@FXML
	private DatePicker insRDDTdataconsegna;

	@FXML
	private TextField insRDDTprezzo;

	@FXML
	private TextField insRDDTqtà;

	@FXML
	private TextField insRDDTral;

	@FXML
	void insertRddt(ActionEvent event) {
		try {	
			riga = queryrigheDdt.searchNrigheDDTArrivo(ddtR_num.getCellData(index), ddtR_anno.getCellData(index), ddtR_serie.getCellData(index))+1;
			RigheDDT nuovariga = new RigheDDT("O"+ddtR_num.getCellData(index)+"R"+riga, riga, Double.parseDouble(insRDDTqtà.getText()), Double.parseDouble(insRDDTprezzo.getText()),
					insRDDTral.getText(), java.sql.Date.valueOf(insRDDTdataconsegna.getValue()), 0.00, String.valueOf('N'), insRDDTcodart.getText(), ddtR_num.getCellData(index), ddtR_anno.getCellData(index), ddtR_serie.getCellData(index), "A", "descr" );

			if(queryrigheDdt.save(nuovariga)) {
				JOptionPane.showMessageDialog(null, "inserito");
				insRDDTcodart.setText(null);
				insRDDTqtà.setText(null);
				insRDDTral.setText(null);
				insRDDTprezzo.setText(null);			
				insRDDTdataconsegna.setValue(null);  
				ossrigheDdt = queryrigheDdt.findAll();
				ddtRtab1.setItems(ossrigheDdt);
			} else {
				JOptionPane.showMessageDialog(null, "non inserito");
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}
	//scelgo riga ddt da cancellare
	@FXML
	void mouseOrdiniProd(MouseEvent event) {

	}

	//inserimento ordini di produzione
	//campi con righe ddt
	@FXML
	private TableColumn<RigheDDT, String> rddtR_ral1;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_codArt1;

	@FXML
	private TableColumn<RigheDDT, Double> rddtR_qtà1;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_commessa1;

	@FXML
	private TableColumn<RigheDDT, java.util.Date> rddtR_datacons1;

	@FXML
	private TableColumn<RigheDDT, String> rddtR_ricetta1;

	@FXML
	private TableColumn<RigheDDT, Integer> rddtR_riga1;

	@FXML
	private TableView<RigheDDT> ddtRtab1;

	@FXML
	private AnchorPane insOrdP;

	@FXML
	private TextField insOrdPbadge;

	@FXML
	private DatePicker insOrdPdatascad;

	int indexprod=-1;
	@FXML
	void mousegerProd(MouseEvent event) {
		indexprod =ddtRtab1.getSelectionModel().getSelectedIndex();    				
	}

	int fase=0;
	String centro;
	String cliente; 

	@FXML
	void insertOrdP(ActionEvent event) {
		while (fase>=0 && fase!=-1 && indexprod!=-1) {
			fase = queryOrdProd.searchFase(rddtR_codArt1.getCellData(indexprod), fase);
			if (fase!=-1) {
				centro = queryOrdProd.searchCentro(rddtR_codArt1.getCellData(indexprod), 1);
				cliente = queryOrdProd.searchCliente(rddtR_commessa1.getCellData(indexprod));
				OrdiniProd nuovaprod = new OrdiniProd(centro, rddtR_commessa1.getCellData(indexprod), cliente, fase, rddtR_qtà1.getCellData(indexprod), 0, 0,"N", java.sql.Date.valueOf(insOrdPdatascad.getValue()), "N", rddtR_ral1.getCellData(indexprod), Integer.parseInt(insOrdPbadge.getText()),rddtR_codArt1.getCellData(indexprod), rddtR_riga1.getCellData(indexprod));
				if(queryOrdProd.save(nuovaprod)) {
					JOptionPane.showMessageDialog(null, "inserito ordine prod per fase "+fase);}
				else {
					JOptionPane.showMessageDialog(null, "non inserito");
					fase =-1;
				}
			}
		}
		fase =0;
		ossrigheDdt = queryrigheDdt.findAll();
		ddtRtab1.setItems(ossrigheDdt);

	}

	//produzione

	@FXML
	private TableView<OrdiniProd> proTab;

	@FXML
	private TableColumn<OrdiniProd, String> op_centro;

	@FXML
	private TableColumn<OrdiniProd, String> op_ral;

	@FXML
	private TableColumn<OrdiniProd, String> op_cliente;

	@FXML
	private TableColumn<OrdiniProd, String> op_commessa;

	@FXML
	private TableColumn<OrdiniProd, String> op_stato;

	@FXML
	private TableColumn<OrdiniProd, String> op_codice;

	@FXML
	private TableColumn<OrdiniProd, Integer> op_fase;

	@FXML
	private TableColumn<OrdiniProd, java.sql.Date> op_datascad;

	@FXML
	private TableColumn<OrdiniProd, Double> op_qtaevasa;

	@FXML
	private TableColumn<OrdiniProd, Double> op_qtapronta;


	@FXML
	private TableColumn<OrdiniProd, Integer> op_qtares;


	@FXML
	private TextField ss_badge;

	@FXML
	private TextField ss_qtàvers;

	@FXML
	private AnchorPane startstop;




	@FXML
	void handleProd(ActionEvent event) {

		ossProd = queryOrdProd.findAll();
		op_centro.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("centro"));
		op_ral.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("IdRAL"));
		op_cliente.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("cliente"));
		op_commessa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("commessa") );
		op_stato.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("stato") );
		op_fase.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("fase"));
		op_codice.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("codArticolo"));
		op_qtares.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("qtà"));
		op_datascad.setCellValueFactory(new PropertyValueFactory<OrdiniProd,java.sql.Date>("datascad"));
		op_qtaevasa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàev"));
		op_qtapronta.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàpr"));
		proTab.setItems(ossProd);
	}

	int indexlav=-1;
	@FXML
	void mouse_startstop(MouseEvent event) {
		indexlav =proTab.getSelectionModel().getSelectedIndex();
	}


	@FXML
	void ss_start(ActionEvent event) {
		final Date CreatedDate= new Date(System.currentTimeMillis());
		Lavorazioni nuovalav = new Lavorazioni(CreatedDate, null, 0, "A", Integer.parseInt(ss_badge.getText()), op_commessa.getCellData(indexlav), op_fase.getCellData(indexlav));
		if ((queryLavorazioni.save(nuovalav))&& (queryOrdProd.updateStart(op_commessa.getCellData(indexlav), op_fase.getCellData(indexlav)))){
			JOptionPane.showMessageDialog(null, "produzione avviata");
			ossProd = queryOrdProd.findAll();
			proTab.setItems(ossProd);
		}
		else {
			JOptionPane.showMessageDialog(null, "non avviata");
		}
	}

	@FXML
	void ss_stop(ActionEvent event) {
		if (queryOrdProd.updateStop(op_commessa.getCellData(indexlav), op_fase.getCellData(indexlav))){
			JOptionPane.showMessageDialog(null, "produzione interrotta");
			ossProd = queryOrdProd.findAll();
			proTab.setItems(ossProd);
		}
		else {
			JOptionPane.showMessageDialog(null, "non interrotta");
		}
	}

	@FXML
	void ss_versa(ActionEvent event) {
		if (Integer.parseInt(ss_qtàvers.getText())>0 && queryOrdProd.updateVersa(op_commessa.getCellData(indexlav), op_fase.getCellData(indexlav), Integer.parseInt(ss_qtàvers.getText()))){
			JOptionPane.showMessageDialog(null, "produzione versata");
			ossProd = queryOrdProd.findAll();
			proTab.setItems(ossProd);	
			ossProd = queryOrdProd.findPronti();
			ddtEtab.setItems(ossProd);


		}
		else {
			JOptionPane.showMessageDialog(null, "non versata");
		}
	}

	//ins ddt emesso

	@FXML
	private TableColumn<Ddt, String> ddtE_CodClie;

	@FXML
	private TableColumn<Ddt, Integer> ddtE_anno;

	@FXML
	private TableColumn<Ddt, java.util.Date> ddtE_data;

	@FXML
	private TableColumn<Ddt, String> ddtE_descrClie;

	@FXML
	private TableColumn<Ddt, Integer> ddtE_num;

	@FXML
	private TableColumn<Ddt, String> ddtE_serie;

	@FXML
	private TableView<Ddt> ddtTabE;


	@FXML
	private AnchorPane insDDTE;

	@FXML
	private TableColumn<OrdiniProd, String> insDDTE_cliente;

	@FXML
	private TableColumn<OrdiniProd, String> insDDTE_codice;

	@FXML
	private TableColumn<OrdiniProd, String> insDDTE_commessa;

	@FXML
	private TableColumn<OrdiniProd, Integer> insDDTE_fase;

	@FXML
	private TableColumn<OrdiniProd, java.sql.Date> insDDTE_datascad;

	@FXML
	private TableColumn<OrdiniProd, Double> insDDTE_qtapronta;

	@FXML
	private TableColumn<OrdiniProd, String> insDDTE_ral;

	@FXML
	private TextField insDDTEanno;

	@FXML
	private TextField insDDTEbadge;

	@FXML
	private TextField insDDTEcodcliente;

	@FXML
	private DatePicker insDDTEdata;

	@FXML
	private TextField insDDTEnum;

	@FXML
	private TextField insDDTEserie;

	@FXML
	private TableView<OrdiniProd> ddtEtab;

	@FXML
	void insertDDTE(ActionEvent event) throws NumberFormatException, ParseException {
		try {
			Ddt nuovoDdt = new Ddt(Integer.parseInt(insDDTEnum.getText()),Integer.parseInt(insDDTEanno.getText()), insDDTEserie.getText(), "u", java.sql.Date.valueOf(insDDTEdata.getValue()), insDDTEcodcliente.getText(), "ao", Integer.parseInt(insDDTEbadge.getText())); 

			if(queryDdt.save(nuovoDdt)) {
				JOptionPane.showMessageDialog(null, "inserito");

				insDDTEanno.setText(null);
				insDDTEbadge.setText(null);
				insDDTEcodcliente.setText(null);
				insDDTEdata.setValue(null);  
				insDDTEnum.setText(null);
				insDDTEserie.setText(null);
				ossDdt =  queryDdt.findAllPartenze();
				ddtTabE.setItems(ossDdt);
			}
			else {
				JOptionPane.showMessageDialog(null, "non inserito");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}


	@FXML
	void insertRigaddtE(ActionEvent event) {
		try {

			index =ddtEtab.getSelectionModel().getSelectedIndex();    	
			final int indexddt = ddtTabE.getSelectionModel().getSelectedIndex();
			riga = queryrigheDdt.searchNrigheDDTUscita(ddtE_num.getCellData(indexddt), ddtE_anno.getCellData(indexddt), ddtE_serie.getCellData(indexddt))+1;

			final double prezzo = queryrigheDdt.searchPrezzo(insDDTE_commessa.getCellData(index));

			RigheDDT nuovariga = new RigheDDT(insDDTE_commessa.getCellData(index), riga, insDDTE_qtapronta.getCellData(index), prezzo,
					insDDTE_ral.getCellData(index), null, 0, String.valueOf('n'), insDDTE_codice.getCellData(index), ddtE_num.getCellData(indexddt), ddtE_anno.getCellData(indexddt), ddtE_serie.getCellData(indexddt), "U", "descr" );
			System.out.print(nuovariga);
			if(queryrigheDdt.save(nuovariga)){//&&queryrigheDdt.updateOrdProd(insDDTE_qtapronta.getCellData(index), insDDTE_commessa.getCellData(index), insDDTE_fase.getCellData(index))) {
				JOptionPane.showMessageDialog(null, "inserito");
//				ossrigheDdt = queryrigheDdt.findAll();
	//			ddtRtab1.setItems(ossrigheDdt);

			} else {
				JOptionPane.showMessageDialog(null, "non inserito");
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "dati non corretti");
		}
	}

	@FXML
	void mouse_ddtem(MouseEvent event) {

	}

	@FXML
	void handleDDTe(ActionEvent event) {
		ossProd = queryOrdProd.findPronti();
		insDDTE_ral.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("IdRAL"));
		insDDTE_cliente.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("cliente"));
		insDDTE_commessa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("commessa") );
		insDDTE_codice.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("codArticolo"));
		insDDTE_fase.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("fase"));
		insDDTE_datascad.setCellValueFactory(new PropertyValueFactory<OrdiniProd,java.sql.Date>("datascad"));
		insDDTE_qtapronta.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàpr"));
		ddtEtab.setItems(ossProd);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//inizializzo mascheraVisualizzaDdtRicevuti
		ossDdt =  queryDdt.findAllArrivi();
		ddtR_num.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("numeroDDT"));
		ddtR_anno.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("anno"));
		ddtR_serie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("serie"));
		ddtR_CodClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("codCliente"));
		ddtR_descrClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("denominazione"));
		ddtR_data.setCellValueFactory(new PropertyValueFactory<Ddt, java.util.Date>("dataDDT"));
		ddtTab.setItems(ossDdt);

		//inizz maschera Visual ord prod

		ossrigheDdt = queryrigheDdt.findAll();
		rddtR_commessa1.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("commessa"));
		rddtR_qtà1.setCellValueFactory(new PropertyValueFactory<RigheDDT, Double>("qtà"));
		rddtR_codArt1.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("codArticolo"));
		rddtR_ral1.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("ral"));
		rddtR_datacons1.setCellValueFactory(new PropertyValueFactory<RigheDDT, java.util.Date>("dataConsegna"));
		rddtR_ricetta1.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("descRicetta"));
		rddtR_riga1.setCellValueFactory(new PropertyValueFactory<RigheDDT, Integer>("riga"));
		ddtRtab1.setItems(ossrigheDdt);

		//iniz maschera DDTemessi
		ossDdt =  queryDdt.findAllPartenze();
		ddtE_num.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("numeroDDT"));
		ddtE_anno.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("anno"));
		ddtE_serie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("serie"));
		ddtE_CodClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("codCliente"));
		ddtE_descrClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("denominazione"));
		ddtE_data.setCellValueFactory(new PropertyValueFactory<Ddt, java.util.Date>("dataDDT"));
		ddtTabE.setItems(ossDdt);
		//iniz maschera Articoli
		ossArticoli = queryArticoli.findAll();
		codArt_cod.setCellValueFactory(new PropertyValueFactory<Articoli, String>("codArticolo"));
		codArt_descr.setCellValueFactory(new PropertyValueFactory<Articoli, String>("descr"));
		codArt_peso.setCellValueFactory(new PropertyValueFactory<Articoli, Double>("peso"));
		codArt_codRicetta.setCellValueFactory(new PropertyValueFactory<Articoli, String>("codRicetta"));
		tabArt.setItems(ossArticoli);

		//iniz ord prod aperti
		ossProd = queryOrdProd.findAll();
		op_centro.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("centro"));
		op_ral.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("IdRAL"));
		op_cliente.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("cliente"));
		op_commessa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("commessa") );
		op_stato.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("stato") );
		op_fase.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("fase"));
		op_codice.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("codArticolo"));
		op_qtares.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("qtà"));
		op_datascad.setCellValueFactory(new PropertyValueFactory<OrdiniProd,java.sql.Date>("datascad"));
		op_qtaevasa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàev"));
		op_qtapronta.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàpr"));
		proTab.setItems(ossProd);
		//iniz ordini pronti da essere spediti
		ossProd = queryOrdProd.findPronti();
		insDDTE_ral.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("IdRAL"));
		insDDTE_cliente.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("cliente"));
		insDDTE_commessa.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("commessa") );
		insDDTE_codice.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("codArticolo"));
		insDDTE_fase.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("fase"));
		insDDTE_datascad.setCellValueFactory(new PropertyValueFactory<OrdiniProd,java.sql.Date>("datascad"));
		insDDTE_qtapronta.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Double>("qtàpr"));
		ddtEtab.setItems(ossProd);
	}

}


