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

import fr.brandon.aoc.tp.algorithme_diffusion.api.AlgorithmeDiffusion;
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
import org.tinylog.Logger;

public final class App
{
    public static void main(String[] args) throws InterruptedException
    {
        Logger.info("DEBUT main()");
        AlgorithmeDiffusion algorithmeDiffusion = new DiffusionSequentielle();
        Capteur capteur = CapteurImpl.create(algorithmeDiffusion);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);
        ObserverDeCapteur afficheur1 = new Afficheur();
        ObserverDeCapteur afficheur2 = new Afficheur();
        ObserverDeCapteur afficheur3 = new Afficheur();
        ObserverDeCapteur afficheur4 = new Afficheur();
        ObserverDeCapteurAsync canal1 = CanalImpl.create(scheduledExecutorService, afficheur1);
        ObserverDeCapteurAsync canal2 = CanalImpl.create(scheduledExecutorService, afficheur2);
        ObserverDeCapteurAsync canal3 = CanalImpl.create(scheduledExecutorService, afficheur3);
        ObserverDeCapteurAsync canal4 = CanalImpl.create(scheduledExecutorService, afficheur4);
        capteur.attach(canal1);
        capteur.attach(canal2);
        capteur.attach(canal3);
        capteur.attach(canal4);
        algorithmeDiffusion.configure(capteur);
        int numberOfTick = 40;

        while (numberOfTick > 0)
        {
            capteur.tick();
            Thread.sleep(500);
            numberOfTick--;
        }
        scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        displayAfficheursDatas(
                List.of(afficheur1.getData(), afficheur2.getData(), afficheur3.getData(), afficheur4.getData()));
        Logger.info("");
        Logger.info("FIN main()");
    }

    static void displayAfficheursDatas(List<List<Integer>> afficheurDatas)
    {

        for (int i = 0; i < afficheurDatas.size(); ++i)
        {
            StringBuilder stringBuild = new StringBuilder();
            Logger.info("Afficheur {} -------------", i + 1);

            for (int dataVal : afficheurDatas.get(i))
            {
                stringBuild.append(dataVal);
                stringBuild.append(" ");
            }
            Logger.info(stringBuild.toString());
            Logger.info("");
        }
    }
}
