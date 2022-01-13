/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author A H M E D
 */
public class Game {
    public static final int HARD = 9;
    public static final int MID = 5;
    public static final int EASY = 3;
    public static final String X = "X";
    public static final String O = "O";
   
    
    public ArrayList<Integer> empty(ArrayList<JButton> bord)  {
            ArrayList<Integer> list = new ArrayList<>();
            for (JButton i : bord) {
                if (!(i.getText() == X || i.getText() == O)) {
                    list.add(bord.indexOf(i));
                }
            }
            
            return list;
        }
    
    public boolean winner(ArrayList<JButton> bord, String playerSymbole) {
        for (int i =0;i<=2;i++) {
            if (
                bord.get(i * 3).getText().equals(playerSymbole) &&
                bord.get((i * 3) + 1).getText().equals(playerSymbole)&&
                bord.get((i * 3) + 2).getText().equals(playerSymbole)||

                bord.get(i).getText().equals(playerSymbole)  &&
                bord.get(i + 3).getText().equals(playerSymbole) &&
                bord.get(i + 6).getText().equals(playerSymbole)
                ) 
            {
                return true;
            }
        } 

        if (
            bord.get(0).getText() == playerSymbole &&
            bord.get(4).getText() == playerSymbole &&
            bord.get(8).getText() == playerSymbole ||
                
            bord.get(2).getText() == playerSymbole &&
            bord.get(4).getText() == playerSymbole &&
            bord.get(6).getText() == playerSymbole
            )
        {
             
            return true;
        }

        return false;
    }
        
    public class ChicArray{
        public int index =0;
        public int score =0;
    }
       
    public ChicArray minimax(ArrayList<JButton> bord , String player,int depth) {

        ArrayList<Integer>  availoblespits = empty(bord);

        if (winner(bord, X)) {
            ChicArray result = new ChicArray();
            result.score = -10;
            return result;
        }else if (winner(bord, O) ) {
              ChicArray result = new ChicArray();
            result.score = 10;
            return result;
        }else if(depth==0||availoblespits.isEmpty()){
            ChicArray result = new ChicArray();
            result.score = 0;
            return result;
        }

        ArrayList<ChicArray> allsecondChic = new ArrayList<>();

        for (Integer i : availoblespits) {
            ChicArray x = new ChicArray();
            x.index = i;
            String btntext = bord.get(i).getText();
            bord.get(i).setText(player);

            if (player == O) {
                ChicArray result = minimax(bord, X,--depth);
                x.score = result.score;
            }
            if (player == X) {
                 ChicArray result = minimax(bord, O,--depth);
                x.score = result.score;
            }

            bord.get(i).setText(btntext);

            allsecondChic.add(x);
        }

        int bestMove = 0;
        if (player == O) {

        int bestscore = -10000;
        for (ChicArray i : allsecondChic) 
            if (i.score > bestscore) {
                bestscore = i.score;
                bestMove = allsecondChic.indexOf(i);
            }

        } else if (player == X) {

            int bestscore = 10000;
            for (ChicArray i : allsecondChic){
                if (i.score < bestscore) {
                    bestscore = i.score;
                    bestMove = allsecondChic.indexOf(i);
                }
            }
        }
        return allsecondChic.get(bestMove);
    }
    
    public JButton update(ArrayList<JButton> currentBord ) {
        int bestMove = minimax(currentBord,O,gameMode).index;
        return currentBord.get(bestMove);
    }
    
    public Game(int gameMode){
        this.gameMode = gameMode;
    }

    private int gameMode;
}
