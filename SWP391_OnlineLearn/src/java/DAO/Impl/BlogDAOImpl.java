/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Impl;

import DAO.BlogDAO;
import Models.BlogCategory;
import Models.BlogList;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mr Tuan
 */
public class BlogDAOImpl implements BlogDAO {

    @Override
    public BlogList get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BlogList> getAll() {
        DBContext dbContext = new DBContext();
        List<BlogList> listBlog = new ArrayList<>();
        try {
            Connection connection = dbContext.getConnection();
            String sql = "select * from onlinelearn.blog_list;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogCategory categoryId = new BlogCategory();
                BlogList blogList = new BlogList();
                blogList.setId(rs.getInt("id"));
                blogList.setTitle(rs.getString("title"));
//                blogList.setCategory_id(categoryId.getId());
//                blogList.setCategory_id(rs.getObject("category_id", categoryId.));
                blogList.setPostdate(rs.getDate("postdate"));
                blogList.setBrief_info(rs.getString("brief_info"));
                blogList.setThumbnail(rs.getString("thumbnail"));
                listBlog.add(blogList);
            }

        } catch (SQLException e) {
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
    public static void main(String[] args) {
        BlogDAOImpl db=new BlogDAOImpl();
        List<BlogList> listBl=db.getAll();
        for (BlogList blogList : listBl) {
            System.out.println(blogList);
        }
    }

}
