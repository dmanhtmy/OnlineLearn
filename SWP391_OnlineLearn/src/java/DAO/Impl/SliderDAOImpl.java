/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.SliderDAO;
import Models.Slider;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class SliderDAOImpl implements SliderDAO {

    @Override
    public Slider get(int id) {
        String query = "SELECT * FROM onlinelearn.slider where id = ?";
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Slider(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Slider> getAll(int status) {
        DBContext dbContext = new DBContext();
        ArrayList<Slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.slider";
            if (status != -1) {
                sql = sql + " where onlinelearn.slider.status = " + status;
            }
            Connection connection = dbContext.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setImage(rs.getString("image"));
                s.setBacklink(rs.getString("backlink"));
                s.setStatus(rs.getInt("status"));
                s.setNote(rs.getString("note"));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean insert(Slider t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Slider t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public void updateSlider(int id, String title, String image, String backlink, int status, String note) {
        String query = "UPDATE onlinelearn.slider\n"
                + "SET\n"
                + "title = ?,\n"
                + "image = ?,\n"
                + "backlink = ?,\n"
                + "status = ?,\n"
                + "note = ?\n"
                + "WHERE id = ?";
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, image);
            ps.setString(3, backlink);
            ps.setInt(4, status);
            ps.setString(5, note);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SliderDAOImpl s = new SliderDAOImpl();
        List<Slider> list = s.getAll(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTitle());
        }
        s.updateSlider(1, "a", "a", "a", 0, "a");
    }

    @Override
    public List<Slider> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
