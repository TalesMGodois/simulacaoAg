package simulacaolocus.model;

import org.jgap.Gene;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EEEC
 */
public class LocusChromossome {
    private Gene gene;
    private double fitness;
    

    
    public LocusChromossome crossover(){
        return this;
    }
    
    public void calcFit(){
        int[] array = this.genetic;
        int sum = 0;
        if(array.length%2 == 1){
            
            for(int i = 0;i < array.length - 1;i = i+2){
                sum = sum + array[i]*array[i+1];
            }   
        }
        try{
            this.fitness = array[array.length - 1]* sum; 
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
    }
    
    public double getFit(){
        return this.fitness;
    }
}
