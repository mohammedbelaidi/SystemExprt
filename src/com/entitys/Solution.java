/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entitys;

/**
 *
 * @author MOHAMMED-PC
 */
public class Solution {
    private int codeS;
    private String description;

    public Solution() {
    }

    public Solution(int codeS, String description) {
        this.codeS = codeS;
        this.description = description;
    }

    public Solution(String description) {
        this.description = description;
    }

    public int getCodeS() {
        return codeS;
    }

    public void setCodeS(int codeS) {
        this.codeS = codeS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Solution{" + "codeS=" + codeS + ", description=" + description + '}';
    }
    
}
