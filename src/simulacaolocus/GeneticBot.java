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
    private static Gene[] genes;
    private static Configuration conf;
    private static Genotype population;
    private static Chromosome default_chromo;

    public FitnessFunction fitnessFunc;


    private GeneticBot(int sizeCloud) throws InvalidConfigurationException, UnsupportedRepresentationException {
        conf = new DefaultConfiguration();
        conf.setPopulationSize(sizeCloud);
        genes = new Gene[7];
        start();
    }

    public static synchronized GeneticBot getInstance() throws InvalidConfigurationException, UnsupportedRepresentationException {
        if (geneticBot == null){
            geneticBot = new GeneticBot(500);
        }
        return geneticBot;
    }


    public static void start() throws InvalidConfigurationException, UnsupportedRepresentationException {
        default_chromo  = new Chromosome(conf,genes);
        conf.setSampleChromosome(default_chromo);

        FitnessFunction myFunc = new TranslateMobFitnessFunction(default_chromo);

        conf.setFitnessFunction(myFunc);

        generateCromossome();

    }

//    Pegar uma Sequencia****************************
    public int[] getBot(){
        IChromosome bestSolutionSoFar = population.getFittestChromosome();
        int[] gen = getGenetic(bestSolutionSoFar);
        return gen;
    }

    public int[] getBot(int[] genetic) throws InvalidConfigurationException{

        IChromosome bestSolutionSoFar = population.getFittestChromosome();
        genetic = getGenetic(bestSolutionSoFar);
        return genetic;
    }
//    Pegar uma Sequencia****************************

//      Gera o Cromossomo com base no tamanho do Array de Genes definido***********************

    public static void generateCromossome() throws InvalidConfigurationException, UnsupportedRepresentationException {
        int size = genes.length - 1;
        for(int i =0;i< size;i++){
            if(i%2 == 0){
                genes[i] = new IntegerGene(conf,0,1);
            }else{
                genes[i] = new IntegerGene(conf,0,9);
            }
        }
        genes[size] = new IntegerGene(conf,0,9);
        genes[size].setValueFromPersistentRepresentation("0");


    }
//  Gera o Cromossomo com base no tamanho do Array de Genes definido***********************

//  Pega sequencia com base em um individuo passado
    public int[] getGenetic(IChromosome cromossome){
        Gene[] genes = cromossome.getGenes();
        int size = genes.length;
        int[] genetic = new int[size];

        for(int i = 0; i <size - 1; i++ ){
            genetic[i] = TranslateMobFitnessFunction.getValueAtGene(
                cromossome, 3);
        }

        return genetic;
    }
}
