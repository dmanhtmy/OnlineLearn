/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.CourseDAOImpl;
import DAO.Impl.LessonDAOImpl;
import Models.Course;
import Models.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author hp
 */
public class EditLesson extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditLesson</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditLesson at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        LessonDAOImpl l = new LessonDAOImpl();
        String idraw = request.getParameter("id");
        CourseDAOImpl c = new CourseDAOImpl();
        List<Course> CourseList = c.getAllCourse();
        String title_value = "Subject Lesson";
        request.setAttribute("title_value", title_value);
        request.setAttribute("ListCourse1", CourseList);
        try {
            int id = Integer.parseInt(idraw);
            Lesson lesson = l.get(id);
            request.setAttribute("lesson", lesson);
        } catch (Exception e) {
            
        }
        
        request.getRequestDispatcher(request.getContextPath() + "lesson/lessondetail.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String lesson_idraw = request.getParameter("lesson_id");
        String title = request.getParameter("title");
        String typeraw = request.getParameter("type");
        String belongtotopicraw = request.getParameter("belongtotopic");
        String order = request.getParameter("order");
        String statusraw = request.getParameter("status");
        String videolink = request.getParameter("videolink");
        String content = request.getParameter("content");
        LessonDAOImpl ld = new LessonDAOImpl();
        try {
            int lesson_id = Integer.parseInt(lesson_idraw);
            int type = Integer.parseInt(typeraw);
            int belongtotopic = Integer.parseInt(belongtotopicraw);
            boolean status = statusraw.equals("active");
            ld.updateLesson(lesson_id, title, type, belongtotopic, order, status, videolink, content);
        } catch (NumberFormatException e) {
            
        }
        response.sendRedirect("subjectLesson");
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
