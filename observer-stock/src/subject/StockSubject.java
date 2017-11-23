package subject;
import observer.*;

import java.util.Vector;

/**
 * Created by sss on 14/11/2017.
 */
public class StockSubject extends Thread implements Subject {

    protected Vector<Observer> observerVector=new Vector<Observer>();


    double openPrice;
    double currentPrice;
    double closePrice;
    double initPrice;



    /**
     *                      Basic   Rules
     *
     *               day 1
     *      | currentPrice-openPrice | / openPrice     < =  10%
     *
     *              day 2
     *      | currentPrice-openPrice | / openPrice     < =  10%
     *
     *      | openPrice-day1.closePrice | / day1.closePrice < = 10%
     *
     */


    @Override
    public void attach(Observer observer) {

        observerVector.add(observer);

    }

    @Override
    public void detach(Observer observer) {

        observerVector.remove(observer);
    }


    @Override
    public void run() {
        super.run();

        int times=0;

        //use per_day  to simulate a day
        int per_day=24;

        while (true) {


            double p=notifyObservers();

            if (times>=per_day) {
                times=0;

                //stop for a day

                openPrice=p;

            }

            try{
            sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

            times++;

        }


    }

    public StockSubject() {
    }

    public StockSubject(double openPrice) {


        this.openPrice = openPrice;
        this.initPrice = openPrice;
        currentPrice   = openPrice;
    }

    @Override
    public double notifyObservers() {

        double changeRate=1;
        double changePrice = 0;

        //set rate to 0.05 to simulate the real stock trend

        while (changeRate>0.05) {


            changePrice=((Math.random() * 0.2*currentPrice)-0.1*currentPrice)+currentPrice;
            changeRate=Math.abs(changePrice-openPrice)/openPrice;


        }


        currentPrice = changePrice;
        double[] data={openPrice,currentPrice,initPrice};


        for (Observer observer:observerVector){

            observer.update(data);

        }

        return currentPrice;

    }
}
