/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board;
import Model.Board.Level;
import View.BoardDisplay;
import javax.swing.JFrame;

/**
 *
 * @author Asus
 */
public class Main extends JFrame {
    private Board board;
    private BoardDisplay boardDisplay;
    public static void main(String [] args){
        new Main().launch();
    }

    private void launch() {
        this.setVisible(true);
    }
    
    public Main(){
        
        board = new Board(10,10,Level.BEGINNER);
        boardDisplay = new BoardDisplay(board);
        addListeners();
        this.setTitle("Mine Sweeper");
        this.setSize(600,630);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.add(boardDisplay);
    }

    private void addListeners(){
        board.addChangeListener((x,y) -> {
            
            boardDisplay.drawCell(x, y);
        });
    }
}
