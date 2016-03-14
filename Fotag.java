import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fotag {
    public Fotag(){
        JFrame f = new JFrame("Fotag");
        ImageCollectionModel model = new ImageCollectionModel();
        ImageCollectionView view = new ImageCollectionView(model);
        Toolbar toolbar = new Toolbar(model);
        JScrollPane panelScroll;

        model.addObserver(view);
        model.notifyObservers();

        File prev = new File("listOfImages.txt");
        if(prev.exists()) {
            model.prevData(prev);
            model.updateView();
        }

        JPanel p = new JPanel(new BorderLayout());
        f.getContentPane().add(p);
        f.setSize(1000,700);

        //set background color of header and view
        toolbar.setBackground(new Color(242,255,249));
        toolbar.setMinimumSize(new Dimension(800,100));
        toolbar.setOpaque(true);
        view.setBackground(new Color(242,255,249));
        view.setOpaque(true);

        panelScroll = new JScrollPane(view);
        view.setPreferredSize(new Dimension(900,2100));
        panelScroll.setPreferredSize(new Dimension(860,2000));
        panelScroll.setBackground(new Color(242,255,249));

        panelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //add header and view to the panel
        p.add(toolbar,BorderLayout.NORTH);
        p.add(panelScroll,BorderLayout.CENTER);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit");
                try{
                    FileWriter fstream = new FileWriter("listOfImages.txt");
                    PrintWriter writer = new PrintWriter(fstream);
                    BufferedWriter out = new BufferedWriter(writer);
                    for(int i=0;i<model.ImageList.size();i++){
                        out.write(model.ImageList.get(i).getPath()+"\n");
                        out.newLine();
                        out.write(model.ImageList.get(i).rating+"\n");
                        out.newLine();
                    }
                    out.close();

                } catch(IOException ex){

                }

            }
        });

        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                model.setSize(f.getWidth(),f.getHeight());
            }
        });

    }

    public static void main(String[] args){
        Fotag fotag = new Fotag();
    }
}
