package org.example.service;

import org.example.entities.Service;
import org.example.tools.DBconnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceService implements ICrud<Service>{
    Connection cnx2;
    public ServiceService() {
        cnx2 = DBconnexion.getInstance().getCnx();
    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `service` WHERE `id` ="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    @Override
    public void ajouterEntite(Service p) {
        String req1 = "INSERT INTO `service`( `nom`, `categorie`, `description`, `duree_service`) VALUES (?,?,?,?)";
        try {
            PreparedStatement st = cnx2.prepareStatement(req1);
            st.setString(1, p.getNom());
            st.setString(2, p.getCategorie());
            st.setString(3,p.getDescription());
            st.setString(4, p.getDuree_service());
            st.executeUpdate();
            System.out.println("service ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Service> afficherEntite() {
        return null;
    }

    @Override
    public void modifierEntite(Service p) {
        String requet = "UPDATE service SET nom=?, categorie=?,description=?,duree_service=? WHERE id =?";
        try {
            PreparedStatement st = cnx2.prepareStatement(requet);
            st.setInt(5,p.getId());
            st.setString(1, p.getNom());
            st.setString(2, p.getCategorie());
            st.setString(3, p.getDescription());
            st.setString(4, p.getDuree_service());
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
    public void supprimerEntite(Service p) {
        String requet = "DELETE FROM service WHERE id =?";
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
            String req = "SELECT * FROM `service`";
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet Getallparticipation(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `service` JOIN `participation` ON service.id = participation.service_id AND participation.user_id="+id;
            PreparedStatement st = cnx2.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }
}

