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

    private GeneticBot geneticBot;
    private Gene[] genes;
    private Configuration conf;
    private Genotype population;
    private Chromosome default_chromo;

    public FitnessFunction fitnessFunc;

//  Passar para construtor o tamanho da nuvem de cromossomos e o tamanho do cromossomo
    public GeneticBot(int sizeCloud) throws InvalidConfigurationException, UnsupportedRepresentationException {
        this.conf = new DefaultConfiguration();
        this.conf.setPopulationSize(sizeCloud);
        this.genes = new Gene[7];
        start();
    }

    public GeneticBot(int sizeCloud,int sizeCromossome) throws InvalidConfigurationException, UnsupportedRepresentationException {
        this.conf = new DefaultConfiguration();
        this.conf.setPopulationSize(sizeCloud);
        this.genes = new Gene[sizeCromossome];
        start();
    }


//  Starta as condicoes para que o codigo funcione
    public void start() throws InvalidConfigurationException, UnsupportedRepresentationException {
        generateCromossome();
        this.default_chromo  = new Chromosome(this.conf,this.genes);
        this.conf.setSampleChromosome(default_chromo);

        this.fitnessFunc = new TranslateMobFitnessFunction(default_chromo);

        this.conf.setFitnessFunction(fitnessFunc);



    }

//  Pegar uma Sequencia****************************
    public int[] getBot() throws InvalidConfigurationException {
        this.population = Genotype.randomInitialGenotype(this.conf);
        IChromosome bestSolutionSoFar = this.population.getFittestChromosome();
        int[] gen = getGenetic(bestSolutionSoFar);
        Gene[] test =  bestSolutionSoFar.getGenes();
        Object allele = test[0].getAllele();
        return gen;
    }

//  Insere um Bot e joga na Nuvem para depois pegar um novo bot
//  A implementar ainda

    public int[] getBot(int[] genetic) throws InvalidConfigurationException{
        System.out.println("entrei aqui");
        IChromosome bestSolutionSoFar = population.getFittestChromosome();
        genetic = getGenetic(bestSolutionSoFar);
        return genetic;
    }


//  Gera o Cromossomo com base no tamanho do Array de Genes definido
    public  void generateCromossome() throws InvalidConfigurationException, UnsupportedRepresentationException {
        int size = this.genes.length - 1;
        for(int i =0;i< size;i++){
            if(i%2 == 0){
                this.genes[i] = new IntegerGene(this.conf,0,1);
            }else{
                this.genes[i] = new IntegerGene(this.conf,0,9);
            }
        }
        genes[size] = new IntegerGene(conf,0,9);
        genes[size].setAllele(0);

    }

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
