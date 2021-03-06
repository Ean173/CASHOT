package application.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import application.model.CashotSystem;
import application.model.Item;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

/**
 * The CashierController class controls the cashier's screen. This initializes
 * all of the FXML buttons onto a matrix, makes an items ArrayList and a System
 * instance.
 * 
 * @author Darean Wilde grl167 63678621
 * @author Jacob Shawver fww704 36242636
 * @author Majerus Sims hug180 79595196
 * @author Alexander Delgado tvh991 79595706
 *
 */

public class CashierController implements EventHandler {

	@FXML
	Button button00;
	@FXML
	Button button01;
	@FXML
	Button button02;
	@FXML
	Button button03;
	@FXML
	Button button10;
	@FXML
	Button button11;
	@FXML
	Button button12;
	@FXML
	Button button13;
	@FXML
	Button button20;
	@FXML
	Button button21;
	@FXML
	Button button22;
	@FXML
	Button button23;
	@FXML
	Button button30;
	@FXML
	Button button31;
	@FXML
	Button button32;
	@FXML
	Button button33;
	@FXML
	Button button40;
	@FXML
	Button button41;
	@FXML
	Button button42;
	@FXML
	Button button43;
	@FXML
	Button button50;
	@FXML
	Button button51;
	@FXML
	Button button52;
	@FXML
	Button button53;

	@FXML
	TextArea receiptNames;
	@FXML
	TextArea receiptPrices;
	@FXML
	TextArea receiptTotal;

	Button cashierButtons[][];

	static ArrayList<Item> itemsInOrder;

	@FXML
	private AnchorPane content;
	static CashotSystem system;

	/**
	 * The initialize method creates the system instances, the button matrix,
	 * and receipt instances need to operate.
	 * 
	 * @throws IOException
	 */
	public void initialize() throws IOException {
		// Load items ?
		system = CashotSystem.getInstance();

		system.setController(this);

		system.loadEmployees();

		cashierButtons = new Button[6][4];
		buttonToMatrix();

		// try {
		// system.loadItems();
		// } catch (Error e) {
		// e.printStackTrace();
		// }

		system.getItemsInButtons("cashier");

		system.newOrder("cashier");
		itemsInOrder = new ArrayList<Item>();

		receiptNames.addEventFilter(ScrollEvent.ANY, (x) -> {
			receiptPrices.setScrollTop(receiptNames.getScrollTop());
		});

		receiptPrices.addEventFilter(ScrollEvent.ANY, (x) -> {
			receiptNames.setScrollTop(receiptPrices.getScrollTop());
		});

	}

