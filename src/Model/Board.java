/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observer;
import java.util.Random;
import java.util.Set;


/**
 *
 * @author Asus
 */
public class Board {
    private final int rows;
    private final int columns;
    private List<Cell> cells;
    private Set<Integer> mines;
    private Set<Integer> openedCells;
    private final Level level;
    private List <Observer> observers = new ArrayList();
    private ChangeListener changeListener;
    
    public Board(int columns, int rows, Level level) {
        
        System.out.println("Por aqui tambien");
        //this.mines = mines();
        this.openedCells = new HashSet();
        this.level = level;
        this.rows = rows;
        this.columns = columns;
        this.cells = cells();
        
    }
    
    public Level getLevel(){
        return level;
    }

    
    public int getColumns(){
        return columns;
    }
    
    public int getRows(){
        return rows;
    }
    private Set<Integer> mines() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Cell> cells() {
        List <Cell> cells = new  ArrayList<>();
        System.out.println(rows + "  " + columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++){
                cells.add(cell(i,j));
                //System.out.println(cells.get(index(i,j)));
            }
        
        return cells;
    }

    private Cell cellAt(int x, int y) {
        if (x < 0 || x >= getRows() || y  < 0 || y >= getColumns()) return null;
        return cells.get(index(x,y));
    }

    public void putFlag(int x, int y) {
        
    }

    public void openCell(int x, int y) {
        Cell cell = cellAt(x,y);
        cell.open();
        if(cell.hasMine())
            System.out.println("Perdiste");
        else if(cells.size() - openedCells.size() == mines.size() )
            System.out.println("Felicidades has ganado");
    }
    
    public enum Level 
    {
        BEGINNER(0.20),
        MEDIUM(0.40), 
        EXPERT(0.60);
        
        public final double mineCoverage;
        
        Level(double mineCoverage){
            this.mineCoverage = mineCoverage;
        }
        
    }
    private Set<Integer> createMines(int x, int y){
        Set<Integer> mines = new HashSet();
        Random random = new Random();
        int ignore = index(x,y);
        while (mines.size() < rows * columns * level.mineCoverage){
            int rand = random.nextInt(rows*columns);
            if(rand == ignore) continue;
            System.out.println(rand);
            mines.add(rand);
        }
        
        System.out.println(mines.size());
        return mines;
    }
    private Cell cell(int x, int y){
        return new Cell(){
            @Override
            public boolean isOpen() {
                return openedCells.contains(index(x,y));
            }

            @Override
            public boolean hasMine() {
                return mines.contains(index(x,y));
            }

            @Override
            public void open() {
                if ( mines == null) mines = createMines(x,y);
                else if(openedCells.contains(index(x,y))) return;
                openedCells.add(index(x,y));
                changeListener.update(x, y);
                if(neighborWithMines() || hasMine() ) return;
                openNeighbors();
            }

            @Override
            public void putFlag() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            private void openNeighbors(){
                getNeighbors(x,y).forEach((neighbor) -> {
                    neighbor.open();
                });
            }
            private boolean neighborWithMines(){
                for (Cell neighbor : getNeighbors(x,y)){
                   if (neighbor.hasMine()) return true;
                 }
                return false;
            }
            
        };
    }
    
        private List<Cell> getNeighbors(int x, int y){
            List<Cell> neighbors = new ArrayList<Cell>(){
                @Override
                public boolean add(Cell cell){
                    if(cell == null) return false;
                    return super.add(cell);
                }
            };
                    neighbors.add(cellAt(x - 1, y - 1));
                    neighbors.add(cellAt(x - 1, y));
                    neighbors.add(cellAt(x - 1, y + 1));
                    neighbors.add(cellAt(x + 1, y - 1));
                    neighbors.add(cellAt(x + 1, y));
                    neighbors.add(cellAt(x + 1, y + 1));
                    neighbors.add(cellAt(x, y - 1));
                    neighbors.add(cellAt(x, y + 1));
                    neighbors.remove(null);
                    return neighbors;
        }
            
    
    private int index(int x, int y){
        return x * columns + y;
    }
    
    public int neighborsWithMines(int x, int y){
        int count = 0;
        for (Cell neighbor: getNeighbors(x,y))
            if (neighbor.hasMine()) count++;
        return count;
    }
     public void addChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }
}