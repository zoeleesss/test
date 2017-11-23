package observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by sss on 14/11/2017.
 */
public class StockObserverTwo extends JPanel implements Observer {


    Graphics2D g2d ;

    Vector<Double> priceData=new Vector<Double>();
    double initPrice;


    int days=0;


    public StockObserverTwo() {

        JFrame jf = new JFrame();
        jf.setSize(600, 800);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(3);
        jf.getContentPane().add(this);

    }

    @Override
    public void update(double[] data) {


        //System.out.println("current price "+data[1]);
        priceData.add(data[1]);
        initPrice=data[2];

        repaint();

    }



    public void paint(Graphics g) {
        super.paintComponent(g);

        g2d=(Graphics2D) g;
        //g2d.setColor(Color.black);



        for (int i = 0; i < 21; i++) {

            g2d.drawLine(50,(i+2)*30,550,(i+2)*30);


            //g2d.drawLine((i+1)*50,60,(i+1)*50,660);
        }

        /*for (int x=0;x<240;x++)
        {
            g2d.drawLine(50+2*x,60,50+2*x,630);


        }*/





        for (int i1=10;i1>=0;i1--){

            int ii=10-i1;

            if (i1!=0) {

                //draw +/- n %        &     corresponding  prices

                //g2d.drawString("+" + i1 + "%", 20, (30) * (ii+2));

                int price=(int)(initPrice*(i1/100.0+1));
                g2d.drawString("$"+price,560,(30) * (ii+2));


                price=(int)(initPrice*(1-(i1/100.0)));
                //g2d.drawString("-" + i1 + "%", 20, 630 - 30 * (ii-1));
                g2d.drawString("$"+price,560,630 - 30 * (ii-1));

            }else{

                //      ground zero

                //g2d.drawString("0%",20,360);
                int price=(int)initPrice;
                g2d.drawString("$"+price,560,360);
            }

        }




        //use per_day  to simulate a day

        int per_day=24;






        for (int j=0;j<priceData.size()/per_day;j++)
        {


            double open_price=priceData.get(j*per_day);
            double close_price=priceData.get((j+1)*per_day-1);

            double highest_price=open_price;
            double lowest_price=open_price;

            for (int ge=j*per_day;ge<(j+1)*per_day;ge++)
            {
                double curr=priceData.get(ge);

                if (highest_price<curr){

                    highest_price=curr;

                }
                if (lowest_price>curr){

                    lowest_price=curr;

                }



            }


            int open_coordinates_y=(int)((1-(open_price-initPrice*0.9)/(0.2*initPrice))*600+60);
            int close_coordinates_y=(int)((1-(close_price-initPrice*0.9)/(0.2*initPrice))*600+60);
            int highest_coordinates_y=(int)((1-(highest_price-initPrice*0.9)/(0.2*initPrice))*600+60);
            int lowest_coordinates_y=(int)((1-(lowest_price-initPrice*0.9)/(0.2*initPrice))*600+60);


            g2d.drawLine(50*(j+1)+50,lowest_coordinates_y,50*(j+1)+50,highest_coordinates_y);


            if (open_price<close_price)

            {
                g2d.setColor(Color.red);

                g2d.fillRect(50*(j+1)+35,close_coordinates_y,35,open_coordinates_y-close_coordinates_y);
                g2d.setColor(Color.black);
            }
            else {
                g2d.setColor(Color.green);

                g2d.fillRect(50*(j+1)+35,close_coordinates_y,35,close_coordinates_y-open_coordinates_y);
                g2d.setColor(Color.black);
            }


            if ( days<(priceData.size()/per_day) && j==(priceData.size()/per_day)-1) {


                System.out.println("********* DAY " + days+"  **********");

                System.out.println(" hi " + highest_price);
                System.out.println(" lo " + lowest_price);
                System.out.println(" open " + open_price);
                System.out.println(" close " + close_price);



                days=priceData.size()/per_day;
            }




        }




    }




}
