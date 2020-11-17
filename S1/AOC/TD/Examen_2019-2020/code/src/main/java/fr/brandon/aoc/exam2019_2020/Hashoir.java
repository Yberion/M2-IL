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
package fr.brandon.aoc.exam2019_2020;

import java.util.concurrent.BlockingQueue;

/**
 * A hashing machine to compute the hashed value from a password source.
 * It takes a blocking queue of password strings as input, and produces pairs of
 * (password, hash)
 * in another BlockQueue output.
 */
public interface Hashoir
{
    /**
     * Sets up the configuration for the algorithm
     *
     * @param input  where to read password from
     * @param output where to output (password, hashed password) pairs to
     */
    void configure(BlockingQueue<String> input, BlockingQueue<Pair<String, String>> output);

    /**
     * Reads passwords from the input, compute hashes and outputs (password, hashed
     * password) pairs.
     * It never stops reading its input but may block waiting for new input on its
     * BlockingQueue.
     */
    void execute() throws InterruptedException;
}
