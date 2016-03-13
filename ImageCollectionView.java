import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer{
    private ImageCollectionModel model;
    //private JPanel panel;
    private boolean modified = false;

    ImageCollectionView(ImageCollectionModel model_){
        model = model_;

//        //this.setMaximumSize();
//        panel = new JPanel();
//        panel.setBackground(new Color(192,233,240));
//       // panel.setOpaque(true);
//        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS)); //default
//        panel.setSize(model.getWidth(),model.getHeight());


        this.setLayout(new FlowLayout()); //default

//        ArrayList<ImageModel> list = model.ImageList;
//        if(list.size() !=0){
//            System.out.println("imagelist is not empty");
//            for(int i=0;i<list.size();i++){
//                JLabel image = new JLabel(list.get(i).getIcon());
//                JLabel name = new JLabel(list.get(i).getName());
//
//                image.setOpaque(true);
//                name.setOpaque(true);
//
//                this.add(image);
//                this.add(name);
//                revalidate();
//            }
//        }

    }

    public void addImage(ArrayList<ImageModel> collection){
        System.out.println("loadImages");
        ArrayList<ImageModel> list = collection;
        int size = list.size();
        if(size !=0){
            for(int i=0;i<size;i++) {
                //create box for each image
                Box b = Box.createVerticalBox();
                Box imgBox = Box.createHorizontalBox();
                if (model.viewMode == "list") {
                    System.out.println("box list");
                    imgBox = Box.createHorizontalBox();
                    imgBox.setPreferredSize(new Dimension(300,200));
                } else if (model.viewMode == "grid") {
                    System.out.println("box grid");
                    imgBox = Box.createVerticalBox();
                    imgBox.setPreferredSize(new Dimension(300,300));
                }

                String title = list.get(i).getName();
                ImageIcon icon = list.get(i).getIcon();

                //image
                JLabel image = new JLabel();

                //image name
                JLabel name = new JLabel();
                name.setText(title);
                Image im = icon.getImage();

                //image date created
                JLabel dateCreated = new JLabel();
                dateCreated.setText(list.get(i).imgDate);

                //image rating stars
                Stars star = new Stars("view");
                if(list.get(i).rating !=0){
                    star.setRating(list.get(i).rating);
                }

                int widthImg = icon.getIconWidth();
                int heightImg = icon.getIconHeight();

                System.out.println(widthImg + " " + heightImg);

                if (widthImg >= 1000 || heightImg >= 1000) {
                    im = icon.getImage().getScaledInstance(widthImg / 10, heightImg / 10, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(im);
                    modified = true;
                } else if ((widthImg > 400 || heightImg > 400) && (widthImg < 1000 || heightImg < 1000)) {
                    im = icon.getImage().getScaledInstance(widthImg / 2, heightImg / 2, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(im);
                    modified = true;
                }

                Image img = im;
                image.setIcon(icon);

                image.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame imageWindow = new JFrame(title);
                        JLabel resized = new JLabel();
                        Image resizedImage;
                        ImageIcon resizedIcon;
                        if (modified) {
                            if (widthImg >= 1000 || heightImg >= 1000) {
                                System.out.println(">1000");

                                resizedImage = img.getScaledInstance(widthImg / 3, heightImg / 3, Image.SCALE_SMOOTH);
                                resizedIcon = new ImageIcon(resizedImage);
                                resized.setIcon(resizedIcon);
                                resized.setPreferredSize(new Dimension(widthImg / 3, heightImg / 3));
                            } else {
                                System.out.println("<1000");
                                resizedImage = img.getScaledInstance(widthImg, heightImg, Image.SCALE_SMOOTH);
                                resizedIcon = new ImageIcon(resizedImage);
                                resized.setIcon(resizedIcon);
                                resized.setPreferredSize(new Dimension(widthImg, widthImg));
                            }
                        } else {
                            System.out.println("normal size");
                            resized = image;
                        }
                        System.out.println("pref size: " + resized.getPreferredSize());
                        imageWindow.add(resized);
                        imageWindow.pack();
                        imageWindow.setVisible(true);
                    }

                });

                b.add(name);
                b.add(dateCreated);
                b.add(star);
                imgBox.add(image);
                imgBox.add(b);
                b.setOpaque(true);
                imgBox.setOpaque(true);
                star.setBackground(new Color(192, 233, 240));
//                imgBox.setBackground(Color.GRAY);
//                b.setBackground(Color.PINK);

                this.add(Box.createRigidArea(new Dimension(30, 30)));
                this.add(imgBox);
                // panelScroll.add(panel);

                // panel.setOpaque(true);
                //panelScroll.setOpaque(true);

                int j=i;
                //action listener for stars
                star.star1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.setRating(1);
                        model.setRatingImage(list.get(j),1);
                    }
                });

                star.star2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.setRating(2);
                        model.setRatingImage(list.get(j),2);
                    }
                });

                star.star3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.setRating(3);
                        model.setRatingImage(list.get(j),3);
                    }
                });

                star.star4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.setRating(4);
                        model.setRatingImage(list.get(j),4);
                    }
                });

                star.star5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.setRating(5);
                        model.setRatingImage(list.get(j),5);
                    }
                });

                star.clear.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        star.clearStar();
                        model.setRatingImage(list.get(j),0);
                    }
                });
            }

        }
    }

    public void filterImages(int ratingFilter){
        System.out.println("filter Image");
        ArrayList<ImageModel> list = new ArrayList<ImageModel>();
        for(int i=0;i<model.ImageList.size();i++){
            list.add(model.ImageList.get(i));
        }
        System.out.println("list size before filter: "+list.size());
        if(ratingFilter!=0){
            for(int i=list.size()-1;i>=0;i--){
                int imgrating = list.get(i).rating;
                if(imgrating > ratingFilter){
                    list.remove(i);
                }
            }
        }

        System.out.println("list size after filter: "+list.size());
        model.SecondList.clear();
        for(int i=0;i<list.size();i++){
            model.SecondList.add(list.get(i));
        }

        reDraw();
        revalidate();
    }

    public void reDraw() {
        int size = model.SecondList.size();
        if(size!=0){
            System.out.println("redraw");
            removeAll();
            repaint();
            //revalidate();
            addImage(model.SecondList);
        }

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("update ImageCollection");
        if(model.change == "add"){
            System.out.println("update add");
            removeAll();
            repaint();
            addImage(model.ImageList);
        }else if(model.change == "view"){
            removeAll();
            repaint();
            if(model.viewMode == "grid"){
                this.setLayout(new FlowLayout());
            }else if(model.viewMode == "list"){
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.setAlignmentX(LEFT_ALIGNMENT);
            }
            addImage(model.SecondList);
        }else if(model.change =="filter"){
            System.out.println("filter Rating update to: "+model.filterRating);
            filterImages(model.filterRating);
        }
        revalidate();
    }
}
