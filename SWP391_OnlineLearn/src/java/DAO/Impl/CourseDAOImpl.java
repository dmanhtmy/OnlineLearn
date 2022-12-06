/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CourseDAO;
import Models.Course;
import Models.CourseRegistration;
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
public class CourseDAOImpl implements CourseDAO {

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

    public Course getCourse(int course_id) {
        DBContext dbContext = new DBContext();
        String sql_Course = "SELECT * FROM onlinelearn.course WHERE cid = ?";
        Course c = new Course();
        try {
            Connection connection = dbContext.getConnection();
            PreparedStatement stm_Course = connection.prepareStatement(sql_Course);
            stm_Course.setInt(1, course_id);
            ResultSet rs = stm_Course.executeQuery();
            while (rs.next()) {
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<Course> getAllCoursePagging(String name, int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "SELECT  c.cid, c.title, c.thumbnail , c.briefinfo, c.introduction, c.listprice, c.saleprice, c.status, c.featureflag, c.updatedate, u.fullname, c.status\n"
                    + "FROM onlinelearn.course as c join onlinelearn.user as u ON c.author = u.id "
                    + "AND c.status=1 ";
            if (name != "") {
                sql += "AND c.title LIKE '%" + name + "%' \n";
            }
            sql += "order by c.cid LIMIT " + pageIndex + "," + pageSize;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                User u = new User();
                u.setFullname(rs.getString("fullname"));
                c.setAuthor(u);
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error at pagging course");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTotalCourse() {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
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

    public int totalCourse(String name) {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "select count(cid) as total from onlinelearn.course ";
            if (name != "") {
                sql += "WHERE course.title LIKE '%" + name + "%'";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<CourseRegistration> GetListCourseRegistedByUserID(int userId) {
        List<CourseRegistration> list = new ArrayList<>();
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "Select distinct c.*, r.status_id\n"
                    + "From onlinelearn.user_registrations_course as urc, onlinelearn.registrations as r,\n"
                    + "onlinelearn.course as c\n"
                    + "where urc.registration_id = r.id\n"
                    + "and r.course_id = c.cid\n"
                    + "and urc.user_id = ? ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt(1));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                int status_id = rs.getInt("status_id");
                list.add(new CourseRegistration(c, status_id));
            }
        } catch (Exception e) {
            System.out.println("Error at GetListCourseRegistedByUserID");
            System.out.println(e.getMessage());
        }

        return list;
    }

}
