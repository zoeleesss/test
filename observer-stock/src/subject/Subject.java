package subject;
import observer.*;
/**
 * Created by sss on 14/11/2017.
 */
public interface Subject{

    public void attach(Observer observer);

    public void detach(Observer observer);

    double notifyObservers();



}
