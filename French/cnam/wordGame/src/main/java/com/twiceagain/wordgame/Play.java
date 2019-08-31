package com.twiceagain.wordgame;


import com.twiceagain.wordgame.tree.Game;
import java.io.IOException;
import java.net.URISyntaxException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xavier
 */
public class Play {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.printf("\nQuart de Singe - (c) Xavier Gandillot 2018\n");
        Game game = new Game(2, 1);   
        System.out.printf("-->%s< \n", game.play(null));
        while (true) {

            int i = System.in.read();
            System.out.printf("-->%s<\t(%s)\n", game.play((char) i), game.example());
        }

    }

}
