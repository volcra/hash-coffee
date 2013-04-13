package org.volcra.coffeescript.compiler

class CommandLine {
    def cli

    CommandLine() {
        cli = new CliBuilder(usage: 'csquare [options]', header: "Options")

        cli.h longOpt: "help", "Print Help."
        cli.b longOpt: "bare", "Compile the JavaScript without the top-level function safety wrapper."
        cli.c longOpt: "compile", "Compile a .coffee script into a .js JavaScript file of the same name."
        cli.e longOpt: "eval", "Compile and print a little snippet of CoffeeScript directly from the command line."
        cli.j longOpt: "join", args: 1, argName: "FILE", "Before compiling, concatenate all scripts together in the order they were passed, and write them into the specified file. Useful for building large projects."
        cli.o longOpt: "output", args: 1, argName: "DIR", "Write out all compiled JavaScript files into the specified directory."
        cli.p longOpt: "print", "Instead of writing out the JavaScript as a file, print it directly to stdout."
    }

    def usage() {
        cli.usage()
    }

    def parse(String... args) {
        cli.parse args
    }
}