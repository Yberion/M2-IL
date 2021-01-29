/*
 * MIT License
 *
 * Copyright (c) 2020 - 2021 Brandon Largeau, David Lafia-Monwoo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.brandon.aoc.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.brandon.aoc.tp.algorithme_diffusion.api.AlgorithmeDiffusion;
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionAtomique;
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionCausal;
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionSequentielle;
import fr.brandon.aoc.tp.canal.api.ObserverDeCapteurAsync;
import fr.brandon.aoc.tp.canal.impl.CanalImpl;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import fr.brandon.aoc.tp.capteur.impl.CapteurImpl;
import fr.brandon.aoc.tp.observer_de_capteur.api.ObserverDeCapteur;
import fr.brandon.aoc.tp.observer_de_capteur.impl.Afficheur;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

class OracleTest
{
    private ObserverDeCapteur afficheur1;
    private ObserverDeCapteur afficheur2;
    private ObserverDeCapteur afficheur3;
    private ObserverDeCapteur afficheur4;

    @BeforeEach
    public void generateAfficheur()
    {
        this.afficheur1 = new Afficheur();
        this.afficheur2 = new Afficheur();
        this.afficheur3 = new Afficheur();
        this.afficheur4 = new Afficheur();
    }

    @Test
    @DisplayName("Test oracle algorithme atomique")
    void Oracle_Algorithme_Atomique() throws InterruptedException
    {
        Logger.info("START - Algorithme atomique");
        AlgorithmeDiffusion algorithmeDiffusion = new DiffusionAtomique();
        Capteur capteur = CapteurImpl.create(algorithmeDiffusion);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);
        ObserverDeCapteurAsync canal1 = CanalImpl.create(scheduledExecutorService, this.afficheur1);
        ObserverDeCapteurAsync canal2 = CanalImpl.create(scheduledExecutorService, this.afficheur2);
        ObserverDeCapteurAsync canal3 = CanalImpl.create(scheduledExecutorService, this.afficheur3);
        ObserverDeCapteurAsync canal4 = CanalImpl.create(scheduledExecutorService, this.afficheur4);
        capteur.attach(canal1);
        capteur.attach(canal2);
        capteur.attach(canal3);
        capteur.attach(canal4);
        algorithmeDiffusion.configure(capteur);
        int tickNumber = 7;

        for (int i = 0; i < tickNumber; ++i)
        {
            capteur.tick();
        }
        scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        int minimumDataCount = minimumDataCountAfficheurs(
                List.of(this.afficheur1, this.afficheur2, this.afficheur3, this.afficheur4));
        List<Integer> dataAfficheur1 = this.afficheur1.getData();
        List<Integer> dataAfficheur2 = this.afficheur1.getData();
        List<Integer> dataAfficheur3 = this.afficheur1.getData();
        List<Integer> dataAfficheur4 = this.afficheur1.getData();
        // Au cas ou le capteur ne commence pas a 0
        int firstValue = dataAfficheur1.get(0);

        for (int i = 0; i < minimumDataCount; ++i)
        {
            assertEquals(dataAfficheur1.get(i), firstValue + i);
            assertEquals(dataAfficheur2.get(i), firstValue + i);
            assertEquals(dataAfficheur3.get(i), firstValue + i);
            assertEquals(dataAfficheur4.get(i), firstValue + i);
        }
        displayAfficheursDatas(List.of(dataAfficheur1, dataAfficheur2, dataAfficheur3, dataAfficheur4),
                minimumDataCount);
        Logger.info("END - Algorithme atomique");
    }

    @Test
    @DisplayName("Test oracle algorithme sequentielle")
    void Oracle_Algorithme_Sequentielle() throws InterruptedException
    {
        Logger.info("START - Algorithme sequentielle");
        AlgorithmeDiffusion algorithmeDiffusion = new DiffusionSequentielle();
        Capteur capteur = CapteurImpl.create(algorithmeDiffusion);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);
        ObserverDeCapteurAsync canal1 = CanalImpl.create(scheduledExecutorService, this.afficheur1);
        ObserverDeCapteurAsync canal2 = CanalImpl.create(scheduledExecutorService, this.afficheur2);
        ObserverDeCapteurAsync canal3 = CanalImpl.create(scheduledExecutorService, this.afficheur3);
        ObserverDeCapteurAsync canal4 = CanalImpl.create(scheduledExecutorService, this.afficheur4);
        capteur.attach(canal1);
        capteur.attach(canal2);
        capteur.attach(canal3);
        capteur.attach(canal4);
        algorithmeDiffusion.configure(capteur);
        int tickNumber = 40;

        for (int i = 0; i < tickNumber; ++i)
        {
            capteur.tick();
            Thread.sleep(500);
        }
        scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        int minimumDataCount = minimumDataCountAfficheurs(
                List.of(this.afficheur1, this.afficheur2, this.afficheur3, this.afficheur4));
        List<Integer> dataAfficheur1 = this.afficheur1.getData();
        List<Integer> dataAfficheur2 = this.afficheur1.getData();
        List<Integer> dataAfficheur3 = this.afficheur1.getData();
        List<Integer> dataAfficheur4 = this.afficheur1.getData();

        for (int i = 0; i < minimumDataCount; ++i)
        {
            assertEquals(dataAfficheur1.get(i), dataAfficheur2.get(i));
            assertEquals(dataAfficheur2.get(i), dataAfficheur3.get(i));
            assertEquals(dataAfficheur3.get(i), dataAfficheur4.get(i));
        }
        displayAfficheursDatas(List.of(dataAfficheur1, dataAfficheur2, dataAfficheur3, dataAfficheur4),
                minimumDataCount);
        Logger.info("END - Algorithme sequentielle");
    }

    @Test
    @DisplayName("Test oracle algorithme causal")
    void Oracle_Algorithme_Causal() throws InterruptedException
    {
        Logger.info("START - Algorithme causal");
        AlgorithmeDiffusion algorithmeDiffusion = new DiffusionCausal();
        Capteur capteur = CapteurImpl.create(algorithmeDiffusion);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);
        ObserverDeCapteurAsync canal1 = CanalImpl.create(scheduledExecutorService, this.afficheur1);
        ObserverDeCapteurAsync canal2 = CanalImpl.create(scheduledExecutorService, this.afficheur2);
        ObserverDeCapteurAsync canal3 = CanalImpl.create(scheduledExecutorService, this.afficheur3);
        ObserverDeCapteurAsync canal4 = CanalImpl.create(scheduledExecutorService, this.afficheur4);
        capteur.attach(canal1);
        capteur.attach(canal2);
        capteur.attach(canal3);
        capteur.attach(canal4);
        algorithmeDiffusion.configure(capteur);
        int tickNumber = 15;

        for (int i = 0; i < tickNumber; ++i)
        {
            capteur.tick();
            Thread.sleep(1300);
        }
        scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        List<ObserverDeCapteur> afficheurs = List.of(this.afficheur1, this.afficheur2, this.afficheur3,
                this.afficheur4);
        int minimumDataCount = minimumDataCountAfficheurs(afficheurs);

        for (ObserverDeCapteur afficheur : afficheurs)
        {

            for (int i = 0; i < minimumDataCount - 1; ++i)
            {
                assertTrue(afficheur.getData().get(i) < afficheur.getData().get(i + 1));
            }
        }
        displayAfficheursDatas(List.of(this.afficheur1.getData(), this.afficheur2.getData(), this.afficheur3.getData(),
                this.afficheur4.getData()), minimumDataCount);
        Logger.info("END - Algorithme causal");
    }

    private int minimumDataCountAfficheurs(List<ObserverDeCapteur> afficheurs)
    {
        int minimumCount = afficheurs.get(0).getData().size();

        for (int i = 1; i < afficheurs.size(); ++i)
        {
            int afficheurDataSize = afficheurs.get(i).getData().size();

            if (afficheurDataSize < minimumCount)
            {
                minimumCount = afficheurDataSize;
            }
        }
        return minimumCount;
    }

    private void displayAfficheursDatas(List<List<Integer>> afficheurDatas, int dataCount)
    {

        for (int i = 0; i < afficheurDatas.size(); ++i)
        {
            StringBuilder stringBuild = new StringBuilder();
            Logger.info("Afficheur {} -------------", i + 1);

            for (int j = 0; j < dataCount; ++j)
            {
                stringBuild.append(afficheurDatas.get(i).get(j));
                stringBuild.append(" ");
            }
            Logger.info(stringBuild.toString());
            Logger.info("");
        }
    }
}
