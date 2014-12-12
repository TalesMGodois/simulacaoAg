/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import org.jgap.InvalidConfigurationException;

/**
 *
 * @author EEEC
 */
public class SimulacaoLocus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidConfigurationException {
        int array[];
        int[] arry = {1,3,1,3,1,2,0};
        GeneticBot.getInstance().getBot(arry);
    }
    
}
