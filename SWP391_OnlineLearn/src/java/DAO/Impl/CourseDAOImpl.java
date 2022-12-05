/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CourseDAO;
import Models.Course;
import Models.CourseCategory;
import Models.User;
import context.DBContext;
import java.io.InputStream;
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
public class CourseDAOImpl implements CourseDAO {

    private DBContext dbContext = new DBContext();
    private Connection connection = dbContext.getConnection();

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Course> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(Course t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Course t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getRowcount(int status, int author, String title) {
        try {

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

    public int getTotalCourse() {
        try {
            String sql = "SELECT COUNT(cid) AS total FROM onlinelearn.course;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Course> getAllCourseSubject(int index, int status, int author, String title) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT course.cid,\n"
                    + "		`course`.`title`,\n"
                    + "		u.fullname,\n"
                    + "		`course`.`listprice`,"
                    + "          course.thumbnail,\n"
                    + "		`course`.`saleprice`,\n"
                    + "		`course`.`status`\n"
                    + "FROM `onlinelearn`.`course` AS course "
                    + "JOIN onlinelearn.user "
                    + "AS u ON course.author = u.id";
            if (status != -1) {
                sql += " AND course.status ='" + status + "'";
            }
            if (author != -1) {
                sql += " AND course.author = '" + author + "'";
            }
            if (title != null) {
                sql += " AND course.title like '%" + title + "%'";
            }
            sql += " LIMIT 3 OFFSET ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, index * 3);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setTitle(rs.getString("title"));
                User u = new User();
                u.setFullname(rs.getString("fullname"));
                c.setAuthor(u);
                c.setListprice(rs.getDouble("listprice"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }

    public ArrayList<CourseCategory> getAllCourseCategory() {
        ArrayList<CourseCategory> categorys = new ArrayList<>();
        try {
            String sql = "SELECT `subject_category`.`id`,\n"
                    + "    `subject_category`.`category_name`\n"
                    + "FROM `onlinelearn`.`subject_category`";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CourseCategory c = new CourseCategory();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("category_name"));
                categorys.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorys;
    }
    public int insertSubject(Course c, String thumbnail, InputStream fileDocument) {
        int row = 0;
        try {
            String sql = "INSERT INTO `onlinelearn`.`course`\n"
                    + "(`title`,\n"
                    + "`thumbnail`,\n"
                    + "`briefinfo`,\n"
                    + "`featureflag`,\n"
                    + "`author`,\n"
                    + "`introduction`,\n"
                    + "`listprice`,\n"
                    + "`saleprice`,\n"
                    + "`status`,\n"
                    + "`updatedate`,"
                    + "`document`,"
                    + "category_id,"
                    + "filedocumentname)\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,\n"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, c.getTitle());
            stm.setString(2, thumbnail);
            stm.setString(3, c.getBriefinfo());
            stm.setBoolean(4, c.isFeatureflag());
            stm.setInt(5, c.getAuthor().getId());
            stm.setString(6, c.getIntroduction());
            stm.setDouble(7, c.getListprice());
            stm.setDouble(8, c.getSaleprice());
            stm.setBoolean(9, c.isStatus());
            stm.setDate(10, c.getUpdatedate());
            stm.setBlob(11, fileDocument);
            stm.setInt(12, c.getCategory().getId());
            stm.setString(13, c.getFilename());
            row = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}
