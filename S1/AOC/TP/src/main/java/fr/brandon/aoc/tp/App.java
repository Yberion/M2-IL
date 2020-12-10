/*
 * MIT License
 *
 * Copyright (c) 2020 Brandon Largeau, David Lafia-Monwoo
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.tinylog.Logger;

import fr.brandon.aoc.tp.algorithme_diffusion.api.AlgorithmeDiffusion;
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionAtomique;
import fr.brandon.aoc.tp.canal.api.CapteurAsync;
import fr.brandon.aoc.tp.canal.impl.Canal;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import fr.brandon.aoc.tp.capteur.impl.CapteurImpl;
import fr.brandon.aoc.tp.observer_de_capteur.api.ObserverDeCapteur;
import fr.brandon.aoc.tp.observer_de_capteur.impl.Afficheur;

public final class App
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        Logger.info("DEBUT main()");
        AlgorithmeDiffusion algorithmeDiffusion = new DiffusionAtomique();
        Capteur capteur = new CapteurImpl(algorithmeDiffusion);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(8);
        CapteurAsync canal1 = new Canal(scheduledExecutorService);
        CapteurAsync canal2 = new Canal(scheduledExecutorService);
        CapteurAsync canal3 = new Canal(scheduledExecutorService);
        CapteurAsync canal4 = new Canal(scheduledExecutorService);
        ObserverDeCapteur afficheur1 = new Afficheur();
        ObserverDeCapteur afficheur2 = new Afficheur();
        ObserverDeCapteur afficheur3 = new Afficheur();
        ObserverDeCapteur afficheur4 = new Afficheur();
        
        MessageDigest md = MessageDigest.getInstance("SHA1");
        
        System.out.println(md);
        
        Logger.info("FIN main()");
    }
}
