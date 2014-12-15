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
        int[] arry = {1,3,1,3,1,2,0};
        GeneticBot geneticBot = new GeneticBot(500);
        geneticBot.getBot();
    }
    
}
