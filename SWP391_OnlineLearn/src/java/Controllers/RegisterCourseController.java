/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.CourseDAOImpl;
import DAO.Impl.UserDAOImpl;
import Models.Course;
import Models.Registration;
import Models.RegistrationStatus;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author windc
 */
public class RegisterCourseController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void loadHeaderAndAsideRight(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login_href_value = "";
        String logout_href = "";
        if (request.getSession().getAttribute("user") == null) {

        } else {
            User user = (User) request.getSession().getAttribute("user");
            login_href_value += user.getFullname();
        }
        request.setAttribute("login_href_value", login_href_value);
        request.setAttribute("logout_href", logout_href);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        loadHeaderAndAsideRight(request, response);
        User u = (User) request.getSession().getAttribute("user");
        UserDAOImpl userCourseDAO = new UserDAOImpl();
        int courseId = Integer.parseInt(request.getParameter("id"));
        String notification = "";
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        Course c = courseDAO.getCourse(courseId);
        Registration r = new Registration();
        r.setRegistrationTime(timestamp);
        RegistrationStatus rs = new RegistrationStatus();
        rs.setId(3);
        r.setRegistrationStatus(rs);
        r.setUpdateTime(timestamp);
        boolean check = userCourseDAO.registerCourse(u, c, r);
        if (check == true) {
            notification = "Enroll Course Successfully";
        } else {
            notification = "Enroll Fail";
        }
        request.getSession().setAttribute("user", u);
        loadHeaderAndAsideRight(request, response);
        response.sendRedirect("../course");
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
