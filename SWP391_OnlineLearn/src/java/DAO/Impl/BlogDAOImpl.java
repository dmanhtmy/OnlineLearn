/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.BlogDAO;
import Models.BlogList;
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
public class BlogDAOImpl implements BlogDAO {

    @Override
    public BlogList get(int id) {
        DBContext dbContext = new DBContext();
        BlogList blog = new BlogList();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "select bl.id,bl.title,bl.brief_info,bl.thumbnail,bl.postdate,bl.post_content from onlinelearn.blog_list bl\n"
                    + "where bl.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setBrief_info(rs.getString("brief_info"));
                blog.setThumbnail(rs.getString("thumbnail"));
                blog.setPostdate(rs.getDate("postdate"));
                blog.setBlogdetail(rs.getString("post_content"));
                return blog;
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException e) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public List<BlogList> getAll() {
        DBContext dbContext = new DBContext();
        List<BlogList> listBlog = new ArrayList<>();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "SELECT b.id, b.title,b.brief_info,b.postdate FROM onlinelearn.blog_list as b;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogList blogs = new BlogList();
                blogs.setId(rs.getInt("id"));
                blogs.setTitle(rs.getString("title"));
                blogs.setBrief_info(rs.getString("brief_info"));
                blogs.setPostdate(rs.getDate("postdate"));
                listBlog.add(blogs);
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlog;
    }

    @Override
    public boolean insert(BlogList t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(BlogList t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getTotalBlog() {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "SELECT COUNT(id) AS total FROM onlinelearn.blog_list;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
