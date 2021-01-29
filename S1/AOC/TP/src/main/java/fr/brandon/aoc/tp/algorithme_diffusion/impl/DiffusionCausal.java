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
package fr.brandon.aoc.tp.algorithme_diffusion.impl;

import fr.brandon.aoc.tp.algorithme_diffusion.api.AlgorithmeDiffusion;
import fr.brandon.aoc.tp.canal.api.ObserverDeCapteurAsync;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import java.util.Objects;

/**
 * DiffusionCausal est une class immutable.
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public final class DiffusionCausal implements AlgorithmeDiffusion
{
    private Capteur capteur;

    @Override
    public void semaphoreReleaseOnce()
    {
        // Ne fais rien sur cet algo
    }

    @Override
    public void configure(Capteur capteur)
    {
        Objects.requireNonNull(capteur);
        this.capteur = capteur;
    }

    @Override
    public void execute() throws InterruptedException
    {
        Objects.requireNonNull(this.capteur);

        for (ObserverDeCapteurAsync canal : this.capteur.getObservers())
        {
            canal.setCapteur(this.capteur);
            canal.update();
        }
    }
}
