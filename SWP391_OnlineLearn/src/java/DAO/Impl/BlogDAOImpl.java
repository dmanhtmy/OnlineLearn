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
    public List<BlogList> search(String name) {
        DBContext dBContext = new DBContext();
        List<BlogList> listBlog = new ArrayList<>();
        try {
            Connection connection = dBContext.getConnection();
            String sql = "SELECT * FROM onlinelearn.blog_list bl\n"
                    + "WHERE bl.title LIKE ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, '%'+name+'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BlogList blog = new BlogList();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setBrief_info(rs.getString("brief_info"));
                blog.setPostdate(rs.getDate("postdate"));
                listBlog.add(blog);
            }
            dBContext.closeConnection(connection, ps, rs);
        } catch (SQLException e) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, e);
        }
        return listBlog;
    }

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
                BlogList blog = new BlogList();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setBrief_info(rs.getString("brief_info"));
                blog.setPostdate(rs.getDate("postdate"));
                listBlog.add(blog);
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlog;
    }

    @Override
    public boolean insert(BlogList t) {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "INSERT INTO onlinelearn.blog_list(title, category_id, postdate,brief_info,thumbnail,post_content)\n"
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, t.getTitle());
            stm.setInt(2, t.getCategory_id());
            stm.setDate(3, t.getPostdate());
            stm.setString(4, t.getBrief_info());
            stm.setString(5, t.getThumbnail());
            stm.setString(6, t.getBlogdetail());
            stm.executeUpdate();
            dbContext.closeConnection(connection, stm);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(BlogList t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        DBContext dbContext = new DBContext();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "DELETE FROM onlinelearn.blog_list bl \n"
                    + "WHERE bl.id=?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            dbContext.closeConnection(connection, stm);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    @Override
    public List<BlogList> getAll(int page) {
        DBContext dbContext = new DBContext();
        List<BlogList> listBlog = new ArrayList<>();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "select * from onlinelearn.blog_list order by id limit ?, 5;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * 5);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BlogList blog = new BlogList();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setBrief_info(rs.getString("brief_info"));
                blog.setPostdate(rs.getDate("postdate"));
                listBlog.add(blog);
            }
        } catch (SQLException e) {
            Logger.getLogger(BlogList.class.getName()).log(Level.SEVERE, null, e);
        }
        return listBlog;
    }
    

    public static void main(String[] args) {
        BlogDAOImpl db = new BlogDAOImpl();
        List<BlogList> list=db.getAll();
        for (BlogList blogList : list) {
            System.out.println(blogList.getCategory_id());
        }
    }

}
