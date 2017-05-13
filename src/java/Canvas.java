/**
 *
 * Canvas.java class
 * Used to control game logic
 */

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Indumini Ayomi
 */

final class Canvas {

    static final long INTERVAL = 2000;
    String value = "begining";
    
    HashMap<String,String> dots = new HashMap<>();
    Random rand = new Random();
    String color = null;
    Player[] players = new Player[4];
    
    public Canvas(){
        generateRandomDots();   // generate random dots
    }
    
    void initPlayers(Player[] players){
        this.players = players;
    }
    
    // check whether two players are collided or not
    void collide(Player currentPlayer){
        
        for(Player p: players){
            if(p.getPlayerID() != currentPlayer.getPlayerID()){
                
                if(p.getX() == currentPlayer.getX() && p.getY()== currentPlayer.getY()){
                    // reduce scores of players if collided
                    currentPlayer.setScore(currentPlayer.getScore() - 3);   
                    p.setScore(p.getScore() - 3);
                    
                    // put collided players to initial positions
                    switch(p.getPlayerID()){
                        case "P1":
                            p.setX(0);
                            p.setY(0);
                            break;
                        case "P2":
                            p.setX(44);
                            p.setY(0);
                            break;
                        case "P3":
                            p.setX(0);
                            p.setY(44);
                            break;
                        case "P4":
                            p.setX(44);
                            p.setY(44);
                            break;
                    }
                    
                    switch(currentPlayer.getPlayerID()){
                        case "P1":
                            currentPlayer.setX(0);
                            currentPlayer.setY(0);
                            break;
                        case "P2":
                            currentPlayer.setX(44);
                            currentPlayer.setY(0);
                            break;
                        case "P3":
                            currentPlayer.setX(0);
                            currentPlayer.setY(44);
                            break;
                        case "P4":
                            currentPlayer.setX(44);
                            currentPlayer.setY(44);
                            break;
                    }
               
                }
            }
        }
    }
    
    void generateRandomDots() {

        for (int i = 0; i < 30; i++) {
            int ranColour = rand.nextInt(3) + 1;  // generate random numbers between 1 and 3
            
            // assign colors according to the generated random values
            switch (ranColour) {
                case 1:
                    color = "R";
                    break;
                case 2:
                    color = "G";
                    break;
                case 3:
                    color = "B";
                    break;
            }
            
            // generate random numbers between 1 and 43 for positions of the dots
            int ranX = rand.nextInt(43) + 1;
            int ranY = rand.nextInt(43) + 1;
            
            String coordinates = ranX+", "+ranY; 
            
            dots.put(coordinates,color);  // put dots coordinates and colors to the hashmap
        }
        
    }
    
    // remove collided dot and update score
    void updateDots(String positions,Player player){
        
        // check whether there is a dot at player moved position
        if(dots.containsKey(positions)){
            color = dots.get(positions);  // get color of dot
            dots.remove(positions);  //remove dot from the hashmap
            
            // update score according to the color of the removed dot
            switch(color){
            case "R": 
                player.setScore(player.getScore()+3);
                break;
            case "G":
                player.setScore(player.getScore()+2);
                break;
            case "B":
                player.setScore(player.getScore()+1);
                break;
            
            }
        }
        
        
    }
    
    // return JSON object of dots
    @Override
    public String toString(){
        
        String objDots = null;
        String elements;
        int i=1;
        int length = dots.size();
        
        if(length == 0){
            objDots = "{ \"DOTS\": null,";
            return objDots;
        }
        
        objDots = "{ \"DOTS\": [";
        
        for (String key: dots.keySet()){
            
            if(i==length){
                elements = "[\""+dots.get(key)+"\", "+key+"]";
            }
            else{
                elements = "[\""+dots.get(key)+"\", "+key+"], ";
            }
            
            objDots = objDots + elements;
            i++;
        }
        
        objDots = objDots + "], ";

        return objDots;
          
    }
    
}