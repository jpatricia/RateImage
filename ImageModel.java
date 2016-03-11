import javax.swing.*;
import java.io.File;
import java.util.Date;

public class ImageModel {
    private int rating; //for the rating of the image
    private Stars star;
    private ImageIcon imgIcon;
    private String imgPath;
    private String imgName;
    private Date imgDate;

    ImageModel(File f){
        System.out.println(f.getPath());
        imgIcon = new ImageIcon(f.getPath());
        rating = 0;
        imgName = f.getName();

    }

    public ImageIcon getIcon(){
        return imgIcon;
    }

    public int getRating(){
        return rating;
    }

    public String getName(){
        return imgName;
    }

    public Date getDate(){
        return imgDate;
    }
}
