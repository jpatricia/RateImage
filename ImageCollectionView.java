import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer{
    private ImageCollectionModel model;
    private JScrollPane panel;

    ImageCollectionView(ImageCollectionModel model_){
        model = model_;
        panel = new JScrollPane();

        //this.setMaximumSize();
        //panel.setBackground(new Color(192,233,240));

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); //default

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

    }

    public void loadImages(){

        ArrayList<ImageModel> list = model.ImageList;
        int size = list.size();
        if(size !=0){
            Box b = Box.createVerticalBox();
            JLabel image = new JLabel();
            JLabel name = new JLabel(list.get(size-1).getName());
           // JLabel date = new JLabel(list.get(size-1).getDate());
            String title = list.get(size-1).getName();
            ImageIcon icon = list.get(size-1).getIcon();

            int widthImg = icon.getIconWidth();
            int heightImg = icon.getIconHeight();

            System.out.println(widthImg+ " "+heightImg);

            if(widthImg > 1000 || heightImg > 1000){
                Image im = icon.getImage().getScaledInstance(widthImg/10,heightImg/10,Image.SCALE_SMOOTH);
                icon =new ImageIcon(im);
            }

            image.setIcon(icon);


           // image.setOpaque(true); //these two are commented because
           // name.setOpaque(true); //apparently it'll make bgrd grey

            image.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame imageWindow = new JFrame(title);
                    imageWindow.add(image);
                    imageWindow.setSize(image.getSize());
                    imageWindow.setVisible(true);
                    imageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }

                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
            });

            b.add(image);
            b.add(name);
            this.add(Box.createRigidArea(new Dimension(30,0)));
            this.add(b);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("update ImageCollection");
        if(model.change == "add"){
            loadImages();
        }else if(model.change == "view"){
            if(model.getviewMode() == "grid"){
                this.setLayout(new FlowLayout());
            }else if(model.getviewMode() == "list"){
                this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            }
        }
        revalidate();
    }
}
