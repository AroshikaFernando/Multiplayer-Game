/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Indumini Ayomi
 */

@WebServlet(urlPatterns = {"/game", "/UpdateGame"})
public class UpdateGame extends HttpServlet {

    Canvas canvas = new Canvas();
    String color;
    Player[] players = new Player[4];   
    int count = 0;
    String d = canvas.toString();   
    
    volatile String message;
    
    @Override
    public void init() throws ServletException {
        
        players[0] = new Player("P1", 0, 0, 0);
        players[1] = new Player("P2", 0, 44, 0);
        players[2] = new Player("P3", 0, 0, 44);
        players[3] = new Player("P4", 0, 44, 44);
        canvas.initPlayers(players);   
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");

        try (final PrintWriter out = response.getWriter()) {
            Object attribute = request.getSession().getAttribute("player");
            if (attribute == null) {
                if (count <= 3) {
                    request.getSession().setAttribute("player", players[count++]);
                }
            }

            while (!Thread.interrupted()) {
                synchronized (this) {
                   
                    // JSON object with all dots and players
                    String obj = d + "\"PLAYERS\": [" + players[0].toString()+ ", " + players[1].toString() + ", " + players[2].toString() +
                                ", " + players[3].toString() + "] }";
                    
                    out.println("data: " + obj);  
                    out.println();
                    out.flush();
                    wait(); 
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            synchronized (this) {
                message = request.getParameter("keypress");  // get pessed key
                int key = Integer.valueOf(message);   // get ascii value of pressed key

                Player player = (Player) request.getSession().getAttribute("player");  // get player session
                String positions = player.updatePlayerPositions(key);   // update (x,y) positions of the player
                canvas.updateDots(positions,player);   // update grid 
                d = canvas.toString();   // call toString method and get JSON object of dots
                canvas.collide(player);   // check whether two players are collided or not
            
                notifyAll();   // notify all sessions
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
