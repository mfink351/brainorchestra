import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class SheetMusic extends Component {
	
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
        int dx, dy;
        g.drawImage(this.bi, 0, 0, w, h, null);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
