import java.util.ArrayList;
import java.util.Observable;

public class ImageCollectionModel extends Observable{
    private boolean first = true;
    private Stars starButton;
    public ArrayList<ImageModel> ImageList;

    ImageCollectionModel(){
        ImageList = new ArrayList<ImageModel>();
    }

    public void addImage(ImageModel m){
        ImageList.add(m);
        System.out.println(ImageList.size());
        setChanged();
        notifyObservers();
    }

}
