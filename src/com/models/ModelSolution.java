/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.entitys.Solution;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MOHAMMED-PC
 */
public class ModelSolution {
     Connection con = null;
    public Connection ConnData(){
         
         String con_string ="jdbc:mysql://localhost/stytemexpert";
         String user = "root";
        try {
            con = DriverManager.getConnection(con_string, user, "");
        } catch (SQLException ex) {
            Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("connection");
        return con;
     }
    public ModelSolution(){
        ConnData();
    }
    public int isertSolution(Solution solution){
        boolean bool = false;
        int code = 0;
        String sql = "INSERT INTO solutions (description) VALUES ('"+solution.getDescription()+"') ";
         try {
             Statement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             bool = stmt.execute(sql);
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return code;
    }
    public boolean updateSolution(Solution solution){
        boolean bool = false;
        String sql = "UPDATE solutions SET codeS = "+solution.getCodeS()+",description = '"+solution.getDescription()
                +"' WHERE codeS = "+solution.getCodeS();
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             bool = ps.execute();
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return bool;
    }
    public Solution getSolutionByDescription(String description){
        Solution solution = null;
        String sql = "SELECT * FROM solutions WHERE  description='" + description +"'";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 solution = new Solution(rs.getInt(1), rs.getString(2));
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return solution;
    }
    public Solution getSolutionByCode(int codeS){
        Solution solution = null;
        String sql = "SELECT * FROM solutions WHERE  codeS =" + codeS +"";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 solution = new Solution(rs.getInt(1), rs.getString(2));
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return solution;
    }
    public int deleteSolution(String description){
        boolean bool = false;
        int code = 0;
        String sql = "DELETE FROM solutions WHERE description = '" + description + "'";
         try {
             Statement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             bool = stmt.execute(sql);
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        return code;
    }
    public List<Solution> getSolutions(){
        Solution solution = null;
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solutions ";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 solution = new Solution(rs.getInt(1), rs.getString(2));
                 solutions.add(solution);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return solutions;
    }
     public boolean isertSoluProb(int codeS,int codeP){
        boolean bool = false;
        String sql = "INSERT INTO solu_prob  VALUES ("+codeS+","+codeP+")";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             bool = ps.execute();
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return bool;
    }
      public boolean isertSolu(int codeS){
        boolean bool = false;
        String sql = "INSERT INTO solu_prob (codeS) VALUES ("+codeS+")";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             bool = ps.execute();
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return bool;
    }
}
