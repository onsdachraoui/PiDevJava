package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.example.entities.Service;
import org.example.service.ServiceService;

import java.io.IOException;
import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dashboard {

    @FXML
    private GridPane grid;

    @FXML
    private Pane pn_addservice;

    @FXML
    private Pane pn_eventlist;

    @FXML
    private TextField tf_categorie;

    @FXML
    private TextArea tf_desc;

    @FXML
    private TextField tf_duree;

    @FXML
    private TextField tf_nom;

    ServiceService ss = new ServiceService();

    @FXML
    void addevent(ActionEvent event) {
// Get the current date
        if (tf_nom.getText().isEmpty() || tf_desc.getText().isEmpty() ||tf_categorie.getText().isEmpty() ||tf_duree.getText().isEmpty()) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        String description = tf_desc.getText();
        String nom = tf_nom.getText();
        String duree = tf_duree.getText();
        String categorie =tf_categorie.getText();
        Service s = new Service(nom,description,description,duree,"oui");
        ss.ajouterEntite(s);
        tf_nom.clear();
        tf_desc.clear();
        tf_duree.clear();
        tf_categorie.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("Service ajouter !");
        alert.showAndWait();
        pn_eventlist.toFront();
    }

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void tocreateevent(ActionEvent event) {
        pn_addservice.toFront();
    }

    @FXML
    void toeventlist(ActionEvent event) {
        pn_eventlist.toFront();
    }


    private void displayg() {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ss.Getall();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Service.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    ServiceC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    String nom =resultSet2.getString("nom");
                    String categorie=resultSet2.getString("categorie");
                    String description=resultSet2.getString("description");
                    String duree=resultSet2.getString("duree_service");
                    Service ppppp = new Service(id,nom,categorie,description,duree,"oui");
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
