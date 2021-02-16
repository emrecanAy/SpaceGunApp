
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author emrecan
 */

class Bullet{
    
    private int x;
    private int y;
    
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}

public class Game extends JPanel implements KeyListener, ActionListener{
    
    Timer timer=new Timer(10, this);
    
    private int passedTime=0;
    private int spentBullet=0;
    
    private BufferedImage image;
    
    private ArrayList<Bullet> bullets=new ArrayList<Bullet>();
    
    private int bulletDirY=7; // Bullet Speed    
    private int enemyX=0;    
    private int enemyDirX=8; // Enemy Speed
    private int spaceShipX=0;
    private int spaceShipDirX=20; // SpaceShip Speed
    
    public boolean checkOut(){
        
        for (Bullet bullet:bullets){
            
            if (new Rectangle(bullet.getX(), bullet.getY(), 10, 20).intersects(new Rectangle(enemyX, 0, 20, 20))) {
                
                return true;
                
            }           
        }
        return false;
    }

    public Game() {
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("spaceship.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setBackground(Color.BLACK);
        
        timer.start();
       
    }
    
   

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        passedTime+=5;
        g.setColor(Color.red);
        g.fillOval(enemyX, 0, 20, 20);
        g.drawImage(image, spaceShipX, 490, image.getWidth()/80, image.getHeight()/80, this);
        
        for (Bullet bullet:bullets){
            if (bullet.getY() < 0) {
                
                bullets.remove(bullet);
                
            }            
        }
        
        g.setColor(Color.BLUE);
        
        for(Bullet bullet:bullets){
            
            g.fillRect(bullet.getX(), bullet.getY(), 10, 20);
            
        }
        
        if (checkOut()) {
            timer.stop();
            String message="You Won...\n"
                          +"Spent Bullet : "+ spentBullet+"\n"
                          +"Passed Time : "+ passedTime / 1000.0+" sec";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
        

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int c = e.getKeyCode();
        
        if (c == KeyEvent.VK_LEFT) {
            
            if (spaceShipX <= 0) {
                spaceShipX = 0;
            }
            else{
                spaceShipX -= spaceShipDirX;
            }
            
        }
        else if (c == KeyEvent.VK_RIGHT){
            
            if (spaceShipX >= 750) {
                spaceShipX = 750;
            }
            else{
                spaceShipX += spaceShipDirX; 
            }
            
        }
        else if (c == KeyEvent.VK_CONTROL) {
            
            bullets.add(new Bullet(spaceShipX+15, 470));
            spentBullet++;
            
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (Bullet bullet:bullets){
            
            bullet.setY(bullet.getY()-bulletDirY);
            
        }
        
        enemyX += enemyDirX;
        
        if (enemyX >= 750) {
            enemyDirX = -enemyDirX;
        }
        if (enemyX <= 0) {
            enemyDirX = -enemyDirX;
        }
        repaint();
        
    }
    
    
    
    
}
