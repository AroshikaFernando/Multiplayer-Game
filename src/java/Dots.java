/**
 *
 * Dots.java class
 * Used to keep dots details
 * Color,X and Y
 * Return JSON object of dot details
 */


/**
 *
 * @author Indumini Ayomi
 */

public class Dots {
    
    int x,y;
    String color;
    
    
    public Dots(String color,int x,int y){
        this.x = x;
        this.y = y;
        this.color = color;
        
    }
    
    @Override
    public String toString(){
        return "[\""+this.color+"\", "+this.x+", "+this.y+"]";
    }
    
    
    
    
}
