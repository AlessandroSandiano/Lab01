package it.polito.tdp.parole;

import it.polito.tdp.parole.model.Parole;

import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Parole elenco;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtTempo;

    @FXML
    private Button btnReset;

    @FXML
    void doInsert(ActionEvent event) {
    	txtTempo.clear();
    	txtResult.clear();
    	
    	int flag = 0;
    	
    	if (txtParola.getText().length() == 0) {
    		txtResult.setText("Attenzione! Non è stata inserita alcuna parola.\n\n");
    		flag = 1;
    	}
    	
    	char[] c = txtParola.getText().toCharArray();
    	for (char ch: c) {
    		if (Character.isDigit(ch)) {
    			txtResult.setText("Attenzione! È presente almeno un carattere numerico.\n\n");
    			flag = 1;
    		}
    		if ((Character.isWhitespace(ch))) {
    			txtResult.setText("Inserire solo una parola per volta!\n\n");
        		flag = 1;
    		}
    	}
    	
    	for (String s: elenco.getElenco())
    		if (s.compareTo(txtParola.getText()) == 0) {
    			txtResult.setText("Parola già presente nell'elenco.\n\n");
        		flag = 1;
    		}
    	
    	String output = new String();
    	long inizio = 0;
    	long fine = 0;
    	if (flag == 0) {
    		inizio = System.nanoTime();
    		elenco.addParola(txtParola.getText());
    		fine = System.nanoTime();
    	}
    	Collections.sort(elenco.getElenco());
    	if (elenco.getElenco().size() > 0) {
    		for (int i=0; i<elenco.getElenco().size()-1; i++)
    			output += elenco.getElenco().get(i) + "\n";
    		output += elenco.getElenco().get(elenco.getElenco().size()-1);
    	}
    	txtResult.appendText(output);
    	txtParola.clear();
    	//txtTempo.setText(Long.valueOf(System.nanoTime()).toString());  SBAGLIATO (si deve calcolare solo l'aggiunta della singola parola!)
    	txtTempo.setText("[Inserimento]" + Double.toString(((double) (fine - inizio))/1e9) + "s");
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtTempo.clear();
    	txtParola.clear();
    	txtResult.clear();
    	long inizio = System.nanoTime();
    	elenco.reset();
    	long fine = System.nanoTime();
    	txtTempo.setText("[Reset]" + Double.toString(((double) (fine - inizio))/1e9) + "s");
    }

    @FXML
    void handleCancella(ActionEvent event) {
    	txtTempo.clear();
    	txtResult.clear();
    	boolean eliminata;
    	long inizio = System.nanoTime();
    	eliminata = elenco.cancella(txtParola.getText());
    	long fine = System.nanoTime();
    	if (eliminata == false)
    		txtResult.setText("Impossibile! La parola selezionata non è nell'elenco.\n\n");
    	String output = new String();
    	Collections.sort(elenco.getElenco());
    	if (elenco.getElenco().size() > 0) {
    		for (int i=0; i<elenco.getElenco().size()-1; i++)
    			output += elenco.getElenco().get(i) + "\n";
    		output += elenco.getElenco().get(elenco.getElenco().size()-1);
    	}
    	txtResult.appendText(output);
    	txtParola.clear();
    	if (eliminata == true) {
    		txtTempo.setText("[Cancella]" + Double.toString(((double) (fine - inizio))/1e9) + "s");
    	}
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

        elenco = new Parole() ;
    }
}


