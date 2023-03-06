/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algo_gen;

import java.util.Random;

/**
 *
 * @author Gustavo
 */

public class gen_pop {

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public gen_pop(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    private float x;
    private float y;
    private float z;
        
    public static void criar(gen_pop[] prox, int quantos)
    {
        for(int i = 0; i < quantos; i++)
        {   
            Random gerador = new Random();
            prox[i]= new gen_pop(gerador.nextFloat(-10,10),gerador.nextFloat(-10,10),0);
        }
        for(int i = 0; i < quantos; i++)
        {   
            prox[i].setZ((-((prox[i].getX()*prox[i].getX())+(prox[i].getY()*prox[i].getY()))+4)+197);
        }
        prox = bubbleSort(prox);
    }
    
    public static gen_pop[] bubbleSort(gen_pop[] kekw) {
        int n = kekw.length;
        gen_pop temp;
        
        for(int i = 0; i < n; i++){
            for(int j = 1; j < (n-i); j++){
                if(kekw[j-1].getZ() < kekw[j].getZ()){
                    temp = kekw[j-1];
                    kekw[j-1] = kekw[j];
                    kekw[j] = temp;
                }
            }
        }
        return kekw;
    }
} 
