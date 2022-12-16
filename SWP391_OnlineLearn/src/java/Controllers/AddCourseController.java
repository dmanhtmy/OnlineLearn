/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.CourseDAOImpl;
import DAO.Impl.UserDAOImpl;
import Models.Course;
import Models.CourseCategory;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class AddCourseController extends HttpServlet {

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
            out.println("<title>Servlet AddCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCourseController at " + request.getContextPath() + "</h1>");
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
        UserDAOImpl userDBContext = new UserDAOImpl();
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        ArrayList<User> authors = userDBContext.getAllAuthor();
        ArrayList<CourseCategory> categorys = courseDAO.getAllCourseCategory();

        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.setAttribute("authors", authors);
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher(request.getContextPath() + "/admin/course/addCourse.jsp").forward(request, response);
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
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        String message;
        String title = request.getParameter("title");
        int author_id = Integer.parseInt(request.getParameter("authors"));
        String brief = request.getParameter("brief");
        double listprice = Double.parseDouble(request.getParameter("listprice"));
        double saleprice = Double.parseDouble(request.getParameter("saleprice"));
        boolean status = request.getParameter("status").equals("Publish");
        String introduction = request.getParameter("introduction");
//        String filename =  request.getParameter("document");
        int category_id = Integer.parseInt(request.getParameter("categorys"));
        boolean feature;
        if (request.getParameter("feature") != null && request.getParameter("feature").equals("ON")) {
            feature = true;
        } else {
            feature = false;
        }
        long millis = System.currentTimeMillis();
        Date updateDate = new Date(millis);
        //get thumbnail
        FileUploadHelper helper = new FileUploadHelper();
        final String path = "C:\\Users\\HP\\Desktop\\OnlineLearn\\SWP391_OnlineLearn\\web\\course_img";
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = helper.getFileName(filePart); // getFilename from file part
        helper.getFileContent(fileName, filePart, path);
        File fileUpload = new File(path + "\\" + fileName);
        String thumbnail = helper.getUrlCloudinaryForAddSubject(fileUpload, fileName);
        //get document
        Part fileDocs = request.getPart("file");
        InputStream fileDocument = fileDocs.getInputStream();
        String filename = fileDocs.getSubmittedFileName();

        //set subject
        Course c = new Course();
        c.setTitle(title);
        User author = new User();
        author.setId(author_id);
        c.setAuthor(author);
        c.setBriefinfo(brief);
        c.setListprice(listprice);
        c.setSaleprice(saleprice);
        c.setStatus(status);
        c.setIntroduction(introduction);
        c.setFilename(filename);
        c.setFeatureflag(feature);
        c.setUpdatedate(updateDate);
        CourseCategory category = new CourseCategory();
        category.setId(category_id);
        c.setCategory(category);
        int row = courseDAO.insertSubject(c, thumbnail, fileDocument);

        if (row == 0) {
            message = "Add Failed";
        } else {
            message = "Add Successfull";
        }
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getSession().setAttribute("message", message);
        response.sendRedirect("addCourse");
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
