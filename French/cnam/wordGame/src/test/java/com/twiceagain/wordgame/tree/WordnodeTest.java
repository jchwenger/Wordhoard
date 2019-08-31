/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twiceagain.wordgame.tree;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author xavier
 */
public class WordnodeTest {


    static final Wordnode DICO = new Wordnode();

    public WordnodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException, URISyntaxException {
        DICO.load();

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //@Test
    public void display1() {
        Wordnode wt = new Wordnode();
        wt.add('a');
        Wordnode a = wt.add('b').add('c');
        a.add('d');
        a.add('e');
        System.out.println(wt.toString());
    }

    //@Test
    public void display2() {
        Wordnode w = new Wordnode();
        w.add("Hello");
        w.add("World");
        w.add("Hell");
        w.add("Hola");
        System.out.println(w.toString());
    }

    //@Test
    public void loadAndDisplay() throws IOException {
        System.out.println(DICO.toString());
    }

    @Test
    public void find() {
//        System.out.printf("Finding 'bla' : %s\n", DICO.find("bla"));
//        System.out.printf("Finding 'xa' : %s\n", DICO.find("xa"));
//        System.out.printf("Finding 'xavier' : %s\n", DICO.find("xavier"));
//        System.out.printf("Finding 'wu' : %s\n", DICO.find("wu"));
//        System.out.printf("Finding 'wuk' : %s\n", DICO.find("wuk"));
//        System.out.printf("Finding 'wurmien' : %s\n", DICO.find("wurmien"));

        assertNotNull(DICO.find(""));
        assertNotNull(DICO.find(null));

        assertNotNull(DICO.find("wu"));
        assertNotNull(DICO.find("wurmien"));

        assertNull(DICO.find("wurmienz"));
        assertNull(DICO.find("xavier"));

    }

}
