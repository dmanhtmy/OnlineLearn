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
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author windc
 */
public class AddSubjectController extends HttpServlet {

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
            out.println("<title>Servlet AddSubjectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSubjectController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher(request.getContextPath() + "/admin/subject/addsubject.jsp").forward(request, response);

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
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddrjnfihc",
                "api_key", "295827132792413",
                "api_secret", "SyPzR-EcBnCG-BSQ5298s4MC9LE"));
        cloudinary.config.secure = true;

        CourseDAOImpl courseDAO = new CourseDAOImpl();
        String message;
        String title = request.getParameter("title");
        Part filePart = request.getPart("thumbnail");
        Part filePartdoc = request.getPart("file");
        String brief = request.getParameter("brief");
        String introduction = request.getParameter("introduction");
        int author_id = Integer.parseInt(request.getParameter("authors"));
        int category_id = Integer.parseInt(request.getParameter("categorys"));
        double listprice = Double.parseDouble(request.getParameter("listprice"));
        double saleprice = Double.parseDouble(request.getParameter("saleprice"));
        boolean status = request.getParameter("status").equals("Publish");
        boolean feature;
        if (request.getParameter("feature") != null && request.getParameter("feature").equals("ON")) {
            feature = true;
        } else {
            feature = false;
        }
        long millis = System.currentTimeMillis();
        Date updateDate = new Date(millis);
        //get thumbnail

        // Retrieves <input type="file" name="thumbnail">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileDocument = filePartdoc.getInputStream();
        String filename = filePartdoc.getSubmittedFileName();

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
        c.setFilename(fileName);
        c.setFeatureflag(feature);
        c.setUpdatedate(updateDate);
        CourseCategory category = new CourseCategory();
        category.setId(category_id);
        c.setCategory(category);

        filePart.write(request.getRealPath("image") + fileName);
        Map path = ObjectUtils.asMap(
                "public_id", "Admin/Subject/images" + c.getCid(),
                "overwrite", true,
                "resource_type", "image"
        );
        Map uploadResult = cloudinary.uploader().upload(request.getRealPath("image") + fileName, path);
        filePart.delete();
        String geturl = uploadResult.get("secure_url").toString();

        int row = courseDAO.insertSubject(c, fileName, fileDocument);
        if (row == 0) {
            message = "Add Unsuccessfull";
        } else {
            message = "Add Successfull";
        }
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getSession().setAttribute("message", message);
        response.sendRedirect("addsubject");

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
