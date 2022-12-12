/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Models.Ads;
import java.util.List;

/**
 *
 * @author MrTuan
 */
public interface AdsDAO extends BaseDAO<Ads>{
    public Ads get();
    public List<Ads> search(String name);
    public List<Ads> getAll(int page);
    public boolean insertToAdsList(Ads t);
    public boolean updateToAdsList(Ads t);
}
