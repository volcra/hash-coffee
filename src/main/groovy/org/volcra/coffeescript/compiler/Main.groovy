package org.volcra.coffeescript.compiler

class Main {
    static cli = new CommandLine()

    static void main(String... args) {
        def options = cli.parse(args);

        if (options == null || options.h) {
            cli.usage()
            return
        }

        def compiler = new CoffeeScriptCompiler()

        if (options.e) {
            def writer = new StringWriter()
            compiler.compile new StringReader(options.arguments()[0]), writer, options.b

            print writer
            return
        }

        if (options.c) {
            def writer = new StringWriter()
            compiler.compile new FileReader(options.arguments()[0]), writer, options.b

            if (options.p) {
                print writer
                return
            }

            def fileName = options.arguments()[0].replace ".coffee", ".js"
            new File(fileName).withWriter { out ->
                out.writeLine writer.toString()
            }
        }
    }
}