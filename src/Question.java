import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by digibrose on 27/10/2015.
 * Class that holds all the elements to each question, Background Image, SymLabels telling the ImagePanel which icons
 * to have and their positions in the answer, called SymHooks.  Also an integer array that matches SymHooks and says
 * which type should be where.  Lastly the Question
 */
public class Question {

    /**
     * Declarations
     */

    private Image SymBackground;
    private Rectangle[] SymHooks;
    private SymLabels[] SymLabels;
    private String Question;
    private int[] Answer;
    private boolean[] AnswerChecker;
    private boolean summedcheck;
    private String SB;


    /**
     * Constructor Method to set up question requiring placing icons on the SymImage Panel
     * @param SymBackground
     * @param SymHooks
     * @param SymLabels
     * @param Answer
     * @param Question
     */

    public Question(String SymBackground, Rectangle[] SymHooks, SymLabels[] SymLabels, int[] Answer, String Question){

        this.SB = SymBackground;
        URL urlBackground = getClass().getResource(this.SB);
        System.out.println("URL is " + urlBackground);
        try {
          //  this.SymBackground = new ImageIcon(urlBackground).getImage();
           // this.SymBackground = new ImageIcon(String.valueOf(urlBackground)).getImage();
            this.SymBackground = ImageIO.read(urlBackground);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println(SymBackground);
            System.out.println(urlBackground);
            System.out.println(SymLabels[0].getSymImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // this.SymBackground = ImageIO.read(urlBackground);
        this.SymHooks = SymHooks;
        this.SymLabels = SymLabels;
        this.Answer = Answer;
        this.Question = Question;
    }

    /**
     * Method to set a new question
     * @param Question
     */

    public void SetQuestion(String Question){
        this.Question = Question;
    }

    /**
     * Method to get question string
     * @return
     */

    public String getQuestion(){
        return this.Question;
    }

    /**
     * Method to get the array of correct Icon positions
     * @return
     */

    public Rectangle[] getSymHooks(){
        return this.SymHooks;
    }

    /**
     * Method to get the array of icons associated with the question.
     * @return
     */

    public SymLabels[] getSymLabels(){
        return this.SymLabels;
    }

    /**
     * Method to get the background image for the SymImage panel
     * @return
     */

    public Image getSymBackground(){
        return this.SymBackground;
    }

    /**
     * Method to get the correct answer array
     * @return
     */

    public int[] getAnswer(){
      return Answer;
    }

    /**
     * Method that checks whether the Icon positions on as passed as a component array matches the Symhooks array
     * @param Ans
     * @return
     */

    public boolean CheckAnswer(Component[] Ans){
        AnswerChecker = new boolean[SymHooks.length];
            for (int i = 0; i < SymHooks.length; i++) {
                for (int j = 0; j < Ans.length; j++) {
                    if (SymHooks[i].intersects(Ans[j].getBounds()) && Answer[j] == Integer.parseInt(Ans[j].getName())){
                    AnswerChecker[i] = true;
                }
            }
        }
        summedcheck = true;
        for (int k = 0; k< AnswerChecker.length;k++){
            if (!AnswerChecker[k]){
                summedcheck = false;
            }
        }
        return summedcheck;
    }

}
