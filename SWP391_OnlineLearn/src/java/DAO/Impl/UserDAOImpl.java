/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.UserDAO;
import Models.Role;
import Models.User;
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
 * @author MrTuan
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public User get(int id) {
        DBContext dbContext = new DBContext();
        try {
            String sql = "SELECT * FROM onlinelearn.user inner join onlinelearn.role\n"
                    + "on onlinelearn.user.Role_Id = onlinelearn.role.role_id\n"
                    + "where onlinelearn.user.id = ?";
            Connection connection = dbContext.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setAvatar(rs.getString("avatar"));
                u.setRole(r);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        DBContext dbContext = new DBContext();

        try {
            String sql = "SELECT * FROM onlinelearn.user join onlinelearn.role\n"
                    + "on onlinelearn.user.Role_Id = onlinelearn.role.role_id where onlinelearn.user.Role_Id = 1 or onlinelearn.user.Role_Id = 4; ";
            Connection connection = dbContext.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setGender(rs.getBoolean("gender"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setRole_id(rs.getInt("role_id"));
                r.setRole_name(rs.getString("role_name"));
                u.setRole(r);
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean insert(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getTotalUser() {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "SELECT COUNT(id) AS total FROM onlinelearn.user;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int updateProfileUser(int id, String username, String fullname, Boolean gender, String address, String phone) {
        String sql = "UPDATE `onlinelearn`.`user`\n"
                + "SET\n"
                + "`username` = ?,\n"
                + "`fullname` = ?,\n"
                + "`gender` = ?,\n"
                + "`address` = ?,\n"
                + "`phonenumber` = ?\n"
                + "WHERE `id` = ?;";
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, fullname);
            ps.setBoolean(3, gender);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setInt(6, id);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage() + "loi o day");
        }
        return 0;
    }

    public static void main(String[] args) {
        UserDAOImpl db = new UserDAOImpl();
        List<User> u = db.getAll();
        for (int i = 0; i < u.size(); i++) {
            System.out.println(u.get(i).getFullname());
        }
        db.updateProfileUser(1, "long", "long", true, "Hoa", "0192103131");
        System.out.println(db.getTotalUser());
    }
}
