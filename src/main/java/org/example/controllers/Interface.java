package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.example.entities.Demande;
import org.example.entities.Service;
import org.example.service.DemandeService;
import org.example.service.ServiceService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interface {

    ServiceService ss = new ServiceService();
    DemandeService ds = new DemandeService();
    @FXML
    private GridPane grid;

    @FXML
    private GridPane grid1;

    @FXML
    private Pane pn_alld;

    @FXML
    private Pane pn_alls;

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void refresh2(ActionEvent event) {
        grid1.getChildren().clear();
        displayg1();
    }

    @FXML
    void todemande(ActionEvent event) {
        pn_alld.toFront();
    }

    @FXML
    void toservice(ActionEvent event) {
        pn_alls.toFront();
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
                    itemController.setDataf(ppppp);
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

    private void displayg1() {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ds.Getall();
        int column = 0;
        int row = 2;

        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Demande.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    DemandeC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    String nom =resultSet2.getString("nom");
                    String prenom=resultSet2.getString("prenom");
                    String email=resultSet2.getString("email");
                    String tel=resultSet2.getString("telephone");
                    Demande ppppp = new Demande(id,nom,prenom,email,Integer.parseInt(tel),"",1);
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid1.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid1.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid1.setMaxHeight(Region.USE_PREF_SIZE);
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
