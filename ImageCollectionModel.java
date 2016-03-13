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
        for(int i=1;i<6;i++){
            fullStar[i] = false;
        }
    }

    public void getPrevImages(ArrayList<ImageModel> prev){
        System.out.println("prev size: "+prev.size());
    }

    public void addImage(ImageModel m){
        ImageList.add(m);
        SecondList.add(m);
        System.out.println(ImageList.size());
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

    public int getRatingImage(ImageModel imgModel){
        for(int i=0;i< ImageList.size();i++){
            if(ImageList.get(i) == imgModel){
                return ImageList.get(i).rating;
            }
        }
        return 0;
    }

    public void updateStar(){
        System.out.println("update star");
        setChanged();
        notifyObservers();
    }


}
