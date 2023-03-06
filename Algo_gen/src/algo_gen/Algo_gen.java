/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algo_gen;
import java.util.Random;
import javax.swing.JFrame;
/**
 *
 * @author Gustavo
 */
public class Algo_gen {
    public static int individuos = 50;
    public static int xizis = 50;
    public static JFrame frame = new JFrame(); 
            
    public static float[] decod(gen_pop[] popi){
        float[] z = new float[individuos];
        for(int i = 0; i < individuos; i++){
            z[i] = popi[i].getZ();
        }
        return z;
    }

    public static float[] apt(float[] pops){
        float aux = 0;
        for(int i = 0; i < individuos; i++){
            aux += pops[i];
        }
        float[] aux2 = new float[individuos];
        for(int i = 0; i < individuos; i++){
            aux2[i] = pops[i]/aux;
        }
        return aux2; 
    }
    
    public static sam crzmt(float[] indZ, gen_pop[] pop){
        Random rand = new Random();
        gen_pop pai1=new gen_pop(0,0,0);
        gen_pop pai2=new gen_pop(0,0,0);
        float[] filhox1 = new float[individuos];
        int posi=0;
        float[] filhoy1 = new float[individuos];
        int posp1 = 0,   posp2 = 0;
        
        
        for(int i = 0; i < individuos/2; i++){
            float sorteio = rand.nextFloat(0, 1);
            float j = 0;
            int aux = 0;
            float cruz = rand.nextFloat(0, 1);
            
            for(int k = 0; k <= individuos - 1; k++){
                if(k < individuos - 1){
                    if(sorteio <= indZ[k]+j){
                        pai1.setX(pop[k].getX());
                        pai1.setY(pop[k].getY());
                        break;
                    }
                    j += indZ[k+1];
                }else if(k >= individuos - 1){ 
                    pai1.setX(pop[individuos - 1].getX());
                    pai1.setY(pop[individuos - 1].getY());
                    break;
                }
                aux = k;
                posp1 = k;
            }
            pai2.setX(pai1.getX());
            pai2.setY(pai1.getY());

            for(;aux != -1;){
                sorteio = rand.nextFloat(0, 1);
                j = 0;
                for(int k = 0; k <= individuos - 1; k++){
                    if(k < individuos - 1){
                        if(sorteio <= indZ[k]+j && aux != k){
                            pai2.setX(pop[k].getX());
                            pai2.setY(pop[k].getY());
                            aux = -1;
                            break;
                        }
                        j += indZ[k+1];
                    }else if(k >= individuos - 1 && aux != k){
                        pai2.setX(pop[individuos - 1].getX());
                        pai2.setY(pop[individuos - 1].getY());
                        aux = -1;
                        break;
                    }else aux = 0;
                    posp2 = k;
                }
            }
            if(cruz <= 0.8){
                float beta = rand.nextFloat(0, 1);

                filhox1[posi] = beta*pai1.getX()+(1-beta)*pai2.getX();
                beta = rand.nextFloat(0, 1);
                filhoy1[posi] = beta*pai1.getY()+(1-beta)*pai2.getY();
                posi++;
                
                beta = rand.nextFloat(0, 1);
                filhoy1[posi] = beta*pai1.getY()+(1-beta)*pai2.getY();
                beta = rand.nextFloat(0, 1);
                filhox1[posi] = beta*pai2.getX()+(1-beta)*pai1.getX();
                posi++;
            }else{
                filhox1[posi] = pop[posp1].getX();
                filhoy1[posi] = pop[posp1].getY();
                posi++;
                filhox1[posi] = pop[posp2].getX();
                filhoy1[posi] = pop[posp2].getY();
                posi++;
            }
        }
        sam result = new sam(filhox1, filhoy1);
        return result;
    }
    
