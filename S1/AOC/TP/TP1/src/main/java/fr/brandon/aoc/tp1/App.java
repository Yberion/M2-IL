/*
 * MIT License
 *
 * Copyright (c) 2020 Brandon Largeau
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
package fr.brandon.aoc.tp1;

import fr.brandon.aoc.tp1.afficheur.impl.Afficheur;
import fr.brandon.aoc.tp1.canal.impl.Canal;
import fr.brandon.aoc.tp1.capteur.api.Capteur;
import fr.brandon.aoc.tp1.capteur.impl.CapteurImpl;
import org.tinylog.Logger;

public final class App
{
    public static void main(String[] args)
    {
        Logger.info("main()");
        Capteur capteur = new CapteurImpl();
        Canal canal1 = new Canal();
        Canal canal2 = new Canal();
        Canal canal3 = new Canal();
        Canal canal4 = new Canal();
        Afficheur afficheur1 = new Afficheur();
        Afficheur afficheur2 = new Afficheur();
        Afficheur afficheur3 = new Afficheur();
        Afficheur afficheur4 = new Afficheur();
    }
}
