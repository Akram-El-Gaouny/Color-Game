/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gade12_rst;

import java.util.Random;


public class randompic {
   private String Final;
   private String Folder;
   private String type;
   


public randompic (String fo, String ty){
Folder =fo;
type = ty;
}

public String getPic(){
 Random d = new Random(); 
 int k = d.nextInt(9)+1;
    
 Final = Folder+k+type;   
    
    
    return Final;
}










}