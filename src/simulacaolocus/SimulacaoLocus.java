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
//        printBot(bot);
        for(int j =0; j<50; j++){
            bot[bot.length -1] = bot[bot.length -1] + victoryOrDerrote();
            bot = geneticBot.getBot(bot);
            printBot(bot);

        }
    }


    public static void printBot(int[] bot){
        for(int i =0;i< bot.length; i++){
            System.out.print(bot[i]);
        }
        System.out.print("\n");
    }

    public static int victoryOrDerrote(){
        double d = Math.random();

        if (d <= 0.5){
            return 0;
        }else{
            return 1;
        }

    }
}
