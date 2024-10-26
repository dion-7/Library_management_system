package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.model.OrderJoinedItem;
import assignment.thereadingroom.view.UserDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderController {
    @FXML
    Button exportButton;

    @FXML
    Button backButton;

    @FXML
    Label logLabel;

    @FXML
    TableView orderTable;

    @FXML
    private TableColumn<OrderJoinedItem, String> orderIdColumn;
    @FXML
    private TableColumn<OrderJoinedItem, String> usernameColumn;
    @FXML
    private TableColumn<OrderJoinedItem, String> createdAtColumn;
    @FXML
    private TableColumn<OrderJoinedItem, Double> totalAmountColumn;
    @FXML
    private TableColumn<OrderJoinedItem, String> bookTitleColumn;
    @FXML
    private TableColumn<OrderJoinedItem, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderJoinedItem, Double> priceColumn;

    List<OrderJoinedItem> orderItems;

    @FXML
    public void initialize() {
        // Set up the columns to use the OrderJoinedItem properties
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load data into the TableView
        loadOrderData("exampleUsername");
    }

    private void loadOrderData(String username) {
        try {
            this.orderItems = Model.getInstance().getOrderDao().getOrderJoinedItemsByUsername(username);
            ObservableList<OrderJoinedItem> orderData = FXCollections.observableArrayList(this.orderItems);
            orderTable.setItems(orderData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        UserDashboard userDashboard = new UserDashboard(stage);
        userDashboard.show();
    }

    public void onExportButtonAction(ActionEvent actionEvent) {
        //Stage stage = (Stage) exportButton.getScene().getWindow();

        // Create a FileChooser to allow the user to select a file location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            exportToCSV(file);
        }

        logLabel.setText("Order exported");
    }

    private void exportToCSV(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write CSV header
            writer.write("Order ID,Username,Created At,Total Amount,Item ID,Book Title,Quantity,Price");
            writer.newLine();

            // Get the data from the TableView
            for (OrderJoinedItem item : orderItems) {
                String line = String.join(",",
                        item.getOrderId(),
                        item.getUsername(),
                        item.getCreatedAt(),
                        String.valueOf(item.getTotalPrice()),
                        item.getItemId(),
                        item.getBookTitle(),
                        String.valueOf(item.getQuantity()),
                        String.valueOf(item.getItemPrice())
                );
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Data exported successfully to " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
