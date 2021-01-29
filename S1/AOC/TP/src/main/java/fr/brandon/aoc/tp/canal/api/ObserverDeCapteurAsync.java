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
package fr.brandon.aoc.tp.canal.api;

import fr.brandon.aoc.tp.capteur.api.Capteur;
import java.util.concurrent.Future;

/**
 * <b>Represente un observeur de capteur en asynchrone</b>
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public interface ObserverDeCapteurAsync
{
    /**
     * Permet d'integrer le capteur au canal
     *
     * @param capteur qui permettra la recuperation de sa valeur (non null)
     *
     * @throws NullPointerException si le capteur est null
     */
    void setCapteur(Capteur capteur);

    /**
     * Permet de faire un appel asynchrone pour notifier l'afficheur d'une nouvelle valeur apres un certain temps (entre 0 et 2 secondes).
     *
     * Le capteur ne doit pas etre null
     *
     * @return Future<Void> un future de rien
     *
     * @throws NullPointerException si le capteur est null
     */
    Future<Void> update();
}
