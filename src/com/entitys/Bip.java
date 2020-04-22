/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entitys;

import java.sql.Blob;

/**
 *
 * @author MOHAMMED-PC
 */
public class Bip {
    private int codeB;
    private String codeP;
    private String audio;

    public Bip() {
    }

    public Bip(String codeP, String audio) {
        this.codeP = codeP;
        this.audio = audio;
    }

    public Bip(int codeB, String codeP, String audio) {
        this.codeB = codeB;
        this.codeP = codeP;
        this.audio = audio;
    }

    public int getCodeB() {
        return codeB;
    }

    public void setCodeB(int codeB) {
        this.codeB = codeB;
    }

    public String getCodeP() {
        return codeP;
    }

    public void setCodeP(String codeP) {
        this.codeP = codeP;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "Bip{" + "codeB=" + codeB + ", codeP=" + codeP + ", audio=" + audio + '}';
    }
    
}
