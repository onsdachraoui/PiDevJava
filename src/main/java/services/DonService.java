package services;

import entities.Don;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DonService implements IService<Don> {
    Connection connection;
    private Don don;

    public DonService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Don don) throws SQLException {
        String sql = "INSERT INTO don (nom, prenom, email , telephone, type,  montant,mode_de_paiement, categorie, etat, commentaire, label,association_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, don.getNom());
            preparedStatement.setString(2, don.getPrenom());
            preparedStatement.setString(3, don.getEmail());
            preparedStatement.setInt(4, don.getTelephone());
            preparedStatement.setString(5, don.getType());

                preparedStatement.setFloat(6, don.getMontant());
                preparedStatement.setString(7, don.getMode_de_paiement());

                preparedStatement.setString(8, don.getCategorie());
                preparedStatement.setString(9, don.getEtat());
                preparedStatement.setString(10, don.getCommentaire());

            preparedStatement.setInt(12, don.getAssociation_id());
            preparedStatement.executeUpdate();
            System.out.println("Don Ajouté !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void modifier(String nom,int telephone,String email,String lieu,int codePostal,String description,int id) throws SQLException {}
    @Override
    public void modifier(Don don) throws SQLException {
        this.don = don;

        try {
            String req = "UPDATE don SET nom=?, prenom=?, email=?, telephone=?, type=?, montant=?, mode_de_paiement=?, categorie=?, etat=?, commentaire=?, label=?, association_id=? WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
                preparedStatement.setString(1, don.getNom());
                preparedStatement.setString(2, don.getPrenom());
                preparedStatement.setString(3, don.getEmail());
                preparedStatement.setInt(4, don.getTelephone());
                preparedStatement.setString(5, don.getType());

                    preparedStatement.setFloat(6, don.getMontant());
                    preparedStatement.setString(7, don.getMode_de_paiement());

                    preparedStatement.setString(8, don.getCategorie());
                    preparedStatement.setString(9, don.getEtat());
                    preparedStatement.setString(10, don.getCommentaire());
                    preparedStatement.setString(11, don.getLabel());

                preparedStatement.setInt(12, don.getAssociation_id());
                preparedStatement.setInt(13, don.getId()); // Assuming id is the 13th parameter
                preparedStatement.executeUpdate();
                System.out.println("Don Modifie !");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM don WHERE id=?";
        try {
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Don supprimé !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Don> afficher() throws SQLException {
        String sql = "select * from don";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Don> dons = new ArrayList<>();
        while (rs.next()) {
            Don don = new Don();
            don.setId(rs.getInt("id"));
            don.setNom(rs.getString("nom"));
            don.setPrenom(rs.getString("prenom"));
            don.setEmail(rs.getString("email"));
            don.setTelephone(rs.getInt("telephone"));
            don.setType(rs.getString("type"));
            don.setMontant(rs.getFloat("montant"));
            don.setMode_de_paiement(rs.getString("mode_de_paiement"));
            don.setCategorie(rs.getString("categorie"));
            don.setEtat(rs.getString("etat"));
            don.setCommentaire(rs.getString("commentaire"));
            don.setLabel(rs.getString("label"));
            don.setAssociation_id(rs.getInt("association_id"));
            dons.add(don);
        }
        return dons;

    }

    @Override
    public void add(Don entity) throws SQLException {

    }
}






