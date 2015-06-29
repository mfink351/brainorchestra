import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SheetMusic extends Component {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage bi;
    int w, h;
     
    public SheetMusic(BufferedImage bi) {
        this.bi = bi;
        this.w = bi.getWidth(null);
        this.h = bi.getHeight(null);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
 
    public void paint(Graphics g) {
        g.drawImage(this.bi, 0, 0, w, h, null);
    }
    
}
