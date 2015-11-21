import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * A class that extends JPanel and sets up the Panel that stores Icons to be added to the main Panel
 */

class ImagePanel extends JPanel {

    /**
     * Declarations
     */

    private Image imgBackground;
    private JLabel label;

    /**
     * Constructor class that takes in an array of SymLabels to populate the Panel and sets the background image
     * @param SymLab
     */

public ImagePanel(SymLabels[] SymLab) {

    /**
     * Setup the background image
     */

            URL urlBackground = getClass().getResource("SymPickup.png");
            this.imgBackground = new ImageIcon(urlBackground).getImage();
    /**
     * Setup the SymLabels as a new set of Labels from SymLabArray
     * Need to change this as no point in setting up 2 sets of labels just save points in SymLabels and use those
     */

        setLayout(null);
            for (int i =0; i < SymLab.length; i++) {
                label = new JLabel();
                label.setIcon(new ImageIcon(SymLab[i].getSymImage()));
                label.setName(String.valueOf(SymLab[i].getType()));
                Dimension d = label.getPreferredSize();
                label.setBounds(SymLab[i].getJLabel().getX(), SymLab[i].getJLabel().getY(), d.width, d.height);
                add(label);
            }
        }

    /**
     * Method to update and change the SymLabels array
     * @param SymLab
     */

            public void ChangeLabels(SymLabels[] SymLab){
                this.removeAll();
                for (int i =0; i < SymLab.length; i++) {
                    label = new JLabel();
                    label.setIcon(new ImageIcon(SymLab[i].getSymImage()));
                    label.setName(String.valueOf(SymLab[i].getType()));
                    Dimension d = label.getPreferredSize();
                    label.setBounds(SymLab[i].getJLabel().getX(), SymLab[i].getJLabel().getY(), d.width, d.height);
                    add(label);
                }
                this.repaint();
            }

    /**
     * Method to paint the background
     * @param g
     */
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(this.imgBackground, 0, 0, getWidth(), getHeight(), null);
            }
        }