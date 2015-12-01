import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Class that listens for mouse inputs and transfers icons to the correct places via the glass panel
 */

class ImageMover extends MouseInputAdapter
{

    /**
     * Declarations
     */

    private ImagePanel Imp;
    private JPanel p1, Op;
    private SymImage p3;
    private JPanel glassPanel;
    private JPanel activePanel;
    private Component selectedComponent;
    private Point offset;
    private boolean dragging, selected;
    private JPanel OriP;
    private Point Original;
    private Rectangle[] SymHooks;
    private Rectangle c1;
    private Boolean dropflag;
    private JLabel newlabel;
    private Dimension d;
    private SymLabels[] Symlab;
    private Rectangle WasteBox;

    /**
     * Constructor for ImageMover taking in all the panels, the glass panel and the question
     * @param Imp
     * @param p1
     * @param Op
     * @param p3
     * @param gp
     * @param Question
     */

    public ImageMover(ImagePanel Imp, JPanel p1, JPanel Op, SymImage p3, JPanel gp, Question Question)
    {
        this.Imp = Imp;
        this.p1 = p1;
        this.Op = Op;
        this.p3 = p3;
        glassPanel = gp;
        offset = new Point();
        dragging = false;
        selected = false;
        this.SymHooks = Question.getSymHooks();
        dropflag = false;
        this.Symlab = Question.getSymLabels();
        WasteBox = new Rectangle(16, 330, 100, 100);
    }

    /**
     * Method to change the symHooks array
     * @param SymHooks
     */

    public void ChangeHooks(Rectangle[] SymHooks){
        this.SymHooks = SymHooks;
    }


    /**
     * method to negotiate draganddrop when mouse is pressed
     * @param e
     */

    public void mousePressed(MouseEvent e)
    {
        /**
         * Set up click point and set ActivePanel
         */

        Point p = e.getPoint();
        System.out.println(p);
        if(!setActivePanel(p))
            return;

        /**
         * record where the initial click occurred
         */

        OriP = activePanel;

        /**
         * Check whether click was over an image label
         */

        selectedComponent = getImageLabel(p);
        if(selectedComponent == null)
            return;

        /**
        *Check whether click was over waste box
        */

        if(selectedComponent.getName().equals("WasteBox")) return;

        /**
         *remove selected component from active panel add it to the glass panel set the offset and original position
         */

        Rectangle labelR = selectedComponent.getBounds();
        Rectangle panelR = activePanel.getBounds();
      //  if(labelR.contains(p.x - panelR.x, p.y - panelR.y))
      //  {
            activePanel.remove(selectedComponent);
            selected = true;
            glassPanel.add(selectedComponent);
            offset.x = p.x - labelR.x - panelR.x;
            offset.y = p.y - labelR.y - panelR.y;
            dragging = true;
            Original = labelR.getLocation();
      //  }
    }

    /**
     * method to set the dragndrop action when mouse is released
     * @param e
     */

