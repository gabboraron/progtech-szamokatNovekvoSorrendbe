package numbersascendinx;

/**
 *
 * @author Sándor
 */



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



class Gui {
    private coordinate gameSize;
    private Game game;
    private GridButtonPanel gpanel;
    private boolean poused = false;
    
    public Gui() {
        game = new Game(8,8);
        gpanel = new GridButtonPanel(game, this, 8, 8);
        gpanel.display();
        
        showGame();
        gameSize = new coordinate(8,8);
    }

    private Gui(int row, int col) {
        game = new Game(row,col);
        gpanel = new GridButtonPanel(game, this, row, col);
        gpanel.display();
        
        showGame();
        gameSize = new coordinate(row,col);
    }
    
    /**
     * RESET BUTTON
     * @return 
     */
    /*JButton reset() {
        JButton btn = new JButton("RESET");
        
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                game.reset();
                showInitGame();
                time = 0;
            }
        });
        return btn;
    }*/

    /**
     * POUSE BUTTON
     * @return 
     */
    /*JButton pouse() {
        JButton btn = new JButton("POUSE");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(poused){
                   btn.setText("POUSE: OFF");
                   poused = false;
                   gpanel.showGameTable();
                }else{
                    btn.setText("POUSE: ON");
                    poused = true;
                    gpanel.hideGameTable();
                }
            }
        });
        return btn;
    }*/

    /**
     * Show the game panel
     */
    private void showGame() {
        for (int rowIdx = 0; rowIdx < GridButtonPanel.N; ++rowIdx)
            for(int colIdx = 0; colIdx < GridButtonPanel.M; ++colIdx){
                
                gpanel.getGridButton(rowIdx, colIdx).setText( ((Tile) game.getTileBycoordinates(new coordinate(rowIdx, colIdx))).getLabelOfTile() );
                gpanel.getGridButton(rowIdx, colIdx).setFont(new Font("Arial", Font.PLAIN, 15));
                gpanel.getGridButton(rowIdx, colIdx).setBackground(Color.white);
            }
        System.out.println("GAME PANEL REFRESHED");
    }

    /**
     * Set a new tick to game panel
     * @param row 
     * @param col 
     */
    /*void setNewTick(int row, int col) {
        if(tickTack.isCurrentIsTick()){
            gpanel.getGridButton(row, col).setText("X");
        } else {
            gpanel.getGridButton(row, col).setText("O");
        }
        
        tickTack.addElem(new coordinate(row, col));
        if(tickTack.isItDoneByTick())
            JOptionPane.showMessageDialog(null, "You are the winner!");
    }*/
    
    /**
     * TIMER LABEL
     * @return 
     */
    int time = 0;
    JLabel timerLabel() {
        JLabel timer = new JLabel();
        timer.setText("Elapsed time: " + Integer.toString(time) + " sec");
        new java.util.Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                if(!poused){
                    ++time;
                    timer.setText("Elapsed time: " + Integer.toString(time) + " sec");
                }
                //System.out.println("Executed...");
                //your code here 
                //1000*5=5000 mlsec. i.e. 5 seconds. u can change accordngly 
            }
        },1000*5,1000*5);
        
        return timer;
    }
    
    /**
     * DISPLAY CURRENT PLAYER
     * @return 
     */
    JLabel currentPlayerLabel(int cp) {
        JLabel l = new JLabel();
        switch(cp){
            case 1 : l.setText("Current Player: Player 1");
                     l.setForeground(Color.green);
                     break;
            case 2 : l.setText("Current Player: Player 2");
                     l.setForeground(Color.blue);
                     break;
        }
        return l;
    }
    
     
    /**
     * NEW GAME MENU WITH MENU OPTIONS
     * @return 
     */
    JMenu newGameMenu() {
        JMenu m = new JMenu("New game");
        gpanel.eightTimeEight = new JMenuItem(eightTimeEight);
        m.add(eightTimeEight);
        
        return m;
    }
    
    /**
     * MENU ITEM
     */
    private AbstractAction eightTimeEight = new AbstractAction("8X8") {
        @Override
        public void actionPerformed(ActionEvent e)
        {                    
            game.newGame();
            showGame();
            gameSize = new coordinate(8,8);
        }
    };
       
      
    /**
     * Change the tiles values and etc in game when a tile is clicked
     * @param row
     * @param col 
     */
    void clickOnTile(int row, int col) {
        game.clickOn(new coordinate(row,col));
        
        showGame();
        
        if(game.isWinner()){
            poused = true;
            JOptionPane.showMessageDialog(null, "YOU WIN! CONGRATULATIONS!");
        }
    }


    

    /**
     * OPTIONS LIST
     * @return 
     */
    /*JScrollPane optionsList() {
        String[] options = {
        "8 X 5", "10 X 6", "12 X 7"};
        
        JList l = new JList(options);
        JScrollPane sp = new JScrollPane(l);
        l.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);
        l.addListSelectionListener((ListSelectionListener) this);
        l.setSelectedIndex(0);
        
        //@Override
        //public void valueChanged(ListSelectionEvent e) {        
        //setTitle((String)lsHonapok.getSelectedValue());
        //}
        
        return sp;
    }*/
    
}
