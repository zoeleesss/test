/**
 * Created by sss on 14/11/2017.
 */

import observer.*;
import subject.*;

public class StockGUI {



    public static void main(String [] agrs){


        StockSubject generator=new StockSubject(100);
        Observer observer1=new StockObserverOne();
        Observer observer2=new StockObserverTwo();

        generator.attach(observer1);
        generator.attach(observer2);

        generator.start();

        System.out.println(" start ");

        //generator.detach(observer2);

        //System.out.println(" only 1 left ");



        //generator.attach(observer2);

        System.out.println(" 2 left ");











    }


}
