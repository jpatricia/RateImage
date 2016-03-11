import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer{
    private ImageCollectionModel model;
   // private JScrollPane panel;

    ImageCollectionView(ImageCollectionModel model_){
        model = model_;

       // panel = new JScrollPane();

        this.setLayout(new GridLayout());
      //  panel.setBackground(new Color(192,233,240));

        ArrayList<ImageModel> list = model.ImageList;
        if(list.size() !=0){
            System.out.println("imagelist is not empty");
            for(int i=0;i<list.size();i++){
                JLabel image = new JLabel(list.get(i).getIcon());
                JLabel name = new JLabel(list.get(i).getName());

                image.setOpaque(true);
                name.setOpaque(true);

                this.add(image);
                this.add(name);
                revalidate();
            }
        }

       // this.add(panel);
    }

    public void loadImages(){
        ArrayList<ImageModel> list = model.ImageList;
        if(list.size() !=0){
            for(int i=0;i<list.size();i++){
                JLabel image = new JLabel(list.get(i).getIcon());
                JLabel name = new JLabel(list.get(i).getName());
                String title = list.get(i).getName();

                image.setOpaque(true);
                name.setOpaque(true);

                image.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame imageWindow = new JFrame(title);
                        imageWindow.add(image);
                        imageWindow.setSize(image.getSize());
                        imageWindow.setVisible(true);
                        imageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                });

                this.add(image);
                this.add(name);
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("update ImageCollection");
        loadImages();
        revalidate();
    }
}
