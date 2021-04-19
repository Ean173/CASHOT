package application.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import application.model.CashotSystem;
import application.model.Employee;
import application.model.Item;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditItemsController implements EventHandler {

	@FXML Button addItem;
	@FXML Button deleteItem;
	@FXML Button editItem;
	
	@FXML TextField addName;
	@FXML TextField addPrice;
	@FXML TextField addRow;
	@FXML TextField addColumn;
	@FXML TextField deleteName;
	@FXML TextField editCurrName;
	@FXML TextField editNewName;
	@FXML TextField editNewPrice;
	@FXML TextField editNewRow;
	@FXML TextField editNewColumn;
	
	@FXML TextField resultOutput;
	@FXML AnchorPane content;
	
	
	ArrayList<Item> itemsInOrder;
	
	static CashotSystem system;
	
	@Override
	public void handle(Event event) {
		
	}
	
	public void initialize( ) throws IOException{
		//Load items ?
		system = CashotSystem.getInstance();
		
		system.setController(this);
	}
	
	public void addItem(Event event){
		
		try{
		String name = addName.getText();
		double price = Double.parseDouble(addPrice.getText());
		int row = Integer.parseInt(addRow.getText());
		int column = Integer.parseInt(addColumn.getText());
		boolean wasMatched = false;
		
		Item temp = new Item(name, price, row, column);
		
		for (Item items : system.getItems()){
			if(items.getName() == temp.getName()){
				wasMatched = true;
				resultOutput.setText("This name already exists.");
				break;
			}
			//System.out.println(items);
			if((items.getRow() == temp.getRow()) && (items.getColumn() == temp.getColumn())){
				wasMatched = true;
				resultOutput.setText("There is an item already in this position.");
				break;
			}
		}
		if(!wasMatched){
			system.addItem(temp);
			system.updateItemsCsv(temp);
		}
		//System.out.println("Bob yo or sup dog");
		}catch(Exception e) {
			resultOutput.setText("You need to enter all fields with information.");
		}
	}
	
	public void deleteItem(Item item){
		
	}
	
	public void editItem(Item item){
		
	}
	
	public void loadAdmin (Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/administor.fxml"));
		content.getChildren().setAll(pane);
	}
}
