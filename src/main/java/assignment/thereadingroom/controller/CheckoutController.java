package assignment.thereadingroom.controller;

import assignment.thereadingroom.dto.OrderCreateDto;
import assignment.thereadingroom.dto.OrderItemCreateDto;
import assignment.thereadingroom.model.CartItem;
import assignment.thereadingroom.model.CreditCardPayment;
import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.model.OrderItem;
import assignment.thereadingroom.view.UserDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

import java.util.ArrayList;

public class CheckoutController {

    @FXML
    private Button backButton;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private TextField cvvInput;

    @FXML
    private TextField expiresOnInput;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label successLabel;

    @FXML
    public void initialize() {
        double totalAmount = Model.getInstance().getCart().getTotalPrice();
        totalAmountLabel.setText(String.format("%.2f", totalAmount));
    }

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        UserDashboard userDashboard = new UserDashboard(stage);
        userDashboard.show();
    }

    public void onPlaceOrderButtonAction(ActionEvent event) {
        String cardNumber = cardNumberInput.getText();
        String cvv = cvvInput.getText();
        String expires = expiresOnInput.getText();

        successLabel.setText("");
        errorLabel.setText("");

        CreditCardPayment cardPayment = new CreditCardPayment();
        cardPayment.setCardNumber(cardNumber);
        cardPayment.setCvv(cvv);
        cardPayment.setExpiryDate(expires);

        try{
            double totalAmount = Model.getInstance().getCart().getTotalPrice();
            boolean success = cardPayment.pay(totalAmount);
            if (success) {
                String username = Model.getInstance().getCurrentUser().getUsername();
                OrderCreateDto orderCreateDto = new OrderCreateDto(username, totalAmount);
                List<OrderItemCreateDto> orderItemCreateDtoList = new ArrayList<OrderItemCreateDto>();
                for(CartItem cartItem: Model.getInstance().getCart().getItems()){
                    OrderItemCreateDto orderItemCreateDto = new OrderItemCreateDto(orderCreateDto.getId(), cartItem.getQuantity(), cartItem.getPrice(), cartItem.getTitle());
                    orderItemCreateDtoList.add(orderItemCreateDto);
                }

                Model.getInstance().getOrderDao().createOrder(orderCreateDto, orderItemCreateDtoList);

                Model.getInstance().getCart().clearCart();

                successLabel.setText("Order created successfully");

                placeOrderButton.setDisable(true);
            } else {
                errorLabel.setText("Invalid card details! Check the card details!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
