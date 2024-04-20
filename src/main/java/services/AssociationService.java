package services;
import entities.Association;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AssociationService implements IService<Association>{
     Connection connection ;
    public AssociationService() {
        connection = MyDatabase.getInstance().getConnection();
    }


    public void ajouter(Association association) throws SQLException {
        String sql = "INSERT INTO association (id, nom, telephone, email, lieu, code_postal,description, photo) VALUES (?, ?, ?, ?, ?, ?,?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, association.getId());
        preparedStatement.setString(2, association.getNom());
        preparedStatement.setInt(3, association.getTelephone());
        preparedStatement.setString(4, association.getEmail());
        preparedStatement.setString(5, association.getLieu());
        preparedStatement.setInt(6, association.getCode_postal());
        preparedStatement.setString(7, association.getDescription());
        preparedStatement.setString(8, association.getPhoto());
        preparedStatement.executeUpdate();
    }

    public void modifier(Association association) throws SQLException {}
    public void modifier(String nom,int telephone,String email,String lieu,int code_postal,String description,int id) throws SQLException {
        String sql = "update association set nom = ?, telephone = ?, email = ?, lieu = ?,code_postal = ?, description = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nom);
        preparedStatement.setInt(2, telephone);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, lieu);
        preparedStatement.setInt(5, code_postal);
        preparedStatement.setString(6, description);
        preparedStatement.setInt(7, id);
        preparedStatement.executeUpdate();
    }


    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM association WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }


    public List<Association> afficher() throws SQLException {
        String sql = "SELECT * FROM association";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Association> associations = new ArrayList<>();
        while (rs.next()) {
            Association association = new Association();
            association.setId(rs.getInt("id"));
            association.setNom(rs.getString("nom"));
            association.setTelephone(rs.getInt("telephone"));
            association.setEmail(rs.getString("email"));
            association.setLieu(rs.getString("lieu"));
            association.setCode_postal(rs.getInt("code_postal"));
            association.setDescription(rs.getString("description"));
            //association.setPhoto(rs.getString("photo"));
            associations.add(association);
        }
        return associations;
    }

    public void add(Association entity) throws SQLException {

    }
    public  List<Association> chercherAssociationParNom(String nom) throws SQLException {
        List<Association> associations = new ArrayList<>();

        String sql = "SELECT * FROM association WHERE nom  LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Association p = new Association();
                p.setId(rs.getInt("association_id"));
                p.setNom(rs.getString("name"));
                p.setTelephone(rs.getInt("Telephone"));
                p.setEmail(rs.getString("email"));
                p.setLieu(rs.getString("lieu"));
                p.setCode_postal(rs.getInt("code_postal"));
                p.setDescription(rs.getString("description"));
                //p.setImage(rs.getBlob("image"));
                associations.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error while searching for users by email: " + ex.getMessage());
        }

        return associations;
    }
}
