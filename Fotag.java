import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Fotag {

    public Fotag(){
        JFrame f = new JFrame("Fotag");
        ImageCollectionModel model = new ImageCollectionModel();
        ImageCollectionView view = new ImageCollectionView(model);
        Toolbar toolbar = new Toolbar(model);

        model.addObserver(view);
        model.notifyObservers();

        JPanel p = new JPanel(new BorderLayout());
        f.getContentPane().add(p);
        f.setSize(800,600);

        //set background color of header and view
        toolbar.setBackground(new Color(242,255,249));
        toolbar.setMinimumSize(new Dimension(800,100));
        toolbar.setOpaque(true);
        view.setBackground(new Color(192,233,240));
        view.setOpaque(true);

        //add header and view to the panel
        p.add(toolbar,BorderLayout.NORTH);
        p.add(view,BorderLayout.CENTER);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit");
                try{
//                    FileWriter fstream = new FileWriter("list.txt");
//                    BufferedWriter out = new BufferedWriter(fstream);
                    File file = new File("listOfImages.txt");
                    FileOutputStream fs = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fs);
                    out.writeObject(model.ImageList);
                    out.close();

                } catch(IOException ex){

                }

            }
        });

    }

    public static void main(String[] args){
        Fotag fotag = new Fotag();
    }
}
