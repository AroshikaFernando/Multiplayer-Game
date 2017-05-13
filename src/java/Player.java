/**
 *
 * Player.java class
 * Used to keep player details
 * PlayerID,Score,X and Y
 * Update player positions after every key press
 * Return JSON object of player details
 */

/**
 *
 * @author Indumini Ayomi
 */

public class Player {
    
    String playerID;
    private int score,x,y;
    
    public Player(String playerID, int score, int x, int y){
        this.playerID = playerID;
        this.score = score;
        this.x = x;
        this.y = y;
    }
    
    String getPlayerID(){
        return this.playerID;
    }
    
    int getX(){
        return this.x;
    }
    
    int getY(){
        return this.y;
    }
    
    void setX(int x){
        this.x = x;
    }
    
    void setY(int y){
        this.y = y;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }      
    
    // update player positions after a keypress
    public String updatePlayerPositions(int key){
        
        if(key == 40){  // ArrowUp key
            if(this.y != 44)
                this.y = this.y+1;
        }
        
        if(key == 38){  //ArrowDown key
            if(this.y != 0)
                this.y = this.y-1;
        }
        
        if(key == 37){ // ArrowLeft key
            if(this.x != 0)
                this.x = this.x-1;
        }
        
        if(key == 39){ // ArrowRight key
            if(this.x != 44)
                this.x = this.x+1;
        }
        
        return this.x+", "+this.y;
        
    }
    
    // return JSON object of player details
    @Override
    public String toString() {
        return "[\""+this.playerID+"\", "+this.score+" , "+this.x+" , "+this.y+"]";
    }
    
}
