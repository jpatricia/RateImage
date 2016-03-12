import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer{
    private ImageCollectionModel model;
    private JScrollPane panelScroll;
    private JPanel panel;
    private boolean modified = false;

    ImageCollectionView(ImageCollectionModel model_){
        model = model_;

        //this.setMaximumSize();
        panel = new JPanel();
        panel.setBackground(new Color(192,233,240));
       // panel.setOpaque(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS)); //default
        panel.setSize(model.getWidth(),model.getHeight());

        panelScroll = new JScrollPane(panel);
        panelScroll.setSize(model.getWidth(),model.getHeight());
        panelScroll.setBackground(new Color(192,233,240));

        panelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(panelScroll);

//        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); //default

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
            if(model.viewMode =="list") b = Box.createHorizontalBox();

            JLabel image = new JLabel();
            JLabel name = new JLabel(list.get(size-1).getName());
           // JLabel date = new JLabel(list.get(size-1).getDate());
            String title = list.get(size-1).getName();
            ImageIcon icon = list.get(size-1).getIcon();
            Image im=icon.getImage();

            int widthImg = icon.getIconWidth();
            int heightImg = icon.getIconHeight();

            System.out.println(widthImg+ " "+heightImg);

            if(widthImg >= 1000 || heightImg >= 1000){
                im = icon.getImage().getScaledInstance(widthImg/10,heightImg/10,Image.SCALE_SMOOTH);
                icon =new ImageIcon(im);
                modified = true;
            }else if((widthImg > 400 || heightImg > 400) && (widthImg < 1000 || heightImg < 1000)){
                im = icon.getImage().getScaledInstance(widthImg/2,heightImg/2,Image.SCALE_SMOOTH);
                icon =new ImageIcon(im);
                modified = true;
            }

            Image img = im;
            image.setIcon(icon);


           // image.setOpaque(true); //these two are commented because
           // name.setOpaque(true); //apparently it'll make bgrd grey

            image.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame imageWindow = new JFrame(title);
                    JLabel resized = new JLabel();
                    Image resizedImage;
                    ImageIcon resizedIcon;
                    if(modified){
                        if(widthImg >= 1000 || heightImg >= 1000){
                            System.out.println(">1000");

                            resizedImage = img.getScaledInstance(widthImg/3,heightImg/3,Image.SCALE_SMOOTH);
                            resizedIcon = new ImageIcon(resizedImage);
                            resized.setIcon(resizedIcon);
                            resized.setPreferredSize(new Dimension(widthImg/3,heightImg/3));
                        }else{
                            System.out.println("<1000");
                            resizedImage = img.getScaledInstance(widthImg,heightImg,Image.SCALE_SMOOTH);
                            resizedIcon = new ImageIcon(resizedImage);
                            resized.setIcon(resizedIcon);
                            resized.setPreferredSize(new Dimension(widthImg,widthImg));
                        }
                    }else{
                        System.out.println("normal size");
                        resized = image;
                    }
                    System.out.println("pref size: "+resized.getPreferredSize());
                    imageWindow.add(resized);
                    //imageWindow.setSize(resized.getPreferredSize());
                    imageWindow.pack();
                    imageWindow.setVisible(true);
                }

            });

            b.add(image);
            b.add(name);
            b.setOpaque(true);
            panel.add(Box.createRigidArea(new Dimension(30,30)));
            panel.add(b);
           // panelScroll.add(panel);

           // panel.setOpaque(true);
            //panelScroll.setOpaque(true);

        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("update ImageCollection");
        if(model.change == "add"){
            loadImages();
        }else if(model.change == "view"){
            if(model.getviewMode() == "grid"){
                panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
            }else if(model.getviewMode() == "list"){
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            }
        }
        panelScroll.setSize(model.getWidth(),model.getHeight());
        revalidate();
    }
}
