package org.volcra.coffeescript.compiler

import org.mozilla.javascript.Context

/**
 * CoffeeScript compiler.
 */
@Singleton(lazy = true)
class CoffeeScriptCompiler {
    /**
     * Global Scope.
     */
    private static def globalScope

    /**
     * Coffee Script JavaScript.
     */
    private static def coffeeScript = getClass().getResource("/org/volcra/coffeescript/coffee-script.js").text

    /**
     * Default Constructor.
     */
    CoffeeScriptCompiler() {
        withContext { context ->
            context.optimizationLevel = -1
            globalScope = context.initStandardObjects()
            context.evaluateString globalScope, coffeeScript, "coffee-script.js", 1, null
        }
    }

    /**
     * Higher Order function that provides a context to the closure being passed.
     *
     * @param c the function or closure to execute
     */
    def withContext(Closure c) {
        def context = Context.enter()

        try {
            c context
        } finally {
            Context.exit()
        }
    }

    /**
     * Uses the reader to get the Coffee Script code and the writer to store the resulted JavaScript code.
     *
     * @param reader
     * @param writer
     * @param bare compile the JavaScript without the top-level function safety wrapper.
     * @return the writer with the resulted code after the compilation
     */
    def compile(Reader reader, Writer writer, Boolean bare = false) {
        withContext { context ->
            def compileScope = context.newObject globalScope
            compileScope.parentScope = globalScope
            compileScope.put "coffeeScriptSource", compileScope, reader.text

            def script = context.evaluateString(compileScope, "CoffeeScript.compile(coffeeScriptSource, {bare: ${bare}})",
                    "CoffeeScriptCompiler", 0, null)

            writer << script
            writer.flush()
        }
    }
}