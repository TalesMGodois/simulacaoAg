/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


/**
 *
 * @author EEEC
 */
public class GeneticBot {
    private Gene[] genes;
    public FitnessFunction fitnessFunc;
    public Configuration conf;
    public Chromosome default_chromo;
    
    
    public GeneticBot(int sizeCloud){
        this.conf = new DefaultConfiguration();
        
    }
    
    public int getBot(int genetic) throws InvalidConfigurationException{
        generateGenes();
        
        
        this.default_chromo  = new Chromosome(this.conf,this.genes);
        this.conf.setSampleChromosome(default_chromo);
        
        this.conf.setPopulationSize(this.genes.length);
        
        
        FitnessFunction myFunc = new TranslateMobFitnessFunction(this.default_chromo );

        conf.setFitnessFunction( myFunc );
        return genetic;
    }
    
   
    
    public void generateGenes() throws InvalidConfigurationException{
        int size = this.genes.length -1;
        if (this.genes.length == 0){
            for(int i =0;i< size -1;i = i+2){
                if(i%2 == 0){
                    this.genes[i] = new IntegerGene(this.conf,0,1);  
                }else{
                    this.genes[i] = new IntegerGene(this.conf,0,9);
                }
            }
            this.genes[size] = new IntegerGene(this.conf,0,9);
        }
    }

}
