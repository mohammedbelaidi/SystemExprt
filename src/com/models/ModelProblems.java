/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.entitys.Problem;
import com.entitys.ProdSolu;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MOHAMMED-PC
 */
public class ModelProblems {
   Connection con = null;
    public Connection ConnData(){
         
         String con_string ="jdbc:mysql://localhost/stytemexpert";
         String user = "root";
        try {
            con = DriverManager.getConnection(con_string, user, "");
        } catch (SQLException ex) {
            Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        System.out.println("connection");
        return con;
     }
    public ModelProblems(){
        con = ConnData();
    }
    public boolean insertProblem(Problem problem){
       boolean bool = false;
       try {
           
           PreparedStatement ps = con.prepareStatement("INSERT INTO `problms` (`codeP`,`symptom`, `type`)"
                   + "VALUES ('"+problem+"'"+problem.getSymptome()+"','"+problem.getType()+"')");
           
           bool = ps.execute(); 
           
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
       return bool;
    }
    public boolean updateProblem(Problem problem){
       boolean bool = false;
       try {
           
           String sql = "UPDATE problms SET symptom = '"+problem.getSymptome()+
                                    "', type = '"+problem.getType()+
                      "' WHERE codeP = '"+problem.getCodeP()+"'";
           PreparedStatement ps = con.prepareStatement(sql);
           bool = ps.execute();
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
       return bool;
    }
    public Problem getProblem(int code){
        Problem problem = null;
        String sql = "SELECT * FROM problms WHERE code = "+code+"";
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           problem = new Problem(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return problem;
    }
     public Problem getProblemBip(String codeP){
        Problem problem = null;
        String sql = "SELECT * FROM problms WHERE codeP = '"+codeP+"' and type ='autre'";
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           problem = new Problem(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return problem;
    }
    public List<Problem> getProblems(){
         Problem problem = null;
        String sql = "SELECT * FROM problms ";
        List<Problem> problems = new ArrayList<>();
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           problem = new Problem(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
           problems.add(problem);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return problems;
    }
    public Problem getProblemBySymtom(String  symptom){
        Problem problem = null;
        String sql = "SELECT * FROM problms WHERE symptom = '"+symptom+"'";
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           problem = new Problem(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return problem;
    }
    public Boolean deleteProblemBySymtom(String  symptom){
        boolean boo = false;
        String sql = "DELETE  FROM problms WHERE symptom = '"+symptom+"'";
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           boo = ps.execute();
           
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return boo;
    }
    public List<Problem> getProblems(String type){
         Problem problem = null;
        String sql = "SELECT * FROM problms WHERE type = '"+type+"' ";
        List<Problem> problems = new ArrayList<>();
       try {
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           problem = new Problem(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
           problems.add(problem);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ModelProblems.class.getName()).log(Level.SEVERE, null, ex);
       }
        return problems;
    }
    public boolean isertProd(int codeP){
        boolean bool = false;
        String sql = "INSERT INTO solu_prob (codeP)  VALUES ("+codeP+")";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             bool = ps.execute();
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return bool;
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
    public List<ProdSolu> getSoluProb(int codeP){
        List<ProdSolu> pses = new ArrayList<>();
        String sql = "SELECT * FROM solu_prob WHERE codeP = "+codeP;
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 ProdSolu ps1 = new ProdSolu(rs.getInt(2), rs.getInt(1));
                 pses.add(ps1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ModelSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
        return pses;
    }
}
