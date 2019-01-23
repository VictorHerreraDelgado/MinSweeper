/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Board;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Asus
 */
public class BoardDisplay extends JPanel{
    private List<Button> buttons;
    private final Board board;
    
    public BoardDisplay(Board board) {
        this.board = board;
        this.setLayout(new GridLayout(board.getRows(),board.getColumns()));
        buttons = new ArrayList<>();
        createButtons();
    }
    

    private void createButtons() {
        this.setLayout(new GridLayout(board.getRows(), board.getColumns()));
        for(int i = 0; i< board.getRows(); i++)
            for (int j = 0; j < board.getColumns(); j++)
                this.add(button(i,j));
    }
    private JButton button (int x, int y){
        Button button = new Button();
        buttons.add(button);
        button.setFocusable(false);
        button.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e){
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                if (SwingUtilities.isLeftMouseButton(e))
                    board.openCell(x,y);
                else if (SwingUtilities.isRightMouseButton(e))
                    board.putFlag(x, y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        return button;
    }
    private int index(int x, int y){
        return x * board.getColumns() + y;
    }
    public void drawCell(int x, int y){
        System.out.println("xxxxx");
        Button button = buttons.get(index(x, y));
   
        button.setMinesAround(board.neighborsWithMines(x, y));
        button.setEnabled(false);
        
        repaint();
    }
    
}
