/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.LessonDAO;
import Models.Lesson;
import Models.Topic;
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
 * @author HP
 */
public class LessonDAOImpl implements LessonDAO {

    private DBContext dbContext = new DBContext();
    private Connection connection = dbContext.getConnection();

    @Override
    public Lesson get(int id) {
        Lesson l = new Lesson();
        try {
            String sql = "SELECT * FROM onlinelearn.lesson WHERE lesson_id= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.setLesson_id(rs.getInt("lesson_id"));
                l.setTitle(rs.getString("title"));
                l.setType(rs.getInt("type"));
                l.setBelongToTopic(rs.getInt("belongtotopic"));
                l.setOder(rs.getString("order"));
                l.setStatus(rs.getBoolean("status"));
                l.setContent(rs.getString("Content"));
                l.setVideolink(rs.getString("videolink"));
            }
        } catch (Exception e) {
            System.out.println("Error at getListTopicByCourseId");
            System.out.println(e.getMessage());
        }

        return l;
    }

    @Override
    public List<Lesson> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(Lesson t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Lesson t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Topic> getListTopicByCourseId(int cid) {
        List<Topic> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.topic WHERE course_id= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topic t = new Topic();
                t.setId(rs.getInt("topic_id"));
                t.setTopic_title(rs.getString("topic_title"));
                t.setQuiz_id(rs.getInt("quiz_id"));
                t.setCourse_id(rs.getInt("course_id"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error at getListTopicByCourseId");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public List<Lesson> getLessonByTopicId(int tid) {
        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.lesson WHERE belongtotopic=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("lesson_id"),
                        rs.getString("title"),
                        rs.getInt("type"),
                        rs.getInt("belongtotopic"),
                        rs.getString("order"),
                        rs.getBoolean("status"),
                        rs.getString("videolink"),
                        rs.getString("content")
                );
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("Error at getLessonByTopicId");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public int changeLessonStatus(int lesson_id, boolean lesson_status) {
        try {
            String sql = "UPDATE `onlinelearn`.`lesson`\n"
                    + "SET\n"
                    + "`status` = ? \n"
                    + "WHERE `lesson_id` = ? ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, lesson_status);
            stm.setInt(2, lesson_id);
            return stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Change lesson status err: ");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void updateLesson(int id, String title, int type, int belongtotopic, String order,
            boolean status, String videolink, String content) {
        String query = "UPDATE `onlinelearn`.`lesson`\n"
                + "SET\n"
                + "`title` = ?,\n"
                + "`type` = ?,\n"
                + "`belongtotopic` = ?,\n"
                + "`order` = ?,\n"
                + "`status` = ?,\n"
                + "`videolink` = ?,\n"
                + "`content` = ?\n"
                + "WHERE `lesson_id` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setInt(2, type);
            ps.setInt(3, belongtotopic);
            ps.setString(4, order);
            ps.setBoolean(5, status);
            ps.setString(6, videolink);
            ps.setString(7, content);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        LessonDAOImpl l = new LessonDAOImpl();
        l.updateLesson(1,"giai long",1,1,"1",true,"long","long");
     
    }

}
