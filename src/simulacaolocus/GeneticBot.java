/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


/**
 *
 * @author EEEC
 */
public class GeneticBot {

    private static GeneticBot geneticBot;

    private Gene[] genes;
    public FitnessFunction fitnessFunc;
    public Configuration conf;
    public Chromosome default_chromo;
    


    private GeneticBot(int sizeCloud) throws InvalidConfigurationException{
        this.conf = new DefaultConfiguration();
        this.conf.setPopulationSize(sizeCloud);
        this.genes = new Gene[7];
    }

    public static synchronized GeneticBot getInstance() throws InvalidConfigurationException {
        if (geneticBot == null){
            geneticBot = new GeneticBot(500);
        }
        return geneticBot;
    }
    
    public int[] getBot(int[] genetic) throws InvalidConfigurationException{
        generateGenes();      
        
        this.default_chromo  = new Chromosome(this.conf,this.genes);
        this.conf.setSampleChromosome(default_chromo);
        
        FitnessFunction myFunc = new TranslateMobFitnessFunction(this.default_chromo );

        conf.setFitnessFunction( myFunc );

        Genotype population = Genotype.randomInitialGenotype(conf);

        population.evolve();

        IChromosome bestSolutionSoFar = population.getFittestChromosome();

        int value = TranslateMobFitnessFunction.getValueAtGene(
                bestSolutionSoFar, 3);
        System.out.println(value);


        genetic = getGenetic(bestSolutionSoFar);

        return genetic;
    }
    
   
    
    public void generateGenes() throws InvalidConfigurationException{
        int size = this.genes.length - 1;
        for(int i =0;i< size;i++){
            if(i%2 == 0){
                this.genes[i] = new IntegerGene(this.conf,0,1);
            }else{
                this.genes[i] = new IntegerGene(this.conf,0,9);
            }
        }
        this.genes[size] = new IntegerGene(this.conf,0,9);

        System.out.println(this.genes[size].getAllele());
        System.out.println(this.genes[size].getEnergy());
        System.out.println(this.genes[size].getPersistentRepresentation());
    }

    public int[] getGenetic(IChromosome cromossome){
        Gene[] genes = cromossome.getGenes();
        int size = genes.length;
        int[] genetic = new int[size];

        for(int i = 0; i <size - 1; i++ ){
            genetic[i] = TranslateMobFitnessFunction.getValueAtGene(
                cromossome, 3);
        }
//        int value =
//
//
        return genetic;
    }
}
