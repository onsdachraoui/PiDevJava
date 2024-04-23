package org.example.service;

import org.example.entities.Demande;
import org.example.entities.Demande;
import org.example.service.ICrud;
import org.example.tools.DBconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DemandeService implements ICrud<Demande> {
    Connection cnx2;
    public DemandeService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `demande_service` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Demande p) {
        String req1 = "INSERT INTO `demande_service`( `nom`, `prenom`, `email`, `telephone`,`service_id`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setString(3,p.getEmail());
            st.setInt(4, p.getTelephone());
            st.setInt(5, p.getService_id());

            st.executeUpdate();
            System.out.println("demande_service ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Demande> afficherEntite() {
        return null;
    }

    @Override
    public void modifierEntite(Demande p) {
        String requet = "UPDATE demande_service SET nom=?, prenom=?,telephone=?,email=? WHERE id =?";
        try {
            PreparedStatement st = cnx2.prepareStatement(requet);
            st.setInt(5,p.getId());
            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setInt(3, p.getTelephone());
            st.setString(4, p.getEmail());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Modification réussie");
            } else {
                System.out.println("Aucune modification effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimerEntite(Demande p) {
        String requet = "DELETE FROM demande_service WHERE id =?";
        try {
            PreparedStatement pst = cnx2.prepareStatement(requet);
            pst.setInt(1, p.getId());  // Assuming getQuizId() returns the Quiz ID
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Suppression réussie");
            } else {
                System.out.println("Aucune suppression effectuée. Vérifiez l'ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet Getall() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `demande_service`";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet Getallparticipation(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `demande_service` JOIN `participation` ON demande_service.id = participation.demande_service_id AND participation.user_id="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }
}

