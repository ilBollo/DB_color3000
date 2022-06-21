package unibo.color3000;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class FXMLController {

	private final ConnectionProvider connection = new ConnectionProvider("root", "Rimini88", "color3000");
	private final Connection con = connection.getMySQLConnection();
	private final QueryClienti q = new QueryClienti(con);
	private final QueryOrdiniProd queryOrdProd = new QueryOrdiniProd(con);
	ObservableList<Clienti> test;
	ObservableList<OrdiniProd> ossProd;
	private int index=-1;

	private final QueryDDT queryDdt = new QueryDDT(con);
	ObservableList<Ddt> ossDdt;
	
	ObservableList<RigheDDT> ossrigheDdt;
	private final QueryrigheDDT queryrigheDdt = new QueryrigheDDT(con);

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
    void handleControlla(ActionEvent event) {
        String search = txtCliente.getText();
        test = q.findByPrimaryKey(search);
        System.out.println(test);
        codCliente.setCellValueFactory(new PropertyValueFactory<Clienti, String>("CodCliente"));
        partitaIVA.setCellValueFactory(new PropertyValueFactory<Clienti, String>("PartitaIVA"));
        denominazione.setCellValueFactory(new PropertyValueFactory<Clienti, String>("Denominazione"));
      
        cliTab.setItems(test);
    }

//produzione
  
    @FXML
    private TableView<OrdiniProd> proTab;
    
    @FXML
    private TableColumn<OrdiniProd, Integer> op_centro;

    @FXML
    private TableColumn<OrdiniProd, String> op_cliente;

    @FXML
    private TableColumn<OrdiniProd, String> op_codice;
    
    @FXML
    private TableColumn<OrdiniProd, String> op_ricetta;


    @FXML
    private TableColumn<OrdiniProd, java.sql.Date> op_datascad;

    @FXML
    private TableColumn<OrdiniProd, Integer> op_ordine;

    @FXML
    private TableColumn<OrdiniProd, Integer> op_qtaevasa;

    @FXML
    private TableColumn<OrdiniProd, Integer> op_qtares;

    @FXML
    private TableColumn<OrdiniProd, String> op_ral;
    
    @FXML
    void handleProd(ActionEvent event) {
    	
    	ossProd = queryOrdProd.findAll();
    	System.out.print(ossProd);
        op_ral.setCellValueFactory(new PropertyValueFactory<OrdiniProd, String>("IdRAL"));
        op_centro.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("CodiceCentro") );
        op_cliente.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("CodCliente") );
        op_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("numero") );
        op_ricetta.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("descrRicetta"));
        op_codice.setCellValueFactory(new PropertyValueFactory<OrdiniProd,String>("codArticolo"));
        op_qtares.setCellValueFactory(new PropertyValueFactory<OrdiniProd,Integer>("qtà"));
        op_datascad.setCellValueFactory(new PropertyValueFactory<OrdiniProd,java.sql.Date>("dataScad"));
        proTab.setItems(ossProd);
    }
    
    @FXML
    void mouseOrdiniProd(MouseEvent event) {
    	index =proTab.getSelectionModel().getSelectedIndex();
    System.out.println(op_datascad.getCellObservableValue(index));
    }
    
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
    
    @FXML
    void handleDDTvisual(ActionEvent event) {
    	
    	ossDdt =  queryDdt.findAll();
    	
    	ddtR_num.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("numeroDDT"));
    	
    	ddtR_anno.setCellValueFactory(new PropertyValueFactory<Ddt, Integer>("anno"));

        ddtR_serie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("serie"));

        ddtR_CodClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("codCliente"));
        
        ddtR_descrClie.setCellValueFactory(new PropertyValueFactory<Ddt, String>("denominazione"));
        
        ddtR_data.setCellValueFactory(new PropertyValueFactory<Ddt, java.util.Date>("dataDDT"));

  
        ddtTab.setItems(ossDdt);
    	
    }
    
    //righe ddt
    @FXML
    private TableColumn<RigheDDT, Integer> rDdtR_riga;

    @FXML
    private TableColumn<RigheDDT, String> rddtR_codArt;

    @FXML
    private TableColumn<RigheDDT, String> rddtR_commessa;

    @FXML
    private TableColumn<RigheDDT, Double> rddtR_qtà;

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
    	rDdtR_riga.setCellValueFactory(new PropertyValueFactory<RigheDDT, Integer>("riga"));
    	rddtR_commessa.setCellValueFactory(new PropertyValueFactory<RigheDDT, String>("commessa"));
    	rddtR_qtà.setCellValueFactory(new PropertyValueFactory<RigheDDT, Double>("qtà"));
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
    Ddt nuovoDdt = new Ddt(Integer.parseInt(insDDTnum.getText()),Integer.parseInt(insDDTanno.getText()), insDDTserie.getText(), "a", java.sql.Date.valueOf(insDDTdata.getValue()), insDDTcodcliente.getText(), "ao", Integer.parseInt(insDDTbadge.getText())); 
   	if(queryDdt.save(nuovoDdt)) {
    JOptionPane.showMessageDialog(null, "inserito");
 
    insRDDTanno.setText(insDDTanno.getText());
    insDDTanno.setText(null);
    
    insDDTbadge.setText(null);
    insDDTcodcliente.setText(null);
    insDDTdata.setValue(null);  
    insRDDTnum.setText(insDDTnum.getText());
    insDDTnum.setText(null);
    insRDDTserie.setText(insDDTserie.getText());
    insDDTserie.setText(null);
    
    }
    else {
    	JOptionPane.showMessageDialog(null, "non inserito");
    }	
   	
    }
    
    
    //insRigheDDT
    @FXML
    private TextField insRDDTanno;

    @FXML
    private TextField insRDDTcodart;

    @FXML
    private TextField insRDDTcommessa;

    @FXML
    private TextField insRDDTdataconsegna;

    @FXML
    private TextField insRDDTnum;

    @FXML
    private TextField insRDDTprezzo;

    @FXML
    private TextField insRDDTqtà;

    @FXML
    private TextField insRDDTral;

    @FXML
    private TextField insRDDTriga;

    @FXML
    private TextField insRDDTserie;

    @FXML
    void insertRddt(ActionEvent event) {
    	JOptionPane.showMessageDialog(null, "Ciao Fiore <3");
    }
    
}


