import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A container Class that sets up a JLabel with an Image associated with one of the Symmetry Icons
 */
public class SymLabels {

    /**
     * Declarations
     */

    private String ImageFileName;
    private Image SymImage;
    private JLabel ImageLabel;
    private int Type;

    /**
     * Constructor Method to setup a Label with the correct Symmetry Icon Image attached and the correct type
     * @param ImageFileName
     * @param Type
     */

    public SymLabels(String ImageFileName, int Type) {
        this.ImageFileName = ImageFileName;
        this.Type = Type;
        URL url = getClass().getResource(this.ImageFileName);
        System.out.println(url);
        try {
            this.SymImage = ImageIO.read(url);
            this.ImageLabel = new JLabel(new ImageIcon(this.ImageFileName));
        }
            catch(MalformedURLException mue)
            {
                System.out.println(mue.getMessage());
            }
            catch(IOException ioe)
            {
                System.out.println(ioe.getMessage());
            }
    }

    /**
     * Width returns the Label width
     * @return
     */

    public int Width(){
        return ImageLabel.getWidth();
    }

    /**
     * Height returns the Label Height
     * @return
     */

    public int Height(){
        return ImageLabel.getHeight();
    }

    /**
     * setPosition takes the parameter p and and assigns it to the Label
     * @param p
     */

    public void setPosition(Point p){
        ImageLabel.setLocation(p);
    }

    /**
     * getPosition returns the Label Position
     * @return
     */

    public Point getPosition(){
        return ImageLabel.getLocation();
    }

    /**
     * getJLabel returns the JLabel
     * @return
     */

    public JLabel getJLabel(){
        return ImageLabel;
    }

    /**
     * getSymImage returns the image on the Label
     * @return
     */

    public Image getSymImage(){
        return SymImage;
    }

    /**
     * getType reutunrs the Type associated with the Label
     * @return
     */

    public int getType(){
        return Type;
    }

}
