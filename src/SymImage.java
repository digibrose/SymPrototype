import javax.swing.*;
import java.awt.*;

/**
 * Created by digibrose on 06/10/2015.
 * Class extends JPanel and draws the active question image on the background
 * It should also scale correctly but doesn't work at present
 */
public class SymImage extends JPanel {

    /**
     * Declarations
     */

    private Image imgBackground;

    /**
    private double height;
    private int h2;
    private double imwidth;
    private double pwidth;
    private int oldwidth;
    private int oldheight;
    private int L;
    private double oldX;
    private double oldY;
    private double DoldWidth;
    private double DoldHeight;
    private double newX;
    private int inewX;
    private double newY;
    private int inewY;
    */

    /**
     * Constructor that takes an Image as the parameter and sets up the Panel
     * @param SymImage
     */

    public SymImage(Image SymImage) {

        this.imgBackground = SymImage;

        /**
         * Setup for Scaling
        imwidth = (double) this.imgBackground.getWidth(null);
        oldwidth = 461;
        oldheight = 361;
         */
    }

    /**
     * Method to change the background image
     * @param SymImage
     */

    public void ChangeImage(Image SymImage){

        this.imgBackground = SymImage;
    /**
        imwidth = (double) this.imgBackground.getWidth(null);
        oldwidth = 461;
        oldheight = 361;
    */
    }

    @Override
    protected void paintComponent(Graphics g) {

        /**
         * Setup for scaling the background image
         * getHeight in draw image is replaced by h2 to ensure proper scaling
         */

        /**
        pwidth = (double) this.getWidth();
        this.height = (pwidth/imwidth) * imgBackground.getHeight(null);
        h2 = (int) height;
         */

        /**
         * Setup for moving the Components along with the Panel the conversion into integers makes it loose position
         */

        /**
        Component[] C = this.getComponents();
        L = C.length;
        DoldWidth = (double) oldwidth;
        DoldHeight = (double) oldheight;
        if (this.getWidth() != oldwidth) {
            for (int i = 0; i < L; i++) {
                oldX = (double) C[i].getX();
                oldY = (double) C[i].getY();
                newX = ((oldX+0.7)/DoldWidth)*pwidth;
                inewX = (int) newX;
                newY = ((oldY+0.9)/DoldHeight)*height;
                inewY = (int) newY;
                C[i].setLocation(inewX, inewY);
            }
                oldwidth = this.getWidth();
                oldheight = h2;
        }
         */

        g.drawImage(this.imgBackground, 0, 0, getWidth(), getHeight(), null);

    }
}
