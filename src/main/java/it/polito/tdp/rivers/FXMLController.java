/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doRiverSelection(ActionEvent event) {
    	this.txtStartDate.setText(this.model.getStartDate(this.boxRiver.getValue()));
    	this.txtEndDate.setText(this.model.getEndDate(this.boxRiver.getValue()));
    	this.txtNumMeasurements.setText(""+this.boxRiver.getValue().getFlows().size());
    	this.txtFMed.setText(this.model.getFlowAvg(this.boxRiver.getValue()));
    }
    
    @FXML
    void runSimulator(ActionEvent event) {
    	if(this.txtK.getText()!=null && this.boxRiver.getValue()!=null) {
    		//controllo input
    		double k = 0.0;
    		try {
    			k = Double.parseDouble(this.txtK.getText());
    		} catch(NumberFormatException e) {
    			this.txtResult.setText("The k factor must be a number\n");
    			return;
    		}
    		
    		if(k>0) {
    			this.txtResult.setText(this.model.run(this.boxRiver.getValue(), k));
    		}
    	} else {
    		this.txtResult.setText("You must select a river and a k factor to run the simulation\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().setAll(this.model.getAllRivers());
    }
}
