import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable{
    public ArrayList<ImageModel> ImageList;
    public ArrayList<ImageModel> SecondList;
    public boolean[] fullStar;
    public String viewMode;
    public String change;
    public int width,height;
    public int filterRating;

    ImageCollectionModel(){
        ImageList = new ArrayList<ImageModel>();
        SecondList = new ArrayList<ImageModel>();
        viewMode = "grid"; //default
        fullStar = new boolean[6];
        for(int i=1;i<6;i++) {
            fullStar[i] = false;
        }
    }

    public void addImage(ImageModel m){
        ImageList.add(m);
        SecondList.add(m);
        System.out.println("addImage: "+ImageList.size());
        change = "add";
        setChanged();
        notifyObservers();
    }

    public void setMode(String mode){
        viewMode = mode; //change to grid or list
        change = "view";
        setChanged();
        notifyObservers();
    }

    public void setSize(int w, int h){
        width = w;
        height = h;
    }

    public void setRatingImage(ImageModel imgModel,int rate){
        for(int i=0;i< ImageList.size();i++){
            if(ImageList.get(i) == imgModel){
                ImageList.get(i).RatingSet(rate);
            }
        }
    }

    public void updateStar(){
        System.out.println("update star");
        setChanged();
        notifyObservers();
    }

    public void prevData(File prev){
        System.out.println("file exist");
        try{
            FileReader fr = new FileReader(prev);
            BufferedReader br = new BufferedReader((fr));
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
                    addImage(img);
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

    public void updateView(){
        setChanged();
        notifyObservers();
    }


}
