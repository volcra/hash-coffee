package org.volcra.coffeescript.compiler

/**
 * <p>Helper class that provides access to the command line options.</p>
 */
class CommandLine {
    /**
     * Command Line Builder.
     */
    def cli

    /**
     * Default constructor.
     */
    CommandLine() {
        cli = new CliBuilder(usage: '#coffee [options]', header: "Options")

        cli.h longOpt: "help", "Print Help."
        cli.b longOpt: "bare", "Compile the JavaScript without the top-level function safety wrapper."
        cli.c longOpt: "compile", "Compile a .coffee script into a .js JavaScript file of the same name."
        cli.e longOpt: "eval", "Compile and print a little snippet of CoffeeScript directly from the command line."
        cli.j longOpt: "join", args: 1, argName: "FILE", "Before compiling, concatenate all scripts together in the order they were passed, and write them into the specified file. Useful for building large projects."
        cli.o longOpt: "output", args: 1, argName: "DIR", "Write out all compiled JavaScript files into the specified directory."
        cli.p longOpt: "print", "Instead of writing out the JavaScript as a file, print it directly to stdout."
    }

    /**
     * <p>Show the command usage.</p>
     * <pre>
     * usage: #coffee [options]
     * Options
     *  -b,--bare           Compile the JavaScript without the top-level function
     *                      safety wrapper.
     *  -c,--compile        Compile a .coffee script into a .js JavaScript file
     *                      of the same name.
     *  -e,--eval           Compile and print a little snippet of CoffeeScript
     *                      directly from the command line.
     *  -h,--help           Print Help.
     *  -j,--join &lt;FILE&gt;    Before compiling, concatenate all scripts together in
     *                      the order they were passed, and write them into the
     *                      specified file. Useful for building large projects.
     *  -o,--output &lt;DIR&gt;   Write out all compiled JavaScript files into the
     *                      specified directory.
     *  -p,--print          Instead of writing out the JavaScript as a file,
     *                      print it directly to stdout.
     * </pre>
     */
    def usage() {
        cli.usage()
    }

    /**
     * Parsers the arguments and returns the options.
     *
     * @param args the arguments to parse
     * @return the options holding the parse result
     */
    def parse(String... args) {
        cli.parse args
    }
}