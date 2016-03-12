import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable{
    private boolean first = true;
    private Stars starButton;
    public ArrayList<ImageModel> ImageList;
    public String viewMode;
    public String change;

    ImageCollectionModel(){
        ImageList = new ArrayList<ImageModel>();
        viewMode = "grid"; //default
    }

    public void getPrevImages(ArrayList<ImageModel> prev){
        System.out.println("prev size: "+prev.size());
    }

    public void addImage(ImageModel m){
        ImageList.add(m);
        System.out.println(ImageList.size());
        change = "add";
        setChanged();
        notifyObservers();
    }

    public void setMode(String mode){
        viewMode = mode;
        change = "view";
        setChanged();
        notifyObservers();
    }

    public String getviewMode(){
        return viewMode;
    }


}
