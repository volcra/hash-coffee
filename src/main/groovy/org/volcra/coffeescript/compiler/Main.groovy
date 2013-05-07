/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.coffeescript.compiler

/**
 * Main class.
 * <p>Parses the command line arguments and runs the CoffeeScript compiler.</p>
 */
class Main {
    /**
     * Command Line.
     */
    private static final def cli = new CommandLine()

    /**
     * CoffeeScript Compiler.
     */
    @Lazy
    private static final def compiler = CoffeeScriptCompiler.instance

    /**
     * <p>Parses the command line arguments and runs the CoffeeScript compiler.</p>
     *
     * <p>If no arguments are given, will print the Usage.</p>
     *
     * @param args command line arguments
     */
    static void main(String... args) {
        def options = cli.parse(args)
        def arguments = options.arguments()

        if (arguments.isEmpty() || options.h) cli.usage()
        else if (options.e) eval arguments[0], options.b
        else if (options.c) compile arguments[0], options.p, options.b
    }

    /**
     * Evaluates an input String and prints the result to the stdout.
     *
     * @param script CoffeeScript to evaluate
     * @param bare bare option
     */
    private static void eval(String script, Boolean bare) {
        def writer = new StringWriter()
        compiler.compile new StringReader(script), writer, bare

        println writer
    }

    /**
     * Compiles a script, the result may be printed to the stdout or written to a file.
     *
     * @param script CoffeeScript code
     * @param print Whether to print the result to the sdtout or not
     * @param bare bare option
     */
    private static void compile(String script, Boolean print, Boolean bare) {
        def writer = new StringWriter()
        compiler.compile new FileReader(script), writer, bare

        if (print) println writer
        else
            new File(script.replace(".coffee", ".js")).withWriter("UTF-8") {
                it.writeLine writer.toString()
            }
    }
}