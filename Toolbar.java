import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Toolbar extends JPanel implements Observer{
    private ImageCollectionModel model;
    private JPanel starPanel;
    private Stars starButton;

    Toolbar(ImageCollectionModel model_){
        model = model_;
        starPanel = new JPanel();
        starPanel.setLayout(new GridLayout(0,5));

        addItems();
        this.setBackground(new Color(242,255,249));
        this.add(starPanel);
    }

    void addItems(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JButton gridButton = new JButton();
        JButton listButton = new JButton();
        JLabel title = new JLabel("Fotag!");
        JButton load = new JButton();
        JLabel filter = new JLabel("Filter by: ");
//        JButton star1 = new JButton();
//        JButton star2 = new JButton();
//        JButton star3 = new JButton();
//        JButton star4 = new JButton();
//        JButton star5 = new JButton();

        gridButton.setIcon(new ImageIcon("grid_icon.png"));
        listButton.setIcon(new ImageIcon("list_icon.png"));
        load.setIcon(new ImageIcon("load_icon.png"));

//        star1.setIcon(new ImageIcon("star_empty.png"));
//        star2.setIcon(new ImageIcon("star_full.png"));
//        star3.setIcon(new ImageIcon("star_full.png"));
//        star4.setIcon(new ImageIcon("star_full.png"));
//        star5.setIcon(new ImageIcon("star_full.png"));

        //set each item to have the same color as Toolbar background
        gridButton.setBackground(new Color(242,255,249));
        listButton.setBackground(new Color(242,255,249));
        title.setBackground(new Color(242,255,249));
        filter.setBackground(new Color(242,255,249));
        load.setBackground(new Color(242,255,249));
//        star1.setBackground(new Color(242,255,249));
//        star2.setBackground(new Color(242,255,249));
//        star3.setBackground(new Color(242,255,249));
//        star4.setBackground(new Color(242,255,249));
//        star5.setBackground(new Color(242,255,249));


//        star1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                star1.setIcon(new ImageIcon("star_full.png"));
//            }
////            @Override
////            public void mouseExited(MouseEvent e) {
////                star1.setIcon(new ImageIcon("star_empty.png"));
////            }
//            @Override
//            public void mousePressed(MouseEvent e){
//                star1.setIcon(new ImageIcon("star_full.png"));
//            }
//        });




        gridButton.setBorderPainted(false);
        listButton.setBorderPainted(false);
        load.setBorderPainted(false);
//        star1.setBorderPainted(false);
//        star2.setBorderPainted(false);
//        star3.setBorderPainted(false);
//        star4.setBorderPainted(false);
//        star5.setBorderPainted(false);

        //set font,size and color for JLabel
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
        filter.setFont(new Font("Arial",Font.BOLD, 20));
        filter.setForeground(Color.BLACK);
        title.setForeground(Color.BLACK);

        //set everything to opaque true
        gridButton.setOpaque(true);
        listButton.setOpaque(true);
        title.setOpaque(true);
        filter.setOpaque(true);
        load.setOpaque(true);
//        star1.setOpaque(true);
//        star2.setOpaque(true);
//        star3.setOpaque(true);
//        star4.setOpaque(true);
//        star5.setOpaque(true);

//        starPanel.add(star1);
//        starPanel.add(star2);
//        starPanel.add(star3);
//        starPanel.add(star4);
//        starPanel.add(star5);

        //add grid button
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.WEST;
        this.add(gridButton,gc);
        gc.gridx++;

        //add list button
        gc.weightx=1.0;
        gc.anchor = GridBagConstraints.WEST;
        this.add(listButton,gc);
        gc.gridx++;

        //add title
       // gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(title, gc);
        gc.gridx+=4;

        //add list button
       // gc.fill = GridBagConstraints.NONE;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 0.5;
        gc.anchor = GridBagConstraints.EAST;
        this.add(load,gc);
        gc.gridx+=3;

        gc.weightx = 0.5;
        gc.anchor = GridBagConstraints.EAST;
        this.add(filter,gc);
        gc.gridx++;

        gc.fill = GridBagConstraints.EAST;
       // gc.weightx = 0.5;
        gc.anchor = GridBagConstraints.EAST;
        this.add(starPanel,gc);



////        gc.weightx = 1.0;
//        gc.anchor = GridBagConstraints.EAST;
//        this.add(star2,gc);
//        gc.gridx++;
//
////        gc.weightx = 1.0;
//        gc.anchor = GridBagConstraints.EAST;
//        this.add(star3,gc);
//        gc.gridx++;
//
////        gc.weightx = 1.0;
//        gc.anchor = GridBagConstraints.EAST;
//        this.add(star4,gc);
//        gc.gridx++;
//
//        gc.weightx = 0.5;
//        gc.anchor = GridBagConstraints.EAST;
//        this.add(star5,gc);
//        gc.gridx++;


        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                File workingDirectory = new File(System.getProperty("user.dir"));
                fileChooser.setCurrentDirectory(workingDirectory);
                fileChooser.setMultiSelectionEnabled(true);
                int returnVal = fileChooser.showOpenDialog(getParent());

                //If user chooses to open a file
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File[] file = fileChooser.getSelectedFiles();

                    try {
                        for(int i=0;i<file.length;i++){
                            ImageModel img = new ImageModel(file[i]);
                            model.addImage(img);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void update(Observable arg0, Object arg1){

    }
}
