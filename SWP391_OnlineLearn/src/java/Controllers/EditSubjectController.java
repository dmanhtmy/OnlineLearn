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
import jakarta.servlet.annotation.MultipartConfig;
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
 * @author windc
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB 
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100)   	// 100 MB
public class EditSubjectController extends HttpServlet {

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
            out.println("<title>Servlet EditSubjectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSubjectController at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("coursesubjectid"));
        UserDAOImpl userDBContext = new UserDAOImpl();

        ArrayList<User> authors = userDBContext.getAllAuthor();
        User user = (User) request.getSession().getAttribute("user");
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        Course course = courseDAO.getCourseById(id);
        ArrayList<CourseCategory> categorys = courseDAO.getAllCourseCategory();
        request.setAttribute("user", user);
        request.setAttribute("authors", authors);
        request.setAttribute("course", course);
        request.setAttribute("categorys", categorys);
        String title_value = "Edit Subject";
        request.setAttribute("title_value", title_value);
        request.getRequestDispatcher(request.getContextPath() + "/admin/subject/editSubject.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("cidi"));
        String title = request.getParameter("title");
        int author_id = Integer.parseInt(request.getParameter("authors"));
        String brief = request.getParameter("brief");
        double listprice = Double.parseDouble(request.getParameter("listprice"));
        double saleprice = Double.parseDouble(request.getParameter("saleprice"));
        boolean status = request.getParameter("status").equals("Publish");
        String introduction = request.getParameter("introduction");
        System.out.println("aaaa" + id);
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
       //get thumbnail
        FileUploadHelper helper = new FileUploadHelper();
        final String path = "C:\\Users\\HP\\Desktop\\OnlineLearn\\SWP391_OnlineLearn\\web\\course_img";
        Part filePart = request.getPart("thumbnail"); // Retrieves <input type="file" name="thumbnail">
        String fileName = helper.getFileName(filePart); // getFilename from file part
        String thumbnail = null;
        helper.getFileContent(fileName, filePart, path);
        File fileUpload = new File(path + "\\" + fileName);
        if(!filePart.getSubmittedFileName().isEmpty()){
            thumbnail = helper.getUrlCloudinaryForEditSubject(fileUpload,fileName);  
        }
        //get document
        InputStream fileDocument = null;
        Part fileDocs = request.getPart("document");
        if (!fileDocs.getSubmittedFileName().isEmpty()) {
            fileDocument = fileDocs.getInputStream();
        }
        String filename = fileDocs.getSubmittedFileName();
        //set subject
        Course c = new Course();
        c.setCid(id);
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
        int row = courseDAO.updateSubject(c, thumbnail, fileDocument);

        if (row == 0) {
            message = "Edit Unsuccessfull";
        } else {
            message = "Edit Successfull";
        }

        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getSession().setAttribute("message", message);
        response.sendRedirect("editcoursesubject?coursesubjectid=" + id);
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
