/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.CourseDAOImpl;
import DAO.Impl.UserDAOImpl;
import Models.Course;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class AdminCourseListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_author_id = request.getParameter("author");
        String raw_course_status_id = request.getParameter("status");
        String course_title = request.getParameter("subject_title");
        String index = request.getParameter("index");

        if (raw_author_id == null || raw_author_id.length() == 0) {
            raw_author_id = "-1";
        }
        if (raw_course_status_id == null || raw_course_status_id.length() == 0) {
            raw_course_status_id = "-1";
        }
        if (index == null || index.length() == 0) {
            index = "0";
        }

        int course_status = Integer.parseInt(raw_course_status_id);
        int author = Integer.parseInt(raw_author_id);
        //get all author
        UserDAOImpl userDBContext = new UserDAOImpl();
        ArrayList<User> authors = userDBContext.getAllAuthor();
        request.setAttribute("authors", authors);
        //get all coure
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        int totalPageSearch = courseDAO.getRowcount(course_status, author, course_title);
        int endPage = totalPageSearch / 3;
        if (totalPageSearch % 3 != 0) {
            endPage++;
        }
        ArrayList<Course> courses = courseDAO.getAllCourseSubject(Integer.parseInt(index), course_status, author, course_title);
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("courses", courses);

        request.setAttribute("course_title", course_title);
        request.setAttribute("subject_status", course_status);
        request.setAttribute("author_id", author);
        
        request.getRequestDispatcher(request.getContextPath() + "/admin/course/courseList.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
