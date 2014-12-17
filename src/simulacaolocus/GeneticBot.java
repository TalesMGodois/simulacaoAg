/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.*;
import org.jgap.data.IDataCreators;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.WeightedRouletteSelector;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author EEEC
 */
public class GeneticBot {

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
        resetAlelle();
        IChromosome bestSolutionSoFar = this.population.getFittestChromosome();
        return getGenetic(bestSolutionSoFar);

    }

//  Insere um Bot e joga na Nuvem para depois pegar um novo bot
//  A implementar ainda

    public int[] getBot(int[] genetic) throws InvalidConfigurationException{
        Gene[] genes = setGenes(genetic);
        IChromosome cromossome = this.population.getFittestChromosome();

        cromossome.setGenes(genes);
        this.population.getPopulation().addChromosome(cromossome);
        this.population.evolve();
        cromossome = this.population.getFittestChromosome();
        System.out.print(cromossome.getFitnessValue());
        System.out.print(" ");
        SimulacaoLocus.printBot(genetic);
        System.out.println("\n");
        genetic = getGenetic(cromossome);
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
        int [] genetic = new int[size];

        for(int i = 0; i < size ; i++ ){
            genetic[i] = (Integer)genes[i].getAllele();
        }

        return genetic;
    }

    protected Gene[] setGenes(int[] array){
        Gene[] genes = this.genes;
        for(int i=0;i< array.length;i++){
            genes[i].setAllele(array[i]);
        }
        return genes;
    }

    protected  void resetAlelle() throws InvalidConfigurationException {
        Population population = this.population.getPopulation();
        List<IChromosome> cromossomes = population.getChromosomes();
        Gene[] genes;
        int size =0;
        System.out.println(cromossomes.size());
        for(int i = 0;i < cromossomes.size(); i++){
            genes = cromossomes.get(i).getGenes();
            size = genes.length -1;
            genes[size].setAllele(0);
            cromossomes.get(i).setGenes(genes);
        }
    }
}
