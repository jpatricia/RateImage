import javax.swing.*;
import java.io.File;
import java.nio.file.attribute.FileTime;

public class ImageModel {
    public int rating; //for the rating of the image
    private Stars star;
    private ImageIcon imgIcon;
    private String imgPath;
    private String imgName;
    private FileTime imgDate;

    ImageModel(File f){
        System.out.println(f.getPath());
        imgPath = f.getPath();
        imgIcon = new ImageIcon(f.getPath());
        rating = 0;
        imgName = f.getName();
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

//    public FileTime getDate() throws IOException{
//        Path path = Paths.get(imgPath);
//        BasicFileAttributes attributes ;
//        try{
//            attributes= Files.readAttributes(path, BasicFileAttributes.class);
//            FileTime creationTime = attributes.creationTime();
//            imgDate = creationTime;
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return imgDate;
//    }
}
