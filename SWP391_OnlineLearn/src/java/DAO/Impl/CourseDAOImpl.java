/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.CourseDAO;
import Models.Course;
import Models.CourseCategory;
import Models.CourseRegistration;
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

    public int updateSubject(Course c, String thumbnail, InputStream fileDocument) {
        int row = 0;
        try {
            String sql = "UPDATE `onlinelearn`.`course`\n"
                    + "SET\n"
                    + "`title` = ?,\n"
                    + "`briefinfo` = ?,\n"
                    + "`author` = ?,\n"
                    + "`introduction` = ?,\n"
                    + "`listprice` = ?,\n"
                    + "`saleprice` = ?,\n"
                    + "`status` = ?,\n"
                    + "`featureflag` = ?,\n"
                    + "`updatedate` = ?\n";
            if (thumbnail != null) {
                sql += ",`thumbnail` = ?\n";
            }
            if (fileDocument != null) {
                sql += ",`document` = ?, filedocumentname = ? \n";
            }
            sql += " WHERE `cid` = ?;";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, c.getTitle());
            stm.setString(2, c.getBriefinfo());
            stm.setInt(3, c.getAuthor().getId());
            stm.setString(4, c.getIntroduction());
            stm.setDouble(5, c.getListprice());
            stm.setDouble(6, c.getSaleprice());
            stm.setBoolean(7, c.isStatus());
            stm.setBoolean(8, c.isFeatureflag());
            stm.setDate(9, c.getUpdatedate());
            if (thumbnail != null && fileDocument != null) {
                stm.setString(10, thumbnail);
                stm.setBlob(11, fileDocument);
                stm.setString(12, c.getFilename());
                stm.setInt(13, c.getCid());
            } else if (thumbnail != null && fileDocument == null) {
                stm.setString(10, thumbnail);
                stm.setInt(11, c.getCid());
            } else if (thumbnail == null && fileDocument != null) {
                stm.setBlob(10, fileDocument);
                stm.setString(11, c.getFilename());
                stm.setInt(12, c.getCid());
            } else {
                stm.setInt(10, c.getCid());
            }
            row = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public Course getCourseById(int id) {
        try {
            String sql = "SELECT `course`.`cid`,\n"
                    + "    `course`.`title`,\n"
                    + "    `course`.`thumbnail`,\n"
                    + "    `course`.`briefinfo`,\n"
                    + "    `course`.`author`,\n"
                    + "    `course`.`introduction`,\n"
                    + "    `course`.`listprice`,\n"
                    + "    `course`.`saleprice`,\n"
                    + "    `course`.`status`,\n"
                    + "    `course`.`featureflag`,\n"
                    + "    `course`.`document`,\n"
                    + "course.filedocumentname\n"
                    + "FROM `onlinelearn`.`course` WHERE cid = ?;";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Course c = new Course();
                c.setCid(rs.getInt("cid"));
                c.setTitle(rs.getString("title"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setBriefinfo(rs.getString("briefinfo"));
                User u = new User();
                u.setId(rs.getInt("author"));
                c.setAuthor(u);
                c.setIntroduction(rs.getString("introduction"));
                c.setListprice(rs.getDouble("listprice"));
                c.setSaleprice(rs.getDouble("saleprice"));
                c.setStatus(rs.getBoolean("status"));
                c.setFeatureflag(rs.getBoolean("featureflag"));
                c.setDocument(rs.getBlob("document"));
                c.setFilename(rs.getString("filedocumentname"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateStatus(int id, int status) {
        try {
            String sql = "UPDATE `onlinelearn`.`course`\n"
                    + "SET\n"
                    + "`status` = ?\n"
                    + "WHERE `cid` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (status == 1) {
                stm.setBoolean(1, false);
            } else {
                stm.setBoolean(1, true);
            }
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public Course getCourse(int course_id) {
        String sql_Course = "SELECT * FROM onlinelearn.course WHERE cid = ?";
        Course c = new Course();
        try {
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
        try {
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

    public int totalCourse(String name) {
        try {
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

    public List<CourseRegistration> getListCourseRegistedByUserID(int userId) {
        List<CourseRegistration> list = new ArrayList<>();

        try {
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
    
    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT  c.cid, c.title, c.thumbnail , c.briefinfo, c.introduction, c.listprice, c.saleprice, c.status, c.featureflag, c.updatedate, u.fullname, c.status\n"
                    + "FROM onlinelearn.course as c join onlinelearn.user as u ON c.author = u.id "
                    + "AND c.status=1 ";
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
        } catch (Exception e) {
            System.out.println("Error at getAllCourse: ");
            System.out.println(e.getMessage());
        }

        return list;
    }
}
