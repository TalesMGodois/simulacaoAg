/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

/**
 *
 * @author EEEC
 */
public class TranslateMobFitnessFunction  extends FitnessFunction{
    private Chromosome cromossome;
    
    public TranslateMobFitnessFunction(Chromosome cromossome){
        
        this.cromossome = cromossome;
    }
    
    @Override
    protected double evaluate(IChromosome ic) {
        Gene[] genes = this.cromossome.getGenes();
        double fitness = 0;
        
        int size = genes.length - 1;
        double sum = 0;
            
        for(int i = 0; i < size ; i = i+2){
            sum = genes[i].getEnergy()*genes[i+1].getEnergy()+ sum;
        }   
        
        try{
             fitness = genes[size].getEnergy()* sum; 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return fitness;
        }
        
    }

    public static int getValueAtGene( IChromosome a_potentialSolution,
                                              int a_position )
    {
        Integer value =
                (Integer) a_potentialSolution.getGene(a_position).getAllele();

        return value.intValue();
    }


}
