/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.entitys.Bip;
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
public class ModelBip {
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
    public ModelBip(){
        con = ConnData();
    }
    public boolean insertBip(Bip bip){
        boolean bool = false;
        String sql = "INSERT INTO bips (codeP,audio) VALUES ('"+bip.getCodeP()+"',?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bip.getAudio());
            bool = ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelBip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    public boolean updateBip(Bip bip){
        boolean bool = false;
        String sql = "UPDATE bips SET codeP = "+bip.getCodeB()+",audio = ? WHERE codeB = "+bip.getCodeB();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bip.getAudio());
            bool = ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ModelBip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    public Bip getBip(int codeB){
        Bip bip = null;
        String sql = "SELECT * FROM bips WHERE codeB = "+codeB;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bip = new Bip(rs.getInt("codeB"), rs.getString("codeP"), rs.getString("audio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelBip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bip;
    }
   
    public Bip getBipByProblem(String codeP){
        Bip bip = null;
        String sql = "SELECT * FROM bips WHERE codeP = '"+codeP + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                 bip = new Bip(rs.getInt("codeB"), rs.getString("codeP"), rs.getString("audio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelBip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bip;
    }
    public List<Bip> getBip(){
        Bip bip = null;
        List<Bip> bips = new ArrayList<>();
        String sql = "SELECT * FROM bips ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 bip = new Bip(rs.getInt("codeB"), rs.getString("codeP"), rs.getString("audio"));
                bips.add(bip);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelBip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bips;
    }
}
