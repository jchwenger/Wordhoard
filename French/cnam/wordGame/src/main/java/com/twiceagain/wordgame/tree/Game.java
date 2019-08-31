/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.wordgame.tree;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.logging.Logger;

/**
 * Utility class to compute scores and select moves. 
 * @author xavier
 */
public class Game {

    protected int totalNumberOfPlayers = 2;
    protected int playersBeforeComputer = 0; // (0 means first)
    public static Wordnode DICO = null;
    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    private String current = "";

    /**
     *
     * @param totalNumberOfPlayers : from 2 to n
     * @param playersBeforeComputer : from 0 (computer first to play) to n-1
     * (computer last to play)
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public Game(int totalNumberOfPlayers, int playersBeforeComputer) throws IOException, URISyntaxException {
        this.totalNumberOfPlayers = totalNumberOfPlayers;
        this.playersBeforeComputer = playersBeforeComputer;
        if (DICO == null) {
            DICO = new Wordnode();
            //DICO.load(FILENAME);
            DICO.load(); // from resource
        }
    }

    public void reset() {
        current = "";
    }

    /**
     * For a given depth, does the computer decides or will it be the opponent/s
     * ?
     *
     * @param depth
     * @return
     */
    public boolean computerDecides(int depth) {
        return ((depth - playersBeforeComputer) % totalNumberOfPlayers) == 0;
    }

    public boolean computerDecides(String text) {
        if (text == null || text.isEmpty()) {
            return computerDecides(0);
        } else {
            return computerDecides(text.length());
        }
    }

    /**
     * Does the computer wins if this string is being played - by him or his
     * opponent ...
     * // TODO Does not work properly !
     * @param text
     * @return
     */
    public boolean computerWins(String text) {
        Wordnode w = DICO.find(text);
        if (w == null) {
            // Does not exist. The one who was playing loose.
            return computerDecides(text);
        }
        if (!w.hasChildren()) {
            // Terminal word. The one playing wins.
            return !computerDecides(text);
        }

        // Now, we have the case where there are multiple solutions and the node is not terminal.
        if (computerDecides(text)) {
            // Since he can decide, computer wins if at least one of the children 
            // is a computerWin position.
            for (Character c : w.children.keySet()) {
                if (computerWins(text + c)) {
                    return true;
                }
            }
            return false;
        } else {
            // Here computer cannot decides, he looses as soon as one of the 
            // children is a loosing computer position.
            for (Character c : w.children.keySet()) {
                if (!computerWins(text + c)) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * Assuming it is the computer's turn, and the last opponent played, what
     * letter should be added ?
     *
     * @param previous
     * @return null if no winning move.
     */
    public Character findWinningMove(String previous) {
        if (!computerDecides(previous)) {
            LOG.severe("Computer trying to play when not his turn, or opponents are cheating ?");
            reset();return null;
        }
        Wordnode pv = DICO.find(previous);
        if (pv == null) {
            LOG.info("You are bluffing - I don't believe you !");
            reset();return null;
        }

        if (!pv.hasChildren()) {
            LOG.info("Congratulations, I'm afraid you won ...!");
            reset();return null;
        }
        
        
        ArrayList<Character> cl = new ArrayList<>(pv.children.keySet());
        Collections.shuffle(cl);
        for (Character c : cl) {
            if (computerWins(previous + c)) {
                LOG.info("For information, I should normally WIN this game ...");        
                return c;
            }
        }

        LOG.info("For information, I should normally LOOSE this game ...");
        for (Character c : cl) {            
                return c;           
        }
        return null; // not reacheable
    }

    /**
     * Opponent plays Character. Computer plays before/after if it is its turn.
     *
     * @param c - can be null to force checking computer play
     * @return
     */
    public String play(Character c) {
        if (c != null && c >= 'a' && c <= 'z') {
            current = current + c;
        }
        if (computerDecides(current)) {
            Character cc = findWinningMove(current);
            if (cc != null) {
                current = current + cc;
            }
        }
        return current;
    }

    /**
     * Finds an example starting with current prefix. Return empty if not found.
     *
     * @return
     */
    public String example() {

        String s = "";
        Wordnode w = DICO.find(current);

        if (w == null) {
            return " -- ???";
        }
        if(!w.hasChildren()) return "***" + current + "***";

        while (w != null && w.hasChildren()) {
            for (Entry<Character, Wordnode> e : w.children.entrySet()) {
                s = s + e.getKey();
                w = e.getValue();
                break;
            }
        }
        return current + s;
    }

}
