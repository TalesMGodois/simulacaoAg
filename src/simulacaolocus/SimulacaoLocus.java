/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.InvalidConfigurationException;
import org.jgap.UnsupportedRepresentationException;

/**
 *
 * @author EEEC
 */
public class SimulacaoLocus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidConfigurationException, UnsupportedRepresentationException {
        int array[];
        GeneticBot geneticBot = new GeneticBot(500);
        int[] bot = geneticBot.getBot();
        printBot(bot);
        bot[bot.length -1]++;

        for(int j =0; j<50; j++){
            printBot(bot);
            if(j%2 ==0){
                bot = geneticBot.getBot(bot);
                bot[bot.length -1]++;
            }else{
                bot = geneticBot.getBot(bot);
                bot[bot.length -1]--;
            }

        }

    }


    public static void printBot(int[] bot){
        for(int i =0;i< bot.length; i++){
            System.out.print(bot[i]);
        }
        System.out.print("\n");
    }
}
