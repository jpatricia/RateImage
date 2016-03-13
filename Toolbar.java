import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        gridButton.setBackground(new Color(242,255,249));
        listButton.setBackground(new Color(242,255,249));
        title.setBackground(new Color(242,255,249));
        filter.setBackground(new Color(242,255,249));
        load.setBackground(new Color(242,255,249));
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
        starPanel.setBackground(new Color(242,255,249));
        right.setBackground(new Color(242,255,249));
        center.setBackground(new Color(242,255,249));
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
                    File prev = new File("listOfImages.txt");
                    if(prev.exists()){
                        System.out.println("file exist");
                        try{
                            FileReader fr = new FileReader(prev);
                            BufferedReader br = new BufferedReader((fr));
//                            FileInputStream fs = new FileInputStream(prev);
//                            ObjectInputStream ois = new ObjectInputStream(fs);
                            String pathImage = "";
                            for(String line; (line = br.readLine()) != null; ) {
                                //line is the path file
                                System.out.println(line);
                                if(isInteger(line)){
                                    //this line is the rating of the image
                                    System.out.println("path img: "+pathImage);
                                    File prevImg = new File(pathImage);
                                    int imgStarPrev = Integer.parseInt(line);
                                    System.out.println("strStarPrev: "+line);
                                    System.out.println("intStarPrev: "+imgStarPrev);
                                    ImageModel img = new ImageModel(prevImg,imgStarPrev);
                                    model.addImage(img);
                                }else if(!isInteger(line)){
                                    //this line is the path to the images
                                    System.out.println("line: "+ line);
                                    if(line.equals("")){
                                        System.out.println("NONE");
                                    }else{
                                        pathImage = line;
                                    }

                                }
                            }
                        } catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                    try {

                        for(int i=0;i<file.length;i++){
                            ImageModel img = new ImageModel(file[i],0);
                            model.addImage(img);
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

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void update(Observable arg0, Object arg1){
        System.out.println("Toolbar update");
        addItems();
        revalidate();
    }
}
