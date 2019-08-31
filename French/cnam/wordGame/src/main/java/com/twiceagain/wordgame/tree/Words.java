/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.wordgame.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Handle exceptions to the dictionnary (added words or banned words)
 *
 * @author xavier
 */
public class Words {

    /**
     * Chcek (ban)  words read from dictionnary.
     *
     * @param w
     * @return
     */
    public static boolean valid(String w) {
        Pattern pat = Pattern.compile( "^bd|pc"
                + "|cm|dm|mm|km|hm"
                + "|dl|cl|ml|kl|kt"
                + "|kg|hg|cg|dg"
                + "|vs|cf"
                + "$");
        
        return w != null && !w.isEmpty() && ! pat.matcher(w).matches();
        
    }
    
    /**
     * Additions to the disctionnary.
     * @return 
     */
    public static Set<String> additions() {
        
        HashSet<String> s = new HashSet<>();
        s.add("quelle");
        s.add("laquelle");
        
        return s;
    }

}
