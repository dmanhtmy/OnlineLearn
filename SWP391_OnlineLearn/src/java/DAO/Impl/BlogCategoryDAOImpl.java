/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.BlogCategoryDAO;
import Models.CategoryBlog;
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
public class BlogCategoryDAOImpl implements BlogCategoryDAO {

    @Override
    public CategoryBlog get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CategoryBlog> getAll() {
        DBContext dbContext = new DBContext();
        List<CategoryBlog> listBlogCategory = new ArrayList<>();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "SELECT * FROM onlinelearn.blog_category;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CategoryBlog categoryBlog = new CategoryBlog();
                categoryBlog.setId(rs.getInt("id"));
                categoryBlog.setName(rs.getString("name"));
                listBlogCategory.add(categoryBlog);
            }
            dbContext.closeConnection(connection, stm, rs);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlogCategory;
    }

    @Override
    public boolean insert(CategoryBlog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(CategoryBlog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        BlogCategoryDAOImpl db = new BlogCategoryDAOImpl();
        List<CategoryBlog> list = db.getAll();
        for (CategoryBlog categoryBlog : list) {
            System.out.println(categoryBlog.getName());
        }

    }
}
