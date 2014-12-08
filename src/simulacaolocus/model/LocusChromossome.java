package simulacaolocus.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EEEC
 */
public class LocusChromossome {
    private final int[] genetic;
    public double fitness;
    
    public LocusChromossome(int[] genetic){
        this.genetic = genetic;
    }
}
