/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entitys;

import java.io.Serializable;

/**
 *
 * @author MOHAMMED-PC
 */
public class Problem implements Serializable{
    private int code;
    private String codeP;
    private String symptome;
    private String type;

    public Problem(int code, String syptome, String type) {
        this.code = code;
        this.symptome = syptome;
        this.type = type;
    }

    public Problem(String syptome, String type) {
        this.symptome = syptome;
        this.type = type;
    }

    public Problem(int code, String codeP, String symptome, String type) {
        this.code = code;
        this.codeP = codeP;
        this.symptome = symptome;
        this.type = type;
    }

    public Problem() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public String getCodeP() {
        return codeP;
    }

    public void setCodeP(String codeP) {
        this.codeP = codeP;
    }

    @Override
    public String toString() {
        return "Problem{" + "code=" + code + ", syptome=" + symptome + ", type=" + type + '}';
    }
    
    
}
