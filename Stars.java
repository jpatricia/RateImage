import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Stars extends JPanel implements Observer {
    private ImageCollectionModel model;

    Stars(ImageCollectionModel model_){
        model = model_;
    }

    public void paintComponent(Graphics g){
        JButton star = new JButton();

    }

    @Override
    public void update(Observable arg0, Object arg1){

    }

}
