package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.DonService;
import entities.Don;

import java.sql.SQLException;

public class AddDonController {

    @FXML
    private Button annuler;

    @FXML
    private ChoiceBox<String> modepaiement; // Change the ChoiceBox type to String

    @FXML
    private TextField montantdon;

    @FXML
    private Button valider;

    private DonService donService; // Create an instance of DonService

    public AddDonController() {
        this.donService = new DonService(); // Initialize DonService
    }

    @FXML
    void annuler(ActionEvent event) {
        // Handle cancel action
        montantdon.clear(); // Clear montant field
        modepaiement.getSelectionModel().clearSelection(); // Clear choice box selection
    }

    @FXML
    void valider(ActionEvent event) {
        // Handle validation action
        String montantText = montantdon.getText().trim();
        String modePaiement = modepaiement.getValue(); // Get selected mode of payment

        if (montantText.isEmpty() || modePaiement == null) {
            // Show an alert if montant or mode of payment is not selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Montant and Mode de paiement are required fields!");
            alert.showAndWait();
            return;
        }

        float montant;
        try {
            montant = Float.parseFloat(montantText); // Parse the montant value
        } catch (NumberFormatException e) {
            // Show an alert if montant is not a valid number
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Montant must be a valid number!");
            alert.showAndWait();
            return;
        }

        // Create a new Don object
        Don don = new Don();
        don.setMontant(montant);
        don.setMode_de_paiement(modePaiement);

        try {
            // Add the don to the database
            donService.ajouter(don);
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Don added successfully!");
            alert.showAndWait();
            // Clear fields after successful addition
            montantdon.clear();
            modepaiement.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            // Show error message if database operation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the don!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