	/**
	 * The handle function happens when the item is about to be rung up. This
	 * gets the items array list, creates
	 * 
	 * @param event
	 *            is what the buttons do when pressed. They add an item to the
	 *            order.
	 */
	@Override
	public void handle(Event event) {
		// System.out.println(cashierButtons[1][0]);
		// System.out.println(event.getSource());
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (cashierButtons[i][j] == event.getSource()) {
					itemsInOrder = system.addItemToOrder(i, j);
					// receiptList.setText(itemsInOrder.toString());
					String strName = "";
					String strPrice = "";
					String strTotal = "";
					double price = 0;
					String moneyString = "";
					for (Item item : itemsInOrder) {
						price = item.getPrice();
						moneyString = CashotSystem.dblToMoneyString((price));
						// str += String.format("%-50s %15s\n", item.getName(),
						// moneyString);
						strName += item.getName() + "\n";
						strPrice += moneyString + "\n";
						// System.out.printf("%-50s %15s\n", item.getName(),
						// moneyString);
						// System.out.print(str);
						// receiptList.setText(str);
					}
					double total;
					receiptNames.setText(strName);
					receiptPrices.setText(strPrice);
					total = system.getOrderTotal();
					strTotal = CashotSystem.dblToMoneyString((total));
					receiptTotal.setText(strTotal);
				}
			}
		}
	}

	// public void nameScroll(Event event){
	// receiptPrices.setScrollTop(receiptNames.getScrollTop());
	// }
	//
	// public void priceScroll(Event event){
	// receiptNames.setScrollTop(receiptPrices.getScrollTop());
	// }

	/**
	 * loadMain changes the screen to main.fxml.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loadMain(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/main.fxml"));
		content.getChildren().setAll(pane);
	}

	/**
	 * loadTraining changes the screen to Training.fxml.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loadTraining(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/Training.fxml"));
		content.getChildren().setAll(pane);
	}

	/**
	 * loadAdminisrer changes the screen to administor.fxml or
	 * adminLoginScreen.fxml by either checking to see if the logged in, thus
	 * bypassing the login screen, or making the user log in to use Admin.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loadAdminister(Event event) throws IOException {
		try {
			if (system.getSignedIn().isAdmin().equals("TRUE")) {
				bypassAdminLogin(event);
			} else {
				loadAdminLogin(event);
			}
		} catch (Exception e) {
			loadAdminLogin(event);
		}
	}

	/**
	 * Called by loadAdminister, this makes the screen immediately change to
	 * administor.fxml.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void bypassAdminLogin(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/administor.fxml"));
		content.getChildren().setAll(pane);
	}

	/**
	 * Called by loadAdminister, if the user does not meet the criteria for a
	 * skip login screen, this page appears.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loadAdminLogin(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/adminLoginScreen.fxml"));
		content.getChildren().setAll(pane);
	}

	/**
	 * Logs out the user when called.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void logOut(Event event) throws IOException {
		system.logOut();
		loadMain(event);
	}

	/**
	 * loadRingUpCustomer changes the screen to Training.fxml.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loadRingUpCustomer(Event event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/view/ringUpCustomer.fxml"));
		content.getChildren().setAll(pane);
	}

	// public void loadItems() throws IOException{
	// system.loadItems();
	// }
	/**
	 * loadItems loads up all of the items in items.csv.
	 * 
	 * @throws IOException
	 */
	public void loadItems() throws IOException {
		// String employeeName, String userName, String employeePassword, int ID
		String row;

		BufferedReader csvReader = new BufferedReader(new FileReader("data/items.csv"));
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			Item tempItem = new Item(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]),
					Integer.parseInt(data[3]));
			addItem(tempItem);

		}
		csvReader.close();

		hideUnimplementedButtons();
	}

	/**
	 * addItem gets an item that has been pressed by the user to the ArrayList.
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		// itemMatrix[item.getRow()][item.getColumn()] = item;
		// controller.setButton(item);
		// System.out.println(itemMatrix[item.getRow()][item.getColumn()]);
		Button button = cashierButtons[item.getRow()][item.getColumn()];
		double price = item.getPrice();
		String moneyString = CashotSystem.dblToMoneyString(price);

		button.setText(item.getName() + "\n" + moneyString);

	}

	/**
	 * ringUpOrder changes the screen to the ring up order page, then ends this
	 * order.
	 * 
	 * @throws IOException
	 */
	public void ringUpOrder() throws IOException {
		system.ringUp();
		system.newOrder("cashier");
		receiptNames.setText("");
		receiptPrices.setText("");
		receiptTotal.setText("");
	}

	/**
	 * Hides any buttons not in use.
	 */
	public void hideUnimplementedButtons() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				Button button = cashierButtons[i][j];
				if (button.getText().equals("")) {
					button.setVisible(false);
				}
			}
		}
	}

	/**
	 * Places each button onto a place on a 2x2 array.
	 */
	public void buttonToMatrix() {
		cashierButtons[0][0] = button00;
		cashierButtons[0][1] = button01;
		cashierButtons[0][2] = button02;
		cashierButtons[0][3] = button03;
		cashierButtons[1][0] = button10;
		cashierButtons[1][1] = button11;
		cashierButtons[1][2] = button12;
		cashierButtons[1][3] = button13;
		cashierButtons[2][0] = button20;
		cashierButtons[2][1] = button21;
		cashierButtons[2][2] = button22;
		cashierButtons[2][3] = button23;
		cashierButtons[3][0] = button30;
		cashierButtons[3][1] = button31;
		cashierButtons[3][2] = button32;
		cashierButtons[3][3] = button33;
		cashierButtons[4][0] = button40;
		cashierButtons[4][1] = button41;
		cashierButtons[4][2] = button42;
		cashierButtons[4][3] = button43;
		cashierButtons[5][0] = button50;
		cashierButtons[5][1] = button51;
		cashierButtons[5][2] = button52;
		cashierButtons[5][3] = button53;

	}

	/**
	 * shows a single button to the item's data passed in.
	 * 
	 * @param item
	 */
	public void setButton(Item item) {
		Button button = cashierButtons[item.getRow()][item.getColumn()];
		double price = item.getPrice();
		String moneyString = CashotSystem.dblToMoneyString((price));

		button.setText(item.getName() + "\n" + moneyString);
		// System.out.println(button);
	}

}
