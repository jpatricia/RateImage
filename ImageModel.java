import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ImageModel {
    public int rating; //for the rating of the image
    private Stars star;
    private ImageIcon imgIcon;
    private String imgPath;
    private String imgName;
    public String imgDate;

    ImageModel(File f){
        System.out.println(f.getPath());
        imgPath = f.getPath();
        imgIcon = new ImageIcon(f.getPath());
        rating = 0;
        imgName = f.getName();

        Path path = Paths.get(imgPath);
        BasicFileAttributes attributes ;
        try{
            attributes= Files.readAttributes(path, BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(creationTime.toMillis());
            imgDate = date;
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public ImageIcon getIcon(){
        return imgIcon;
    }

    public String getName(){
        return imgName;
    }

    public void RatingSet(int r){
        rating = r;
    }

}
