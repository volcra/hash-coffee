package org.volcra.coffeescript.compiler

/**
 * <p>Main class.</p>
 * <p>Parses the command line arguments and runs the CoffeeScript compiler.</p>
 */
class Main {
    /**
     * Command Line.
     */
    private static def cli = new CommandLine()

    /**
     * CoffeeScript Compiler.
     */
    @Lazy
    private static def compiler = CoffeeScriptCompiler.instance

    /**
     * Parsers the command line arguments and runs the CoffeeScript compiler.
     */
    static void main(String... args) {
        def options = cli.parse(args);

        if (options.arguments().isEmpty() || options.h) {
            cli.usage()
        } else if (options.e) {
            def writer = new StringWriter()
            compiler.compile new StringReader(options.arguments()[0]), writer, options.b

            println writer
        } else if (options.c) {
            def writer = new StringWriter()
            compiler.compile new FileReader(options.arguments()[0]), writer, options.b

            if (options.p) {
                println writer
            } else {
                def fileName = options.arguments()[0].replace ".coffee", ".js"

                new File(fileName).withWriter("UTF-8") {
                    it.writeLine writer.toString()
                }
            }
        }
    }
}