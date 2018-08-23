package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class CarTableViewController {

	@FXML
	private TableView<Car> carTableView;
	
	@FXML
	private Button editSelectedCarButton;
	
	@FXML
	private Button createNewCarButton;
	
	@FXML
	private Button deleteSelectedCarButton;

	@FXML
	protected void handleTableViewClickedAction(ActionEvent event) {

	}

}
