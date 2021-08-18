package vistaControl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import modelo.Parqueadero;

public class ParqueoPublicoController {

    private static final NumberFormat CURRENCY =
    	       NumberFormat.getCurrencyInstance();
    
    @FXML
    private ChoiceBox<String> tipoVehiculoChoiceBox;

    @FXML
    private TextField horaInicioTextField;

    @FXML
    private TextField horaFinalTextField;

    @FXML
    private Button calcularButton;

    @FXML
    private TextField valorAPagarTextField;
    
    private Parqueadero parqueoPublico;
        
    // called by FXMLLoader to initialize the controller
    public void initialize() {
    	parqueoPublico = new Parqueadero();
    	CURRENCY.setRoundingMode(RoundingMode.HALF_UP);
    	tipoVehiculoChoiceBox.setItems(FXCollections.observableArrayList("Carro","Moto"));
    	
    	tipoVehiculoChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    	      @Override
    	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
    	        //System.out.println(tipoVehiculoChoiceBox.getItems().get((Integer) number2));
    	    	  parqueoPublico.marcarHoraInicio(tipoVehiculoChoiceBox.getItems().get((Integer) number2));
    	    	  String horaInicio = parqueoPublico.darHoraInicio();
    	    	  horaInicioTextField.setText(horaInicio);
    	    	  horaFinalTextField.setText("");
    	    	  valorAPagarTextField.setText("");
    	      }
    	    });    	
    }
    
    @FXML
    void calcularButtonPresionado(ActionEvent event) {
    	//System.out.println("Hola!");
    	parqueoPublico.marcarHoraFinal();
    	String horaFinal = parqueoPublico.darHoraFinal();
    	horaFinalTextField.setText(horaFinal);
    	double valorAPagar = parqueoPublico.calcularValorAPagar();
    	// To modify line
    	valorAPagarTextField.setText(CURRENCY.format(valorAPagar));
    }    
}
