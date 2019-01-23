/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Asus
 */
public class Button extends JButton{
    private int minesAround;
    private boolean hasMine;
    //private CellState cellState = CellState.NO_FLAGGED;

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }
    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }
    
   /* public void setCellState(CellState cellState){
        this.cellState = cellState;
    }*/

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial",Font.BOLD,12));
        g.drawString("hole",10,10);
        paintComponent(g);
        g.setFont(new Font("SERIF", Font.BOLD, 16));
            g.setColor(Color.RED);
            g.drawString(minesAround+"", getWidth()/2 - 4, getHeight()/2 + 7);
            
        /*if (cellState == CellState.MINE){
            g.drawImage(scaleImage(Images.MINE.getImage()), 0, 0, null);
        }else if (cellState == CellState.NUM){
            g.setFont(new Font("SERIF", Font.BOLD, 16));
            g.setColor(Colors.get(minesAround));
            g.drawString(minesAround+"", getWidth()/2 - 4, getHeight()/2 + 7);
        }else if (cellState == CellState.FLAGGED) {
            g.drawImage(scaleImage(Images.FLAG.getImage()), 0, 0, null);
        }
    }

    private Image scaleImage(BufferedImage image){
        return image.getScaledInstance(getWidth(), getHeight(), 0);
*/      
    }

}
