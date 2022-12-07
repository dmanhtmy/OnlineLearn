/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.Impl.SettingsListDAOImpl;
import Models.Setting;
import Models.SettingStatus;
import Models.SettingType;
//import Models.User;
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
public class SettingsListController extends HttpServlet {

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
        String raw_type = request.getParameter("type");
        String raw_status = request.getParameter("status");
        String raw_setting_value = request.getParameter("setting_value");
        String setting_value;
        if (raw_setting_value != null) {
            setting_value = raw_setting_value;
        } else {
            setting_value = null;
        }
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "0";
        }
        int index = Integer.parseInt(indexPage);
        if (raw_type == null || raw_type.length() == 0) {
            raw_type = "-1";
        }
        if (raw_status == null || raw_status.length() == 0) {
            raw_status = "-1";
        }
        int type = Integer.parseInt(raw_type);
        int status = Integer.parseInt(raw_status);

        SettingsListDAOImpl sldbc = new SettingsListDAOImpl();

        int count = sldbc.getTotalSettings(type, status, setting_value);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);

        ArrayList<SettingType> settingTypes = sldbc.listType();
        request.setAttribute("settingTypesList", settingTypes);

        ArrayList<SettingStatus> settingStatus = sldbc.listSettingStatus();
        request.setAttribute("settingStatusList", settingStatus);

        request.setAttribute("type_id", type);
        request.setAttribute("status_id", status);

        ArrayList<Setting> listBySearch = sldbc.getListSettingBySearch(index, type, status, setting_value);
        request.setAttribute("listBySearch", listBySearch);

        request.getRequestDispatcher(request.getContextPath() + "/admin/setting/settingslist.jsp").forward(request, response);
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
        String raw_type = request.getParameter("type");
        String raw_status = request.getParameter("status");
        String raw_setting_value = request.getParameter("setting_value");
        String setting_value;
        if (raw_setting_value != null) {
            setting_value = raw_setting_value;
        } else {
            setting_value = null;
        }
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "0";
        }
        int index = Integer.parseInt(indexPage);
        if (raw_type == null || raw_type.length() == 0) {
            raw_type = "-1";
        }
        if (raw_status == null || raw_status.length() == 0) {
            raw_status = "-1";
        }
        int type = Integer.parseInt(raw_type);
        int status = Integer.parseInt(raw_status);

        SettingsListDAOImpl sldbc = new SettingsListDAOImpl();

        int count = sldbc.getTotalSettings(type, status, setting_value);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("current_index", index);
        request.setAttribute("endPage", endPage);

        ArrayList<SettingType> settingTypes = sldbc.listType();
        request.setAttribute("settingTypesList", settingTypes);

        ArrayList<SettingStatus> settingStatus = sldbc.listSettingStatus();
        request.setAttribute("settingStatusList", settingStatus);

        request.setAttribute("type_id", type);
        request.setAttribute("status_id", status);

        ArrayList<Setting> listBySearch = sldbc.getListSettingBySearch(index, type, status, setting_value);
        request.setAttribute("listBySearch", listBySearch);

        request.getRequestDispatcher(request.getContextPath() + "/admin/setting/settingslist.jsp").forward(request, response);
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
