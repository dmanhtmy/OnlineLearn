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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
/**
 *
 * @author windc
 */
public class SubjectListController extends HttpServlet {

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
        String raw_subject_status_id = request.getParameter("status");
        String subject_title = request.getParameter("subject_title");
        String index = request.getParameter("index");

        if (raw_author_id == null || raw_author_id.length() == 0) {
            raw_author_id = "-1";
        }
        if (raw_subject_status_id == null || raw_subject_status_id.length() == 0) {
            raw_subject_status_id = "-1";
        }
        if (index == null || index.length() == 0) {
            index = "0";
        }

        int subject_status = Integer.parseInt(raw_subject_status_id);
        int author = Integer.parseInt(raw_author_id);
        //get all author
        UserDAOImpl userDBContext = new UserDAOImpl();
        ArrayList<User> authors = userDBContext.getAllAuthor();
        request.setAttribute("authors", authors);
        //get all coure
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        int totalPageSearch = courseDAO.getRowcount(subject_status, author, subject_title);
        int endPage = totalPageSearch / 3;
        if (totalPageSearch % 3 != 0) {
            endPage++;
        }
        ArrayList<Course> courses = courseDAO.getAllCourseSubject(Integer.parseInt(index), subject_status, author, subject_title);
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);
        request.setAttribute("courses", courses);

        request.setAttribute("subject_title", subject_title);
        request.setAttribute("subject_status", subject_status);
        request.setAttribute("author_id", author);
        
        request.getRequestDispatcher(request.getContextPath() + "/admin/subject/subjectList.jsp").forward(request, response);
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
