/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.CourseDAOImpl;
import DAO.Impl.RegisterDAOImpl;
import Models.Course;
import Models.CourseRegistration;
import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

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
        CourseDAOImpl dao = new CourseDAOImpl();
        RegisterDAOImpl rao = new RegisterDAOImpl();
//        ArrayList<String> userNameExist = rao.getUserNameExist();
//        ArrayList<String> emailExist = rao.getEmailExist();
        List<CourseRegistration> ListRegistedCourse = null;

//        if (request.getSession().getAttribute("user") != null) {
//            User c = (User) request.getSession().getAttribute("user");
//            int user_id = c.getId();
//            ListRegistedCourse = dao.GetListCourseRegistedByUserID(user_id);
//            if (ListRegistedCourse != null) {
//                request.setAttribute("ListStatusCourseRegisted", ListRegistedCourse);
//            }
//        }
//        request.setAttribute("username", userNameExist);
//        request.setAttribute("email", emailExist);

        if (request.getParameter("action") != null && request.getParameter("action").equals("detail") && request.getParameter("id") != null) {
            int cid = Integer.parseInt(request.getParameter("id"));
            Course c = dao.getCourse(cid);
            request.setAttribute("course", c);
            if (ListRegistedCourse != null) {
                for (CourseRegistration cr : ListRegistedCourse) {
                    if (cr.getC().getCid() == cid) {
                        request.setAttribute("status", cr.getStatus());
                        break;
                    }
                }
            }
//            super.loadHeaderAndAsideRight(request, response);
            String title_value = "ECOURSES - COURSE DETAILS";
            request.setAttribute("title_value", title_value);
            request.setAttribute("pageInclude", "/client/detail.jsp");
            request.getRequestDispatcher("/client/index.jsp").forward(request, response);
        } else {
            final int PAGE_SIZE = 6;
            int page = 1;
            String name = "";
            int totalProducts;
            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
                request.setAttribute("urlSearch", "&action=search?name=" + name);
                totalProducts = dao.totalCourse(name);
            } else {
                totalProducts = dao.totalCourse("");
            }

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int totalPage = totalProducts / PAGE_SIZE;
            if (totalProducts % PAGE_SIZE != 0) {
                totalPage++;
            }
            int start = (page - 1) * PAGE_SIZE;

            List<Course> list = dao.getAllCoursePagging(name, start, PAGE_SIZE);
            User user = (User) request.getSession().getAttribute("user");

            //send data
            request.setAttribute("ListCourse", list);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("url", "course?");
            request.setAttribute("user", user);
//            super.loadHeaderAndAsideRight(request, response);
            request.setAttribute("pageInclude", "/client/course.jsp");
            request.getRequestDispatcher("/client/index.jsp").forward(request, response);
        }
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