    public void mouseReleased(MouseEvent e)
    {

        /**
         * get position if off glass panel then return
         */

        Point p = e.getPoint();
        if(!contains(glassPanel, selectedComponent))
            return;

        /**
         * check drop point is on glasspanel
         * this is copied form below and should be put into a new method
         */

        Rectangle gP = glassPanel.getBounds();
        if (!gP.contains(p)){
            glassPanel.remove(selectedComponent);
            OriP.add(selectedComponent);
            int x = Original.x;
            int y = Original.y;
            Dimension d = selectedComponent.getSize();
            selectedComponent.setBounds(x, y, d.width, d.height);
            return;
        }

        /**
         * Calculate actions when dropped
         * remove from glasspanel and set new active panel
         */
        glassPanel.remove(selectedComponent);
        setActivePanel(p);

        /**
         * check whether the droppoint was over a symhooks point
         */

        Rectangle PanelA = new Rectangle(activePanel.getBounds());
            c1 = new Rectangle();
        for (int i = 0; i<SymHooks.length; i++) {
            c1.setBounds(SymHooks[i].getBounds());
       //     c1.setLocation(c1.x, c1.y + p1.getHeight());
            if (activePanel.equals(p3) && (c1.contains(p))) {
                activePanel.add(selectedComponent);
                Rectangle r = activePanel.getBounds();
                int x = p.x - offset.x - r.x;
                int y = p.y - offset.y - r.y;
                Dimension d = selectedComponent.getSize();
                selectedComponent.setBounds(x, y, d.width, d.height);
                dragging = false;
                dropflag = true;

                /**
                 * if the icon came from the ImagePanel then repopulate
                 */

                if (OriP.equals(Imp)){
                    for (int j = 0; j< Symlab.length;j++){
                        if (Original.getY() == Symlab[j].getPosition().getY()){
                            newlabel = new JLabel();
                            newlabel.setIcon(new ImageIcon(Symlab[j].getSymImage()));
                            d = new Dimension();
                            d = newlabel.getPreferredSize();
                            newlabel.setBounds(Symlab[j].getJLabel().getX(), Symlab[j].getJLabel().getY(), d.width, d.height);
                            newlabel.setName(String.valueOf(Symlab[j].getType()));
                            Imp.add(newlabel);
                            JLabel test = (JLabel) selectedComponent;
                        }
                    }
                }

            }
        }

        /**
         * if the drop point was over the waste box then don't replace at original site
         */

            if (WasteBox.contains(p) && (!OriP.equals(Imp))){
                dropflag = true;
            }

        /**
         * if not dropped then return to original position
         */

            if (!dropflag) {
                glassPanel.remove(selectedComponent);
                OriP.add(selectedComponent);
                int x = Original.x;
                int y = Original.y;
                Dimension d = selectedComponent.getSize();
                selectedComponent.setBounds(x, y, d.width, d.height);

            }
        dropflag = false;
       // activePanel.repaint();
       // OriP.repaint();
        glassPanel.repaint();
        activePanel = null;
        }

    /**
     *Method to set the mouse dragged actions
     * @param e
     */

    public void mouseDragged(MouseEvent e)
    {
        if(dragging)
        {
            Point p = e.getPoint();
            int x = p.x - offset.x;
            int y = p.y - offset.y;
            Dimension d = selectedComponent.getSize();
            selectedComponent.setBounds(x, y, d.width, d.height);
            if(!selected)
            {
                activePanel.repaint();
                selected = false;
            }
            glassPanel.repaint();
        }
    }

    /**
     * method to check whether a panel contains a specific component
     * @param p
     * @param comp
     * @return
     */

    private boolean contains(JPanel p, Component comp)
{
    Component[] c = p.getComponents();
    for(int j = 0; j < c.length; j++)
        if(c[j] == comp)
            return true;
    return false;
}

    /**
     * Method to setup the active panel
     * @param p
     * @return
     */

    private boolean setActivePanel(Point p)
    {
        activePanel = null;
        Rectangle r = Imp.getBounds();
        if(r.contains(p))
            activePanel = Imp;
        r = p1.getBounds();
        if(r.contains(p))
            activePanel = p1;
        r = Op.getBounds();
        if(r.contains(p))
            activePanel = Op;
        r = p3.getBounds();
        if(r.contains(p))
            activePanel = p3;
        if(activePanel != null)
            return true;
        return false;
    }

    /**
     * method to check whether a mouse click was over an icon
     * @param p
     * @return
     */

    private Component getImageLabel(Point p)
    {
        Rectangle r1 = activePanel.getBounds();
        Rectangle r;
        Component[] c = activePanel.getComponents();
        for(int j = 0; j < c.length; j++) {
            r = c[j].getBounds();
            if (r.contains(p.x - r1.x, p.y - r1.y))
                return c[j];
        }
        return null;
    }
}