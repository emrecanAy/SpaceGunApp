
import java.awt.HeadlessException;
import javax.swing.JFrame;


/**
 *
 * @author emrecan
 */
public class GameFrame extends JFrame{

    public GameFrame(String title) throws HeadlessException {
        super(title);
    }
    
    
    public static void main(String[] args) {
        
        GameFrame frame=new GameFrame("Space Gun");
        frame.setResizable(false);
        frame.setFocusable(false);
        
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        
        frame.add(game);
        frame.setVisible(true);
        
        
        
    }
    
}
