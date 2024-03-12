package view;

import application.CurrencyAppController;
import dao.TransactionDao;
import entity.Currency;
import entity.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.text.*;
import java.util.List;

public class CurrencyConverterView extends Application {
    private CurrencyAppController controller;

    public void start(Stage stage) {
        controller = new CurrencyAppController();
        stage.setTitle("Currency Converter");

        Label amountLabel = new Label("Amount: ");
        TextField amount = new TextField();
        Label fromLabel = new Label("From: ");
        ChoiceBox<Currency> from = new ChoiceBox<>();
        Label toLabel = new Label("To:");
        ChoiceBox<Currency> to = new ChoiceBox<>();
        Button convertButton = new Button("Convert");
        Button addCurrencyButton = new Button("Add Currency");
        Label resultLabel = new Label("Result: ");
        TextField result = new TextField();
        Text instructions = new Text("First enter the amount to convert. \n" +
                "Then select currencies to convert from and to. \n" +
                "Finally, click the convert button. \n" +
                "\nIf you want to add a new currency, click the add currency button. " +
                "\nAbbreviation and name must contain letters only. Rate must be a positive number.");
        instructions.setWrappingWidth(500);

        List<Currency> currencies;
        try {
            currencies = controller.getCurrencies();
            from.getItems().addAll(currencies);
            to.getItems().addAll(currencies);
        } catch (Exception e) {
            showErrorDialog("Error", "Failed to get currencies from database.");
            return;
        }

        addCurrencyButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Stage newStage = new Stage();
                newStage.setTitle("Add Currency");
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10, 10, 10, 10));

                Label abbreviationLabel = new Label("Abbreviation:");
                TextField abbreviationField = new TextField();
                Label nameLabel = new Label("Name:");
                TextField nameField = new TextField();
                Label rateLabel = new Label("Rate:");
                TextField rateField = new TextField();
                Button addButton = new Button("Add");

                grid.add(abbreviationLabel, 0, 0);
                grid.add(abbreviationField, 1, 0);
                grid.add(nameLabel, 0, 1);
                grid.add(nameField, 1, 1);
                grid.add(rateLabel, 0, 2);
                grid.add(rateField, 1, 2);
                grid.add(addButton, 1, 3);

                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        String abbreviation = abbreviationField.getText();
                        String name = nameField.getText();
                        String rateText = rateField.getText();

                        if (abbreviation.isEmpty() || name.isEmpty() || rateText.isEmpty()) {
                            showError("Please fill in all fields.");
                            return;
                        }


                        double rate;
                        try {
                            rate = Double.parseDouble(rateText);
                            if (rate <= 0) {
                                showError("Rate must be a positive number.");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            showError("Rate must be a valid number.");
                            return;
                        }

                        Currency newCurrency = new Currency(abbreviation, name, rate);
                        controller.addCurrency(newCurrency);
                        currencies.add(newCurrency);
                        from.getItems().add(newCurrency);
                        to.getItems().add(newCurrency);
                        newStage.close();
                    }
                    private void showError(String message) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText(message);
                        alert.showAndWait();
                    }
                });

                Scene scene = new Scene(grid, 300, 150);
                newStage.setScene(scene);
                newStage.showAndWait();
            }
        });

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    double howMuch = Double.parseDouble(amount.getText());
                    if (howMuch < 0) {
                        result.setText("Please enter a non-negative amount.");
                        return;
                    }
                    Currency fromCurrency = from.getValue();
                    Currency toCurrency = to.getValue();
                    double converted = controller.convert(howMuch, fromCurrency, toCurrency);
                    result.setText(String.format("%.2f", converted));

                    Transaction transaction = new Transaction(howMuch, fromCurrency.getAbbreviation(), toCurrency.getAbbreviation());
                    TransactionDao transactionDao = new TransactionDao();
                    transactionDao.persist(transaction);

                } catch (NumberFormatException e) {
                    result.setText("Invalid input");
                } catch (NullPointerException e) {
                    result.setText("Please select currencies.");
                }
            }
        });

        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10, 5, 5, 10));
        vbox.getChildren().addAll(
                instructions,
                amountLabel,
                amount,
                fromLabel,
                from,
                toLabel,
                to,
                convertButton,
                addCurrencyButton,
                resultLabel,
                result
        );

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
