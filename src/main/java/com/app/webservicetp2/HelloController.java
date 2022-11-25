package com.app.webservicetp2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    callWebService callWebService;

    SpinnerValueFactory<Double> spinnerNorth = new SpinnerValueFactory.DoubleSpinnerValueFactory(-90d,90d, 0, 0.1);
    SpinnerValueFactory<Double> spinnerSouth = new SpinnerValueFactory.DoubleSpinnerValueFactory(-90d,90d, 0, 0.1);
    SpinnerValueFactory<Double> spinnerEast = new SpinnerValueFactory.DoubleSpinnerValueFactory(-90d,90d, 0, 0.1);
    SpinnerValueFactory<Double> spinnerWest = new SpinnerValueFactory.DoubleSpinnerValueFactory(-90d,90d, 0, 0.1);

    @FXML
    private Label welcomeText;

    @FXML
    Spinner<Double> north;

    @FXML
    Spinner<Double> south;

    @FXML
    Spinner<Double> east;

    @FXML
    Spinner<Double> west;

    @FXML
    protected void onJSONButtonClick() {
        String response = callWebService.callGeoNamesService("citiesJSON", north.getValue(), south.getValue(), east.getValue(), west.getValue());
        System.out.println("mmh  "+response);
    }

    @FXML
    protected void onXMLButtonClick() {

    }

    @FXML
    protected void onSwitchButtonClick() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        north.setValueFactory(spinnerNorth);
        south.setValueFactory(spinnerSouth);
        east.setValueFactory(spinnerEast);
        west.setValueFactory(spinnerWest);

        this.callWebService = new callWebService();
        this.callWebService.initializeService("http://www.geonames.org/");
    }
}