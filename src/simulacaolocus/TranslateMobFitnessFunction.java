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

import java.util.List;

/**
 *
 * @author EEEC
 */
public class TranslateMobFitnessFunction  extends FitnessFunction{
    private Chromosome cromossome;
    private int maxSumOfElements = 5;
    private int maxWeight = 30;
    private int[] weights = {3,5,8};


    public TranslateMobFitnessFunction(Chromosome cromossome){
        
        this.cromossome = cromossome;
    }
    
    @Override
    protected double evaluate(IChromosome ic) {
        Gene[] genes = ic.getGenes();
        double fitness = 0;
        int size = genes.length;
        double sum = 0;

        int totalofMobs =(Integer) genes[1].getAllele() + (Integer)genes[3].getAllele() +  (Integer)genes[5].getAllele();
        int n1 =(Integer) genes[0].getAllele()*(Integer)genes[1].getAllele()*weights[0];
        int n2 =(Integer) genes[2].getAllele()*(Integer)genes[3].getAllele()*weights[1];
        int n3 =(Integer) genes[4].getAllele()*(Integer)genes[5].getAllele()*weights[2];
        int weight = n1+ n2 + n3;

        if((totalofMobs < maxSumOfElements) &&  weight <= maxWeight){

            fitness =((Integer)genes[size - 1].getAllele() + 1) * weight;
        }

        return  fitness;
    }

    public static int getValueAtGene( IChromosome a_potentialSolution,
                                              int a_position )
    {
        Integer value =
                (Integer) a_potentialSolution.getGene(a_position).getAllele();

        return value.intValue();
    }


}
