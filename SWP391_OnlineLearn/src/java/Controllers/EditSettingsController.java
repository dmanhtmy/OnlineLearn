/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.SettingsListDAOImpl;
import Models.Setting;
import Models.SettingType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author windc
 */
public class EditSettingsController extends HttpServlet {

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
            out.println("<title>Servlet EditSettingsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSettingsController at " + request.getContextPath() + "</h1>");
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
        String eid = request.getParameter("sid");
        SettingsListDAOImpl dao = new SettingsListDAOImpl();
        Setting s = dao.getSettingById(eid);
        request.setAttribute("st", s);
        ArrayList<SettingType> ltype = dao.listSettingType();
        request.setAttribute("ltype", ltype);
        String title_value = "Edit Setting";
        request.setAttribute("title_value", title_value);
        request.getRequestDispatcher(request.getContextPath() + "/admin/setting/edit.jsp").forward(request, response);
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
        String eid = request.getParameter("id");
        String ename = request.getParameter("name");
        int estatus = Integer.parseInt(request.getParameter("status"));
        String evalue = request.getParameter("value");
        String edescription = request.getParameter("description");
        int etype = Integer.parseInt(request.getParameter("type"));
//        int etype = 1;
        SettingsListDAOImpl dao = new SettingsListDAOImpl();

        dao.updateSetting(eid, ename, edescription, etype, evalue, estatus);
        response.sendRedirect("/admin/settingslist");
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
