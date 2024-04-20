package controllers;

import entities.Association;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.AssociationService;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import entities.Association;

public class AssociationController {

    @FXML
    private Button AjouterButtoon;

    @FXML
    private TextField idSearch;

    @FXML
    private ListView<Association> listView;
    private final AssociationService associationService=new AssociationService();

    @FXML
    private Button modifierButton;

    @FXML
    private Button supprimerButton;
    Association  currentAssociation;
    @FXML
    void ajouterAssociation(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddAssociation.fxml"));
            Parent newPageRoot = loader.load();


            Scene pageScene = new Scene(newPageRoot);
            Stage stage = (Stage) listView.getScene().getWindow();
            stage.setScene(pageScene);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }




    @FXML
    void modifierAssociation(ActionEvent event) {

       try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifyAssociation.fxml"));
            Parent newPageRoot = loader.load();

            ModifyAssociation modifyAssociation= loader.getController();
            modifyAssociation.initializeValues(
                    currentAssociation.getNom(),currentAssociation.getTelephone(),
                    currentAssociation.getEmail(), currentAssociation.getLieu(), currentAssociation.getCode_postal(),currentAssociation.getDescription(),currentAssociation.getId());

            Scene newPageScene = new Scene(newPageRoot);
            Stage currentStage = (Stage) listView.getScene().getWindow();
            currentStage.setScene(newPageScene);
            currentStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void initialize()  {
        ObservableList<Association> associations = FXCollections.observableArrayList();
        listView.setItems(associations);
        try {
            List<Association> campaignFromService = associationService.afficher();
            associations.addAll(campaignFromService);
            listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldIndex, Number newIndex) {
                    currentAssociation = listView.getSelectionModel().getSelectedItem();
//
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void supprimerAssociation(ActionEvent event) {
        if (currentAssociation != null) {
            try {
                associationService.supprimer(currentAssociation.getId());
                initialize(); // Refresh the list after deletion
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No association selected for deletion.");
            alert.showAndWait();
        }
    }


    @FXML
    void searchauto()
        {
        String nom =idSearch.getText();

        try {
            List<Association> searchResults = associationService.chercherAssociationParNom(nom);

            ObservableList<Association> observableList = FXCollections.observableList(searchResults);

            listView.setItems(observableList);




        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    }

