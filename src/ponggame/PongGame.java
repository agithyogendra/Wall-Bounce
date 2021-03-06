
package ponggame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class PongGame extends JComponent implements ActionListener, MouseMotionListener{

    public static int ballX = 400;
    public static int ballY = 300;
    int ballSpeedX = 8;
    int ballSpeedY = 10;
    int paddleX;
    int hits = 0;
    public static int promptX = 800;
    public static int promptY = 600;
    public static Font font1;
    public static Font font2;
    
    public Dimension getPreferredSize(){
        //Set screen size
        return new Dimension(800,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        
       //Background
       g.setColor(new Color(178,223,224));
       g.fillRect(0, 0, 800, 600);
       
       g.setColor(new Color(245, 227, 27));
       g.fillOval(300, 50, 100, 100);
       
       //Score Board
       g.setColor(Color.black);
       g.drawRect(730, 0, 68, 30);
       g.setFont(font1);
       g.drawString("Hits: " + hits, 735, 20);
       
       //Paddle 
       g.setColor(new Color(110,65,12));
       g.fillRect(paddleX, 510, 150, 15);
       
       //Ball
       g.setColor(new Color(155,93,169));
       g.fillOval(ballX, ballY, 30, 30);
       
       //You Lose Prompt
       g.setColor(Color.DARK_GRAY);
       g.setFont(font2);
       g.drawString("You Lose!", promptX, promptY);
    }

    
    public static void main(String[] args) {
        //Set up the window
        JFrame window = new JFrame("Pong");
        PongGame game = new PongGame();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //Score Board font
        font1 = new Font("Arial", Font.PLAIN,14);
        
        //Losing font
        font2 = new Font("Century", Font.BOLD, 30);
        
        //Set up ActionListener 
        Timer t = new Timer(100, game);
        t.start();
        
        //Set up MouseMotionListener
        window.addMouseMotionListener(game);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ballY = ballY + ballSpeedY;
        ballX = ballX +  ballSpeedX;
        
        int [] speedsX = {10, 15, 20};
        int [] speedsY = {15, 20, 24};    
        
        int randSpeedX = speedsX[(int)(Math.random()*3)];
        int randSpeedY = speedsY[(int)(Math.random()*3)];
        //Algorithms to animate ball bouncing of screen
        
        if(ballX >= paddleX && ballX <= paddleX + 150 && ballY + 30 == 510){
            
            ballSpeedY = (hits < 3) ? -10 : -randSpeedY;
            hits++;
        }else if(ballX <= 0){
            ballSpeedX = (hits < 3) ? 8 : randSpeedX;
        }else if(ballX + 30 >= 800){
            ballSpeedX = (hits < 3) ? -8 : -randSpeedX;
        }else if(ballY <= 0){
            ballSpeedY = (hits < 3) ? 10 : randSpeedY;
        }
        
        
    
        
        
        
        //Game over scenario
        if(ballY > 510){
            promptX = 320;
            promptY = 300;
        }   
        
        repaint();
            
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddleX = e.getX() - 75;
        repaint();
 
    }
    
}
