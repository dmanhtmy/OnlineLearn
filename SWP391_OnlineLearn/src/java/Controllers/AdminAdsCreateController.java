/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.AdsDAOImpl;
import Models.Ads;
import Models.User;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MrTuan
 */
@MultipartConfig
public class AdminAdsCreateController extends HttpServlet {

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
            out.println("<title>Servlet AdminAdsCreateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAdsCreateController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            AdsDAOImpl adsDAOImpl = new AdsDAOImpl();
            List<Ads> listAds = adsDAOImpl.getAll();
            request.setAttribute("listAds", listAds);
            request.getRequestDispatcher(request.getContextPath() + "/admin/ads/adsCreate.jsp").forward(request, response);
        }
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
        AdsDAOImpl adsDAOImpl = new AdsDAOImpl();
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddrjnfihc",
                "api_key", "295827132792413",
                "api_secret", "SyPzR-EcBnCG-BSQ5298s4MC9LE"));
        cloudinary.config.secure = true;
        String name_brand = request.getParameter("brand");
        Part filePart = request.getPart("image");
        String link = request.getParameter("link");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        filePart.write(request.getRealPath("image") + fileName);
        Map path = ObjectUtils.asMap(
                "public_id", "Admin/Ads/" + name_brand,
                "overwrite", true,
                "resource_type", "image"
        );
        Map uploadResult = cloudinary.uploader().upload(request.getRealPath("image") + fileName, path);
        filePart.delete();
        String geturl = uploadResult.get("secure_url").toString();
        boolean status = adsDAOImpl.insert(new Ads(name_brand, geturl, link));
        request.setAttribute("status", status);
        response.sendRedirect(request.getContextPath() + "/admin/ads?status=" + status);
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
