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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class LessonDAOImpl implements LessonDAO{
    private DBContext dbContext = new DBContext();
    private Connection connection = dbContext.getConnection();

    @Override
    public Lesson get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
}
