/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.AdsDAOImpl;
import Models.Ads;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author MrTuan
 */
public class AdminAdsChangeModeController extends HttpServlet {

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
            out.println("<title>Servlet AdminAdsChangeModeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAdsChangeModeController at " + request.getContextPath() + "</h1>");
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
            int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
            String indexpasge = request.getParameter("page");
            if (indexpasge == null) {
                indexpasge = "1";
                int page = Integer.parseInt(indexpasge);
                int count = adsDAOImpl.getTotalBlog();
                int endpage = count / 5;
                if (count % 5 != 0) {
                    endpage++;
                }
                Ads ads = adsDAOImpl.get(id);
                Ads adsInAdsList = adsDAOImpl.get();
                if (adsInAdsList == null) {
                    boolean status = adsDAOImpl.insertToAdsList(new Ads(1, ads.getName_brand(), ads.getImage(), ads.getHref()));
                    List<Ads> listAds = adsDAOImpl.getAll(page);
                    request.setAttribute("blogs", listAds);
                    request.setAttribute("endpage", endpage);
                    response.sendRedirect(request.getContextPath() + "/admin/ads?status=" + status);
                } else {
                    boolean status = adsDAOImpl.updateToAdsList(new Ads(1, ads.getName_brand(), ads.getImage(), ads.getHref()));
                    List<Ads> listAds = adsDAOImpl.getAll(page);
                    request.setAttribute("blogs", listAds);
                    request.setAttribute("endpage", endpage);
                    response.sendRedirect(request.getContextPath() + "/admin/ads?status=" + status);
                }
            } else {
                int page = Integer.parseInt(indexpasge);
                int count = adsDAOImpl.getTotalBlog();
                int endpage = count / 5;
                if (count % 5 != 0) {
                    endpage++;
                }
                Ads ads = adsDAOImpl.get(id);
                Ads adsInAdsList = adsDAOImpl.get();
                if (adsInAdsList == null) {
                    boolean status = adsDAOImpl.insertToAdsList(new Ads(1, ads.getName_brand(), ads.getImage(), ads.getHref()));
                    List<Ads> listAds = adsDAOImpl.getAll(page);
                    request.setAttribute("blogs", listAds);
                    request.setAttribute("endpage", endpage);
                    response.sendRedirect(request.getContextPath() + "/admin/ads?status=" + status);
                } else {
                    boolean status = adsDAOImpl.updateToAdsList(new Ads(1, ads.getName_brand(), ads.getImage(), ads.getHref()));
                    List<Ads> listAds = adsDAOImpl.getAll(page);
                    request.setAttribute("blogs", listAds);
                    request.setAttribute("endpage", endpage);
                    response.sendRedirect(request.getContextPath() + "/admin/ads?status=" + status);
                }
            }
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
