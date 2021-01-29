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
package fr.brandon.aoc.tp.observer_de_capteur.api;

import fr.brandon.aoc.tp.canal.api.CapteurAsync;
import java.util.List;

/**
 * <b>Represente un observeur de capteur</b>
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public interface ObserverDeCapteur
{
    /**
     * Permet de simuler la mise a jour de la valeur affichee par l'observer de capteur.
     *
     * @param canal est un CapteurAsync
     *
     * @throws InterruptedException ou ExecutionException
     */
    void update(CapteurAsync canal);

    /**
     * Permet d'obtenir les valeurs que l'observer de capteur a reçues.
     *
     * @return List<Integer> represente l'ensemble des valeurs reçues
     */
    List<Integer> getData();
}
