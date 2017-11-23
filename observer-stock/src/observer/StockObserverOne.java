package observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by sss on 14/11/2017.
 */
public class StockObserverOne extends JPanel implements Observer {



    Graphics2D g2d ;

    Vector<Double> priceData=new Vector<Double>();
    double initPrice;


    public StockObserverOne() {

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
        g2d.setColor(Color.black);





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

                g2d.drawString("+" + i1 + "%", 20, (30) * (ii+2));

                int price=(int)(initPrice*(i1/100.0+1));
                g2d.drawString("$"+price,560,(30) * (ii+2));


                price=(int)(initPrice*(1-(i1/100.0)));
                g2d.drawString("-" + i1 + "%", 20, 630 - 30 * (ii-1));
                g2d.drawString("$"+price,560,630 - 30 * (ii-1));

            }else{

                //      ground zero

                g2d.drawString("0%",20,360);
                int price=(int)initPrice;
                g2d.drawString("$"+price,560,360);
            }

        }


        //coordinates of y
        int old_y=360;
        int new_y ;
        for (int j=0;j<priceData.size();j++)
        {

                double rate=100*(priceData.get(j)-initPrice)/initPrice;


            //System.out.println(" rate " + rate);


             new_y=(int)(-(rate)*30+360);



            //System.out.println(" new y " + new_y);


            if (j>=1)
                g2d.drawLine(50+10*j,  old_y, 50 +10*(1+j), new_y);

            old_y=new_y;
                //System.out.println(" cuurentaaaaaa  " + priceData.get(j));

        }




    }




}
