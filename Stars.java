import javax.swing.*;
import java.awt.*;

public class Stars extends JPanel {
    private boolean[] filled;
    private String content;
    public JButton star1;
    public JButton star2;
    public JButton star3;
    public JButton star4;
    public JButton star5;
    public JButton clear;

    Stars(String c){

        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setMaximumSize(new Dimension(300,30));
        content = c;
        filled = new boolean[6];
        for(int i=0;i<6;i++){
            filled[i] = false; //empty star
        }

        star1 = new JButton();
        star2 = new JButton();
        star3 = new JButton();
        star4 = new JButton();
        star5 = new JButton();
        clear = new JButton();

        star1.setIcon(new ImageIcon("star_empty.png"));
        star2.setIcon(new ImageIcon("star_empty.png"));
        star3.setIcon(new ImageIcon("star_empty.png"));
        star4.setIcon(new ImageIcon("star_empty.png"));
        star5.setIcon(new ImageIcon("star_empty.png"));
        clear.setIcon(new ImageIcon("clear_icon.png"));

        if(content =="toolbar"){
            star1.setBackground(new Color(242,255,249));
            star2.setBackground(new Color(242,255,249));
            star3.setBackground(new Color(242,255,249));
            star4.setBackground(new Color(242,255,249));
            star5.setBackground(new Color(242,255,249));
            clear.setBackground(new Color(242,255,249));
        }else if(content == "view"){
            star1.setBackground(new Color(192,233,240));
            star2.setBackground(new Color(192,233,240));
            star3.setBackground(new Color(192,233,240));
            star4.setBackground(new Color(192,233,240));
            star5.setBackground(new Color(192,233,240));
            clear.setBackground(new Color(192,233,240));
        }

        star1.setBorderPainted(false);
        star2.setBorderPainted(false);
        star3.setBorderPainted(false);
        star4.setBorderPainted(false);
        star5.setBorderPainted(false);
        clear.setBorderPainted(false);

        star1.setOpaque(true);
        star2.setOpaque(true);
        star3.setOpaque(true);
        star4.setOpaque(true);
        star5.setOpaque(true);
        clear.setOpaque(true);

        this.add(star1);
        this.add(star2);
        this.add(star3);
        this.add(star4);
        this.add(star5);
        this.add(clear);
        this.setBackground(new Color(242,255,249));
    }

    public void drawStar(){
        System.out.println("drawStar");
        for(int i=1;i<6;i++){ //1 to 5
            if(i==1){
                if(filled[1]) star1.setIcon(new ImageIcon("star_full.png"));
                else star1.setIcon(new ImageIcon("star_empty.png"));
            }else if(i==2){
                if(filled[2]) star2.setIcon(new ImageIcon("star_full.png"));
                else star2.setIcon(new ImageIcon("star_empty.png"));
            }else if(i==3){
                if(filled[3]) star3.setIcon(new ImageIcon("star_full.png"));
                else star3.setIcon(new ImageIcon("star_empty.png"));
            }else if(i==4){
                if(filled[4]) star4.setIcon(new ImageIcon("star_full.png"));
                else star4.setIcon(new ImageIcon("star_empty.png"));
            }else if(i==5){
                if(filled[5]) star5.setIcon(new ImageIcon("star_full.png"));
                else star5.setIcon(new ImageIcon("star_empty.png"));
            }
        }
    }

    public void setRating(int rate){
        System.out.println("setRating: "+rate);
        if(filled[rate]){
            //rating is changed from full to empty
            for(int i = rate+1;i<filled.length;i++){
                filled[i] = false;
            }
        }else{
            for(int i=1;i<=rate;i++){
                filled[i] = true;
            }
        }
        drawStar();
    }

    public void clearStar(){
        System.out.println("clear star function");
        for(int i=1;i<6;i++){
            filled[i] = false;
        }
        drawStar();
    }

}
