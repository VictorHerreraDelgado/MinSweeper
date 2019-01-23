/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public  interface Cell {
    public boolean isOpen();
    public boolean hasMine();
    
    
    public void open();
    //public void setOpenListener(Open Listener openListener);
    public void putFlag();
    /*public enum Flag{
        NON, FLAG, QUESTION;
        
        public flag next(){
            int index = ordinal() +1 %flag;
            return null;
        }
        
    }*/
}

