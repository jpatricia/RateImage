
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ImageCollectionView extends JPanel implements Observer{
    private ImageCollectionModel model;

    ImageCollectionView(ImageCollectionModel model_){
        model = model_;
        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10)); //default
    }

    public void addImages(ArrayList<ImageModel> collection){
        System.out.println("loadImages");
        ArrayList<ImageModel> list = collection;
        int size = list.size();
        if(size !=0){
            for(int i=0;i<size;i++) {
                //create box for each image
                JPanel dataBox = new JPanel();
                dataBox.setLayout(new GridLayout(2,1));
                Box nameDate = Box.createVerticalBox();
                Box imgBox = Box.createHorizontalBox();
                if (model.viewMode == "list") {
                    System.out.println("box list");
                    imgBox = Box.createHorizontalBox();
                } else if (model.viewMode == "grid") {
                    System.out.println("box grid");
                    imgBox = Box.createVerticalBox();
                }

                String title = list.get(i).getName();
                ImageIcon icon = list.get(i).getIcon();
                Image imageReal = icon.getImage();

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

                //set the font of JLabel
                name.setFont(new Font("Arial",Font.PLAIN, 17));
                dateCreated.setFont(new Font("Arial",Font.PLAIN, 17));

                int widthImg = icon.getIconWidth();
                int heightImg = icon.getIconHeight();

                System.out.println(widthImg + " " + heightImg);

                im = icon.getImage().getScaledInstance(250,200, Image.SCALE_SMOOTH);
                icon = new ImageIcon(im);

                Image img = imageReal;
                image.setIcon(icon);

                image.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFrame imageWindow = new JFrame(title);
                        JLabel resized = new JLabel();
                        Image resizedImage;
                        ImageIcon resizedIcon;

                        if (widthImg >= 1000 || heightImg >= 1000) {
                            System.out.println(">1000");

                            resizedImage = img.getScaledInstance(widthImg/4, heightImg/4, Image.SCALE_SMOOTH);
                            resizedIcon = new ImageIcon(resizedImage);
                            resized.setIcon(resizedIcon);
                            resized.setPreferredSize(new Dimension(widthImg/4, heightImg/4));
                        } else {
                            System.out.println("<1000");
                            resizedImage = img.getScaledInstance(widthImg, heightImg, Image.SCALE_SMOOTH);
                            resizedIcon = new ImageIcon(resizedImage);
                            resized.setIcon(resizedIcon);
                            resized.setPreferredSize(new Dimension(widthImg, widthImg));
                        }

                        System.out.println("pref size: " + resized.getPreferredSize());
                        imageWindow.add(resized);
                        imageWindow.setMaximumSize(new Dimension(800,600));
                        imageWindow.pack();
                        imageWindow.setVisible(true);
                    }

                });

                //setting up the alignment
                if (model.viewMode == "list") {
                    imgBox.setPreferredSize(new Dimension(300,200));
                    image.setPreferredSize(new Dimension(200,200));
                    image.setAlignmentX(Component.LEFT_ALIGNMENT);
                    name.setAlignmentX(Component.LEFT_ALIGNMENT);
                    dateCreated.setAlignmentX(Component.LEFT_ALIGNMENT);

                    name.setVerticalAlignment(SwingConstants.CENTER);
                    name.setHorizontalAlignment(SwingConstants.CENTER);

                    nameDate.setMaximumSize(new Dimension(200,70));
                    nameDate.setAlignmentX(Component.LEFT_ALIGNMENT);
//                    nameDate.setAlignmentY(Component.BOTTOM_ALIGNMENT);

                    dataBox.setMaximumSize(new Dimension(300,200));
                    dataBox.setAlignmentX(Component.LEFT_ALIGNMENT);
//                    dataBox.setAlignmentY(Component.CENTER_ALIGNMENT);

                    imgBox.setAlignmentX(Component.LEFT_ALIGNMENT);

                    //add data of the image
                    nameDate.add(Box.createHorizontalStrut(10));
                    nameDate.add(name);
                    nameDate.add(dateCreated);
                    nameDate.setOpaque(true);
                    dataBox.setOpaque(true);

                    imgBox.add(image);
                    imgBox.add(Box.createHorizontalStrut(10));

                } else if (model.viewMode == "grid") {
                    this.add(Box.createVerticalStrut(10));
                    if(i==3) this.add(Box.createHorizontalStrut(10));
                    imgBox.setPreferredSize(new Dimension(300,300));
                    imgBox.setAlignmentX(Component.LEFT_ALIGNMENT);
                    imgBox.setMaximumSize(new Dimension(300,200));
                    image.setAlignmentX(Component.CENTER_ALIGNMENT);
                    image.setAlignmentY(Component.TOP_ALIGNMENT);
                    name.setAlignmentX(Component.CENTER_ALIGNMENT);
                    dateCreated.setAlignmentX(Component.CENTER_ALIGNMENT);
                    nameDate.setMaximumSize(new Dimension(100,100));

                    nameDate.add(name);
                    nameDate.add(dateCreated);
                    nameDate.setOpaque(true);
                    dataBox.setOpaque(true);
                    imgBox.add(image);
                }

                dataBox.add(nameDate);
                dataBox.add(star);

                imgBox.add(dataBox);
                imgBox.setOpaque(true);
                star.setBackground(new Color(242,255,249));
                nameDate.setBackground(new Color(242,255,249));

                //testing purpose only
//                imgBox.setBackground(Color.GRAY);
//                dataBox.setBackground(Color.PINK);
//                nameDate.setBackground(Color.CYAN);

                this.add(Box.createRigidArea(new Dimension(30, 30)));
                this.add(imgBox);

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
                if(imgrating < ratingFilter){
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
            addImages(model.SecondList);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("update ImageCollection");
        if(model.change == "add"){
            System.out.println("update add");
            removeAll();
            repaint();
            addImages(model.ImageList);
        }else if(model.change == "view"){
            removeAll();
            repaint();
            if(model.viewMode == "grid"){
                this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
                this.setAlignmentX(Component.LEFT_ALIGNMENT);
            }else if(model.viewMode == "list"){
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.setAlignmentX(Component.LEFT_ALIGNMENT);
                this.setAlignmentY(Component.TOP_ALIGNMENT);
            }
            addImages(model.SecondList);
        }else if(model.change =="filter"){
            System.out.println("filter Rating update to: "+model.filterRating);
            filterImages(model.filterRating);
        }
        revalidate();
    }
}