/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algo_gen;

/**
 *
 * @author Macedo
 */
public class sam {

    public sam(float[] filhox1,float[] filhoy1) {
        this.filhox1 = filhox1;
        this.filhoy1 = filhoy1;
    }
    
    public void setFilhoTe1(float x,float y, int posi){
        this.filhox1[posi] = x;
        this.filhoy1[posi] = y;
    }
    
    public void setFilhoxizin(float x, int pos){
        this.filhox1[pos]=x; 
    }
    
    public void setFilhoyizin(float x ,int pos){
        this.filhoy1[pos]=x; 
    }
    
    public float getFilhoxizin(int pos){
        return this.filhox1[pos]; 
    }
    
    public float getFilhoyizin(int pos){
        return this.filhoy1[pos]; 
    }
    
    public float[] getFilhox1() {
        return filhox1;
    }

    public float[] getFilhoy1() {
        return filhoy1;
    }
    private float[] filhox1 = new float[Algo_gen.individuos];
    
    private float[] filhoy1 = new float[Algo_gen.individuos];
    
   public void samGen(gen_pop[] pop)
    {
        for(int i = 0; i < Algo_gen.individuos; i++){
            pop[i].setX(this.filhox1[i]);
            pop[i].setY(this.filhoy1[i]);
            pop[i].setZ((-((pop[i].getX()*pop[i].getX())+(pop[i].getY()*pop[i].getY()))+4)+197);
        }
        pop = gen_pop.bubbleSort(pop);
    }
}
