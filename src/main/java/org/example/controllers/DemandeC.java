package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.example.entities.Demande;
import javafx.event.ActionEvent;
import org.example.entities.Service;
import org.example.service.DemandeService;

import java.util.Objects;

public class DemandeC {

    @FXML
    private Button btnmod;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_tel;
    DemandeService ds = new DemandeService();
    private Demande d ;
    private int id;
    public void setData(Demande q) {
        this.d = q;
        id=q.getId();
        tf_nom.setText(q.getNom());
        tf_prenom.setText(q.getPrenom());
        tf_email.setText(q.getEmail());
        tf_tel.setText(String.valueOf(q.getTelephone()));
        tf_nom.setEditable(false);
        tf_prenom.setEditable(false);
        tf_email.setEditable(false);
        tf_tel.setEditable(false);
    }

    @FXML
    void Supprimer(ActionEvent event) {
        try {
            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("etes vous sure de vouloir supprimer votre demande?");

            // Add "OK" and "Cancel" buttons to the dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the confirmation dialog and wait for the user's response
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // If the user clicked "OK" in the confirmation dialog, proceed with the deletion
            if (userResponse == ButtonType.OK) {
                // Create a new User instance with the provided ID
                Demande eventToDelete = new Demande(this.id,"","","",0,"",0);

                // Call the method to delete the user entity
                ds.supprimerEntite(eventToDelete);
                /*
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Dashboard.fxml"));
                Dashboard itemController = fxmlLoader.getController();
                ActionEvent e = new ActionEvent();
                itemController.refresh(e);

                 */
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID entered by the user is not a valid integer
            // Display an error message or handle it as appropriate for your application
            e.printStackTrace(); // Or log the error
        }
    }
    @FXML
    void modifier(ActionEvent event) {
        if (Objects.equals(btnmod.getText(), "Modifier")) {
            btnmod.setText("Valider");
            tf_nom.setEditable(true);
            tf_email.setEditable(true);
            tf_prenom.setEditable(true);
            tf_tel.setEditable(true);
        } else {
            if (tf_nom.getText().isEmpty() || tf_tel.getText().isEmpty() ||tf_prenom.getText().isEmpty() ||tf_prenom.getText().isEmpty()) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            Demande p = new Demande(this.id,tf_nom.getText(),tf_prenom.getText(),tf_email.getText(),Integer.parseInt(tf_tel.getText()),"",0);
            ds.modifierEntite(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Valider");
            alert.setHeaderText(null);
            alert.setContentText("Demande modifier avec success !");
            alert.showAndWait();
            tf_nom.setEditable(false);
            tf_tel.setEditable(false);
            tf_prenom.setEditable(false);
            tf_email.setEditable(false);
            btnmod.setText("Modifier");
        }
    }

}
