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
    private JPanel left;
    private JPanel right;
    private JPanel center;
    private Stars starButton;

    Toolbar(ImageCollectionModel model_){
        model = model_;
        starPanel = new JPanel();
        starButton = new Stars("toolbar");
        left = new JPanel(new GridLayout(0,2));
        right = new JPanel(new FlowLayout());
        center = new JPanel(new GridLayout(0,1));
        starPanel = new JPanel(new FlowLayout());

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(242,255,249));
        this.setOpaque(true);
        addItems();
    }

    void addItems(){
        System.out.println("addItems toolbar");

        JButton gridButton = new JButton();
        JButton listButton = new JButton();
        JLabel title = new JLabel("Fotag!");
        JButton load = new JButton();
        JLabel filter = new JLabel("Filter by: ");

        //add image to the JLabel(grid, list and load)
        gridButton.setIcon(new ImageIcon("grid_icon.png"));
        listButton.setIcon(new ImageIcon("list_icon.png"));
        load.setIcon(new ImageIcon("load_icon.png"));

        //set each item to have the same color as Toolbar background
        gridButton.setBackground(new Color(192,233,240));
        listButton.setBackground(new Color(192,233,240));
        title.setBackground(new Color(192,233,240));
        filter.setBackground(new Color(192,233,240));
        load.setBackground(new Color(192,233,240));
        title.setSize(500,100);

        //set the buttons to not have border
        gridButton.setBorderPainted(false);
        listButton.setBorderPainted(false);
        load.setBorderPainted(false);


        //set font,size and color for JLabel
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 38));
        filter.setFont(new Font("Arial",Font.BOLD, 20));
        filter.setForeground(Color.BLACK);
        title.setForeground(Color.BLACK);
        title.setLocation(300,10);

        //set everything to opaque true
        gridButton.setOpaque(true);
        listButton.setOpaque(true);
        filter.setOpaque(true);
        load.setOpaque(true);
        title.setOpaque(true);

        //add components to the panel
        starPanel.add(starButton);
        left.add(gridButton);
        left.add(listButton);
        center.add(title);
        right.add(load);
        right.add(filter);
        right.add(starPanel);

        //set background of right panel
        starPanel.setBackground(new Color(192,233,240));
        right.setBackground(new Color(192,233,240));
        center.setBackground(new Color(192,233,240));
        center.setOpaque(true);

        //add panels to view
        this.add(left, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);

        //load button listener
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
                            ImageModel img = new ImageModel(file[i],0);
                            model.addImage(img);
                        }
                        if(model.filterRating!=0){
                            starButton.clearStar();
                            model.filterRating = 0;
                            model.change = "filter";
                            model.updateStar();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setMode("grid");
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setMode("list");
            }
        });

        starButton.star1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.setRating(1);
                model.filterRating = 1;
                model.change = "filter";
                model.updateStar();
            }
        });

        starButton.star2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.setRating(2);
                model.filterRating = 2;
                model.change = "filter";
                model.updateStar();
            }
        });

        starButton.star3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.setRating(3);
                model.filterRating = 3;
                model.change = "filter";
                model.updateStar();
            }
        });

        starButton.star4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.setRating(4);
                model.filterRating = 4;
                model.change = "filter";
                model.updateStar();
            }
        });

        starButton.star5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.setRating(5);
                model.filterRating = 5;
                model.change = "filter";
                model.updateStar();
            }
        });

        starButton.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starButton.clearStar();
                model.filterRating = 0;
                model.change = "filter";
                model.updateStar();
            }
        });

    }



    @Override
    public void update(Observable arg0, Object arg1){
        System.out.println("Toolbar update");
        addItems();
        revalidate();
    }
}
