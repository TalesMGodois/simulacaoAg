/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaolocus;

import simulacaolocus.model.selection.Roleta;
import simulacaolocus.model.selection.Aleatory;
import simulacaolocus.model.selection.Elitism;
import simulacaolocus.signature.Selection;

/**
 *
 * @author EEEC
 */
public class Selectionfactory {
    
    public Selection getSelection(int[] gene,String method){
        if(method.equals("roullete"))
            return new Roleta();           
        else if(method.equals("aleatory"))
            return new Elitism();
        else if(method.equals("elitism"))
            return new Aleatory();
        else
            return new Roleta();
    }
}
