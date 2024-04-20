package controllers;



import entities.Association;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AssociationService;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ModifyAssociation {

    @FXML
    private TextField CodeField;

    @FXML
    private TextField DescField;

    @FXML
    private TextField EmailFiled;

    @FXML
    private TextField LieuFiled;

    @FXML
    private Button ModifyCampainButton;
    private final AssociationService cs = new AssociationService();
    @FXML
    private Button addImage;

    @FXML
    private TextField associationNameField;

    @FXML
    private ImageView imageField;

    @FXML
    private TextField numberFiled;
   int association_id;
    private final AssociationService associationService = new AssociationService();
    public void switchToDisplayAllAssociations() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Association.fxml"));
            Parent newPageRoot = loader.load();


            Scene pageScene = new Scene(newPageRoot);
            Stage stage = (Stage) associationNameField.getScene().getWindow();
            stage.setScene(pageScene);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }



    @FXML
    void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choisir une image");

        // Set initial directory
        File initialDirectory = new File("/Utilisateurs/ons/Téléchargements");
        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(initialDirectory);
        } else {
            System.err.println("Initial directory is not valid or does not exist.");
            // You might want to handle this case by providing a default directory or prompting the user to specify one.
        }

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG Images", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Images", "*.png"),
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.png")
        );

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageField.setImage(image);
        } else {
            System.out.println("No file has been selected");
        }
    }




    @FXML
    void modifyCampaign(ActionEvent event) {

        if (numberFiled.getText().isEmpty() || associationNameField.getText().isEmpty() || EmailFiled.getText().isEmpty() || associationNameField.getText().isEmpty() ||
                LieuFiled.getText().isEmpty() || DescField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(" Au moins un champ est vide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else if (!numberFiled.getText().matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numéro invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit être en chiffres !");
            alert.showAndWait();
        } else if (!associationNameField.getText().matches("^[a-zA-Z]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Le nom doit être composé uniquement de caractères alphabétiques");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir ce champ uniquement avec des caractères alphabétiques !");
            alert.showAndWait();
        } else if (numberFiled.getText().length() != 8) {


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Numéro invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres");
            alert.showAndWait();
        } else {
            try {
                associationService.modifier(associationNameField.getText(),Integer.parseInt(numberFiled.getText()),EmailFiled.getText(),
                        LieuFiled.getText(), Integer.parseInt(CodeField.getText()), DescField.getText(), association_id);

                switchToDisplayAllAssociations();

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur lors de la modification d'une association");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }

    }

    public void initializeValues(String nom, int telephone, String email, String lieu, int code_postal, String description, int id) {
        associationNameField.setText(nom);
        numberFiled.setText(Integer.toString(telephone));
        EmailFiled.setText(email);
        LieuFiled.setText(lieu);
        CodeField.setText(Integer.toString(code_postal));
        DescField.setText(description);
        association_id = id;



    }
}

