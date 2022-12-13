/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CourseDAO;
import DAO.UserDAO;
import Models.Course;
import Models.Registration;
import Models.RegistrationStatus;
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

    public DBContext dbContext = new DBContext();
    public Connection connection = dbContext.getConnection();

    public int getTotalMyCourse(int id, String courseName) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 1";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Registration> getMyCourseBySearch(int indexPage, int id, String courseName) {
        ArrayList<Registration> list = new ArrayList<>();
        try {
            String sql = "SELECT c.cid, c.title, r.id, r.registration_time, c.listprice, c.thumbnail, rs.registration_status_id , rs.registrations_status_name FROM onlinelearn.user AS u \n"
                    + "JOIN onlinelearn.user_registrations_course AS urc ON u.id = urc.user_id\n"
                    + "LEFT JOIN onlinelearn.registrations AS r ON urc.registration_id = r.id\n"
                    + "LEFT JOIN onlinelearn.registrations_status AS rs ON r.status_id = rs.registration_status_id\n"
                    + "LEFT JOIN onlinelearn.course AS c ON r.course_id = c.cid \n"
                    + "WHERE (1=1) AND rs.registration_status_id = 1";
            if (courseName != null) {
                sql += "AND c.title LIKE '%" + courseName + "%'";
            }
            sql += " AND u.id = '" + id + "'";
            sql += " ORDER BY c.cid LIMIT 5 OFFSET ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, indexPage * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setId(rs.getInt("r.id"));
                r.setRegistrationTime(rs.getTimestamp("r.registration_time"));
                Course c = new Course();
                c.setCid(rs.getInt("c.cid"));
                c.setTitle(rs.getString("c.title"));
                c.setListprice(rs.getDouble("c.listprice"));
                c.setThumbnail(rs.getString("c.thumbnail"));
                RegistrationStatus rst = new RegistrationStatus();
                rst.setId(rs.getInt("rs.registration_status_id"));
                rst.setName(rs.getString("rs.registrations_status_name"));
                r.setRegistrationStatus(rst);
                r.setCourse(c);
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean registerCourse(User u, Course c, Registration r) {
        try {
            connection.setAutoCommit(false);
            String sql_registration = "INSERT INTO `onlinelearn`.`registrations`\n"
                    + "(`course_id`,\n"
                    + "`registration_time`,\n"
                    + "`totalcost`,\n"
                    + "`status_id`,\n"
                    + "`update_time`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?);";
            PreparedStatement stm_registration = connection.prepareCall(sql_registration);
            stm_registration.setInt(1, c.getCid());
            stm_registration.setTimestamp(2, r.getRegistrationTime());
            stm_registration.setDouble(3, c.getListprice());
            stm_registration.setInt(4, r.getRegistrationStatus().getId());
            stm_registration.setTimestamp(5, r.getUpdateTime());
            stm_registration.executeUpdate();

            String sql_get_identity = "SELECT @@IDENTITY AS registration_id";
            PreparedStatement stm_get_identity = connection.prepareCall(sql_get_identity);
            ResultSet rs_get_identity = stm_get_identity.executeQuery();
            if (rs_get_identity.next()) {
                r.setId(rs_get_identity.getInt("registration_id"));
            }

            String sql_register_course = "INSERT INTO `onlinelearn`.`user_registrations_course`\n"
                    + "(`registration_id`,\n"
                    + "`user_id`)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?);";
            PreparedStatement stm_register_course = connection.prepareCall(sql_register_course);
            stm_register_course.setInt(1, r.getId());
            stm_register_course.setInt(2, u.getId());
            stm_register_course.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public User get(int id) {
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

    public List<User> getAll(int status, String search) {
        List<User> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM onlinelearn.user join onlinelearn.role\n"
                    + "on onlinelearn.user.Role_Id = onlinelearn.role.role_id where (onlinelearn.user.Role_Id = 1 or onlinelearn.user.Role_Id = 4) ";
            if (status != -1) {
                sql = sql + "and onlinelearn.user.status = " + status;
            }
            if (!search.isEmpty() || search == null) {
                sql = sql + " and onlinelearn.user.fullname like '%" + search + "%'";

            }
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

    public void addUser(User u) {
        DBContext dbContext = new DBContext();
        String sql = "INSERT INTO `onlinelearn`.`user`\n"
                + "(\n"
                + "`username`,\n"
                + "`password`,\n"
                + "`fullname`,\n"
                + "`gender`,\n"
                + "`address`,\n"
                + "`email`,\n"
                + "`phonenumber`,\n"
                + "`status`,\n"
                + "`Role_Id`)\n"
                + "VALUES\n"
                + "(?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?)";
        try {

            Connection connection = dbContext.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setString(3, u.getFullname());
            stm.setBoolean(4, u.isGender());
            stm.setString(5, u.getAddress());
            stm.setString(6, u.getEmail());
            stm.setString(7, u.getPhonenumber());
            stm.setInt(8, u.getStatus());
            stm.setInt(9, u.getRole().getRole_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRowcount(int status, int author, String title) {
        try {
            Connection connection = dbContext.getConnection();

            String sql = "SELECT COUNT(*) AS TOTAL FROM onlinelearn.course AS c \n"
                    + "JOIN onlinelearn.user AS u ON c.author = u.id  WHERE (1=1)";
            if (status != -1) {
                sql += " AND c.status ='" + status + "'";
            }
            if (author != -1) {
                sql += " AND c.author = '" + author + "'";
            }
            if (title != null) {
                sql += " AND c.title like '%" + title + "%'";
            }
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("TOTAL");
            }
        } catch (SQLException e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public ArrayList<User> getAllAuthor() {
        try {
            Connection connection = dbContext.getConnection();
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM onlinelearn.user AS u JOIN onlinelearn.role r on u.Role_Id = r.role_id WHERE u.Role_id = 6;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAddress(rs.getString("address"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setGender(rs.getBoolean("gender"));
                user.setPhonenumber(rs.getString("phonenumber"));
                user.setStatus(rs.getInt("status"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                int role_id = rs.getInt("Role_id");
                Role r = new Role();
                r.setRole_id(role_id);
                user.setRole(r);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAOImpl db = new UserDAOImpl();
        List<User> u = db.getAll(3, "");
        for (int i = 0; i < u.size(); i++) {
            System.out.println(u.get(i).getFullname());
        }
        Role r = new Role();
        r.setRole_id(1);
        User us = new User(1, "longgg11", "longgg2", "longg3", true, "hoalac", "long@gmail.com", "123456789", 1, r);
        db.addUser(us);

    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
