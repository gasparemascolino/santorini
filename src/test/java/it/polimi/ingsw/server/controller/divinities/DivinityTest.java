package it.polimi.ingsw.server.controller.divinities;

import it.polimi.ingsw.server.controller.Divinity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DivinityTest {

    Divinity apollo, artemis, athena, atlas, chronus, demeter, hephaestus, hestia, minotaur, pan, poseidon, prometheus, triton, zeus;

    @Before
    public void setUp() {

       apollo = new Apollo();
       artemis = new Artemis();
       athena = new Athena();
       atlas = new Atlas();
       chronus = new Chronus();
       demeter = new Demeter();
       hephaestus = new Hephaestus();
       hestia = new Hestia();
       minotaur = new Minotaur();
       pan = new Pan();
       poseidon = new Poseidon();
       prometheus = new Prometheus();
       triton = new Triton();
       zeus = new Zeus();

    }

    @After
    public void tearDown()
    {}

    @Test
    public void DivinitiesTest() {
        System.out.println(apollo.toString()+": "+apollo.Description());
        System.out.println(artemis.toString()+": "+artemis.Description());
        System.out.println(athena.toString()+": "+athena.Description());
        System.out.println(atlas.toString()+": "+atlas.Description());
        System.out.println(chronus.toString()+": "+chronus.Description());
        System.out.println(demeter.toString()+": "+demeter.Description());
        System.out.println(hephaestus.toString()+": "+hephaestus.Description());
        System.out.println(hestia.toString()+": "+hestia.Description());
        System.out.println(minotaur.toString()+": "+minotaur.Description());
        System.out.println(pan.toString()+": "+pan.Description());
        System.out.println(poseidon.toString()+": "+poseidon.Description());
        System.out.println(prometheus.toString()+": "+prometheus.Description());
        System.out.println(triton.toString()+": "+triton.Description());
        System.out.println(zeus.toString()+": "+zeus.Description());


    }
}
