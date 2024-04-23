package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.entities.Demande;
import org.example.entities.Service;
import org.example.service.DemandeService;
import org.example.service.ServiceService;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceC {

    private Service service= new Service();
    private int id;

    @FXML
    private Button btndem;

    @FXML
    private Button btnmod;

    @FXML
    private Button btnsupp;

    @FXML
    private TextField tf_categorie;

    @FXML
    private TextArea tf_desc;

    @FXML
    private TextField tf_duree;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_nomp;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_tel;
    ServiceService ss = new ServiceService();
    DemandeService ds= new DemandeService();

    @FXML
    void demander(ActionEvent event) {
        if(Objects.equals(btndem.getText(), "Demander"))
        {
        tf_nomp.setVisible(true);
        tf_tel.setVisible(true);
        tf_email.setVisible(true);
        tf_prenom.setVisible(true);
        tf_nom.setVisible(false);
        tf_desc.setVisible(false);
        tf_categorie.setVisible(false);
        tf_duree.setVisible(false);
        btndem.setText("Valider");
        }else{
            if (tf_nomp.getText().isEmpty() || tf_prenom.getText().isEmpty() ||tf_email.getText().isEmpty() ||tf_tel.getText().isEmpty()) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            try {
                // Attempt to parse the text as an integer
                int num = Integer.parseInt(tf_tel.getText());
            } catch (NumberFormatException e) {
                // If parsing fails (NumberFormatException is thrown), display an alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Num tel incorrect");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un num exact !");
                alert.showAndWait();

                // Return or perform any necessary action based on the invalid input
                return;
            }
            String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(tf_email.getText());

            if (!matcher.matches()) {
                // If the email doesn't match the pattern, display an alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Mail incorrect");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un mail valide!");
                alert.showAndWait();
                return;
            }
            Demande p = new Demande(tf_nomp.getText(),tf_prenom.getText(),tf_email.getText(),Integer.parseInt(tf_tel.getText()),"",this.id);
            ds.ajouterEntite(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Valider");
            alert.setHeaderText(null);
            alert.setContentText("Service modifier avec success !");
            alert.showAndWait();
            tf_nom.setEditable(false);
            tf_desc.setEditable(false);
            tf_categorie.setEditable(false);
            tf_duree.setEditable(false);
            btnmod.setVisible(false);
            btndem.setText("Demander");
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        if (Objects.equals(btnmod.getText(), "Modifier")) {
            btnmod.setText("Valider");
            tf_nom.setEditable(true);
            tf_desc.setEditable(true);
            tf_categorie.setEditable(true);
            tf_duree.setEditable(true);
        } else {
            if (tf_nom.getText().isEmpty() || tf_duree.getText().isEmpty() ||tf_desc.getText().isEmpty() ||tf_categorie.getText().isEmpty()) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            Service p = new Service(this.id,tf_nom.getText(),tf_categorie.getText(),tf_desc.getText(),tf_duree.getText(),"");
            ss.modifierEntite(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Valider");
            alert.setHeaderText(null);
            alert.setContentText("Service modifier avec success !");
            alert.showAndWait();
            tf_nom.setEditable(false);
            tf_desc.setEditable(false);
            tf_categorie.setEditable(false);
            tf_duree.setEditable(false);
            btnmod.setVisible(false);
            btnmod.setText("Modifier");
        }
    }
    @FXML
    void supprimer(ActionEvent event) {
        try {
            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("etes vous sure de vouloir supprimer ce service?");

            // Add "OK" and "Cancel" buttons to the dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the confirmation dialog and wait for the user's response
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // If the user clicked "OK" in the confirmation dialog, proceed with the deletion
            if (userResponse == ButtonType.OK) {
                // Create a new User instance with the provided ID
                Service eventToDelete = new Service(this.id,"","","","","");

                // Call the method to delete the user entity
                ss.supprimerEntite(eventToDelete);
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

    public void setData(Service q) {
        this.service = q;
        id=q.getId();
        tf_nom.setText(q.getNom());
        tf_desc.setText(q.getDescription());
        tf_categorie.setText(q.getCategorie());
        tf_duree.setText(q.getDuree_service());
        tf_nom.setEditable(false);
        tf_desc.setEditable(false);
        tf_categorie.setEditable(false);
        tf_duree.setEditable(false);
        tf_nomp.setVisible(false);
        tf_tel.setVisible(false);
        tf_email.setVisible(false);
        tf_prenom.setVisible(false);
        btndem.setVisible(false);
    }

    public void setDataf(Service q) {
        this.service = q;
        id=q.getId();
        tf_nom.setText(q.getNom());
        tf_desc.setText(q.getDescription());
        tf_categorie.setText(q.getCategorie());
        tf_duree.setText(q.getDuree_service());
        tf_nom.setEditable(false);
        tf_desc.setEditable(false);
        tf_categorie.setEditable(false);
        tf_duree.setEditable(false);
        btnmod.setVisible(false);
        btnsupp.setVisible(false);
        tf_nomp.setVisible(false);
        tf_tel.setVisible(false);
        tf_email.setVisible(false);
        tf_prenom.setVisible(false);

    }




}
