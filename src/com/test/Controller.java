/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.entitys.Bip;
import com.entitys.Problem;
import com.entitys.Solution;
import com.models.ModelBip;
import com.models.ModelProblems;
import com.models.ModelSolution;
import java.sql.Blob;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author MOHAMMED-PC
 */
public class Controller {
    public static void main(String arg[] ){
        ModelProblems mp = new ModelProblems();
        boolean bool =  mp.isertProd(2);
        if(!bool){
            
            System.out.println("secces");
        }
    }
    
}
