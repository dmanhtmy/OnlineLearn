/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.BlogDAOImpl;
import Models.BlogList;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author MrTuan
 */
public class BlogDetailController extends HttpServlet {

    protected void loadHeaderAndAsideRight(HttpServletRequest request, HttpServletResponse response) {
        String login_href_value = "";
        String logout_href = "home";
        if (request.getSession().getAttribute("user") == null) {
            return;
        } else {
            User user = (User) request.getSession().getAttribute("user");
            login_href_value += user.getFullname();
        }
        request.setAttribute("login_href_value", login_href_value);
        request.setAttribute("logout_href", logout_href);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadHeaderAndAsideRight(request, response);
        BlogDAOImpl blogDAO = new BlogDAOImpl();
        User user = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("user", user);
        BlogList getDetail = blogDAO.get(id);
        request.setAttribute("getdetail", getDetail);
        request.getRequestDispatcher(request.getContextPath() + "/client/blog/blogDetail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
    }

}
