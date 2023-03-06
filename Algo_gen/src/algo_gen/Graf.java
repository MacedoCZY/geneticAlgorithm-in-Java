/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algo_gen;

import java.awt.*;  
import javax.swing.*;  
import java.awt.geom.*;  
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
  
//Extends JPanel class  
public class Graf extends JPanel{  
    //initialize coordinates  
    int[] cord = {65, 20, 40, 80};  
    int marg ;
    gen_pop[] pops;
    public static int cont = 0;
    
    private Graf(gen_pop[] pup, int ki){
        this.pops = pup;
        this.marg = ki;
    }
    
    protected void paintComponent(Graphics grf){  
        //create instance of the Graphics to use its methods  
        super.paintComponent(grf);  
        Graphics2D graph = (Graphics2D)grf;  
          
        //Sets the value of a single preference for the rendering algorithms.  
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
          
        // get width and height  
        int width = getWidth();  
        int height = getHeight();  
          
        // draw graph  
        //graph.draw(new Line2D.Double(marg, marg, marg, height-marg));  
        //graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));         
        graph.drawLine(0, height/2, width, height/2);  
        graph.drawLine(width/2, 0, width/2, height);  
        
        
        //find value of x and scale to plot points  
        double scaley = (double)(height/20);  
        double scalex = (double)(width/20);
        
        int zulu = 1;
        int sinp = -10;
        
        for(int k = 1; k < 600; k+=(width/20)){
            graph.drawLine(k, (height/2)+zulu, k, (height/2)-zulu);
            if(sinp != 0){
                graph.drawString(String.valueOf(sinp), (float)k-1, (float)(height/2)+15);
            }
            sinp++;
        }
        sinp = 10;
        for(int k = 1; k < 600; k+=(height/20)){
            graph.drawLine((width/2)+zulu, k, (width/2)-zulu, k);
            if(sinp != 0){
                graph.drawString(String.valueOf(sinp), (float)(width/2)-15, (float)k+1);
            }
            sinp--;
        }
        
        //set color for points  
        
        Random rand= new Random();
        
        //graph.setPaint(Color.MAGENTA);  
        // set points to the graph  
        for(int i=0; i< Algo_gen.individuos; i++){  
            double x1 = (width/2)+pops[i].getX()*scalex;  
            double y1 = (height/2)-pops[i].getY()*scaley;  
            graph.setPaint(new Color(rand.nextInt(0, 180), rand.nextInt(0, 180), rand.nextInt(0, 180)));
            graph.fill(new Ellipse2D.Double(x1, y1, 8, 8));
            
        }  
        graph.setPaint(new Color(0,0,0));
        graph.drawString("Geração : "+String.valueOf(cont), 10, 15);
        graph.drawString("Melhor X : "+String.valueOf(pops[0].getX()), 10, 30);
        graph.drawString("Melhor Y : "+String.valueOf(pops[0].getY()), 10, 45);
    }  
      
    //create getMax() method to find maximum value  
    private int getMax(){  
        int max = -Integer.MAX_VALUE;  
        for(int i=0; i<cord.length; i++){  
            if(cord[i]>max)  
                max = cord[i];  
             
        }  
        return max;  
    }         
      
    //main() method start  
    public static void main(gen_pop[] puping, int ki){  
        //create an instance of JFrame class   
        // set size, layout and location for frame.  
        Algo_gen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Algo_gen.frame.add(new Graf(puping,ki));  
        Algo_gen.frame.setSize(600, 600);  
        Algo_gen.frame.setLocation(200, 0);  
        Algo_gen.frame.setVisible(true); 
        
        if(ki < Algo_gen.xizis-1){
            try {
                //System.out.println("Z = "+my_pop[1].getZ()+"\n");
                //System.out.println("Z = "+my_pop[0].getZ()+"\n");

                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(Algo_gen.class.getName()).log(Level.SEVERE, null, ex);
            }
            //frame.setVisible(false);
        }
        if(ki > 1){
            java.awt.EventQueue.invokeLater(new Runnable(){
                public void run(){
                    Algo_gen.frame.repaint();
        }
        });
        }
    }  
}  