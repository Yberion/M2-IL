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
package fr.brandon.aoc.tp.algorithme_diffusion.api;

import fr.brandon.aoc.tp.capteur.api.Capteur;

/**
 * <b>Represente l'algorithme de diffusion</b>
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public interface AlgorithmeDiffusion
{
    /**
     * Permet de configurer l'algorithme de diffusion en lui injectant un capteur.
     *
     * @param capteur est un Capteur (non null)
     *
     * @throws NullPointerException si le capteur est null
     */
    void configure(Capteur capteur);

    /**
     * Lance l'execution de l'algorithme.
     *
     * Le capteur ne doit pas etre null
     *
     * @throws NullPointerException si le capteur est null
     * @throws InterruptedException si le thread est en attente d'un token et qu'un signal d'interruption est lance
     */
    void execute() throws InterruptedException;

    /**
     * Permet de liberer un token sur le semaphore. Est utilise depuis le capteur.
     *
     * @throws NullPointerException si le semaphore est null
     */
    void semaphoreReleaseOnce();
}