    public static sam mut(sam filho){
        Random rand = new Random();
        float test;
        int x = 30;
        

        for(int k = 0; k < individuos; k++){
            float muta = rand.nextFloat(0, 1);
            if(muta <= 0.05){
                test = (rand.nextFloat(-x, x))/10;
                if((test + filho.getFilhoxizin(k)) > 10){
                    filho.setFilhoxizin(10, k);
                }else if((test + filho.getFilhoxizin(k)) < -10){
                    filho.setFilhoxizin(-10, k);
                }else{
                    filho.setFilhoxizin(filho.getFilhoxizin(k)+test, k);
                }
                test = (rand.nextFloat(-x, x))/10;
                if((test + filho.getFilhoyizin(k)) > 10){
                    filho.setFilhoyizin(10, k);
                }else if((test + filho.getFilhoyizin(k)) < -10){
                    filho.setFilhoyizin(-10, k);
                }else{
                    filho.setFilhoyizin(filho.getFilhoyizin(k)+test, k);
                }
            }
        }
        
        return filho;
    }
    
    public static void main(String[] args) {
        gen_pop[] my_pop;
        gen_pop pop2;
        float[] vetorZ= new float[individuos];
        my_pop = new gen_pop[individuos];
        gen_pop.criar(my_pop, individuos);
        
       
        /*
        for(int k = 0; k < individuos; k++){
            System.out.println("X = "+my_pop[k].getX()+"\nY = "+my_pop[k].getY()+"\nZ = "+my_pop[k].getZ());
        }
        */
        
        for(int i = 0; i < xizis; i++){
            Graf.cont++;
            vetorZ=decod(my_pop);
            vetorZ=apt(vetorZ);
            
            //System.out.println("Vetor z = "+vetorZ[0]);
            
            pop2 = new gen_pop(my_pop[0].getX(), my_pop[0].getY(), my_pop[0].getZ());
            
            sam filho;
            filho = crzmt(vetorZ, my_pop);

            filho = mut(filho);


            filho.samGen(my_pop);
            
            my_pop[individuos - 1].setX(pop2.getX());
            my_pop[individuos - 1].setY(pop2.getY());
            my_pop[individuos - 1].setZ(pop2.getZ());
            
            gen_pop.bubbleSort(my_pop);
            
            
            Graf.main(my_pop, i);
        }  
        
        /*
        System.out.println("Filhos1 sem ordenar");
        System.out.println(filho.getFilhoxizin(0));
        System.out.println(filho.getFilhoxizin(25));  
        System.out.println(filho.getFilhoxizin(49));
        System.out.println("");
        
        
        System.out.println("Pais ordenados");
        System.out.println(vetorZ[0]);
        System.out.println(vetorZ[49]);
        System.out.println("");        
        
        System.out.println("Pais ordenados dentro da classe");
        System.out.println(my_pop[0].getZ());
        System.out.println(my_pop[49].getZ());
        System.out.println("");
        
        System.out.println("Valores dos pais x");
        System.out.println(my_pop[0].getX());
        System.out.println(my_pop[49].getX());
        System.out.println("");
        
        System.out.println("Valores dos pais y");
        System.out.println(my_pop[0].getY());
        System.out.println(my_pop[49].getY());
        System.out.println("");
        
        filho.samGen(my_pop);
        
        System.out.println("Pós suruba");
        
        System.out.println("Pais ordenados dentro da classe");
        System.out.println(my_pop[0].getZ());
        System.out.println(my_pop[49].getZ());
        System.out.println("");
        
        System.out.println("Valores dos pais x");
        System.out.println(my_pop[0].getX());
        System.out.println(my_pop[49].getX());
        System.out.println("");
        
        System.out.println("Valores dos pais y");
        System.out.println(my_pop[0].getY());
        System.out.println(my_pop[49].getY());
        System.out.println("");
        
        filho = mut(filho);
        System.out.println("Filhos1 pos multacao");
        System.out.println(filho.getFilhoxizin(0));
        System.out.println(filho.getFilhoxizin(49));
        System.out.println("");
        */
    }
}
