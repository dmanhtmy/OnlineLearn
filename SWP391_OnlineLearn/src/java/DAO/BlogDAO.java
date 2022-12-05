/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Models.BlogList;
import java.util.List;

/**
 *
 * @author MrTuan
 */
public interface BlogDAO extends BaseDAO<BlogList>{
    public List<BlogList> getAll(int page);
    public List<BlogList> search(String name);
}
