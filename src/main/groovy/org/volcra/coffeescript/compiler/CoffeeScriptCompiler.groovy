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
     * CoffeeScript JavaScript compiler code.
     */
    private static def coffeeScript = getClass().getResource("/org/volcra/coffeescript/coffee-script.js").text

    /**
     * Default Constructor.
     */
    private CoffeeScriptCompiler() {
        withContext {
            it.optimizationLevel = -1
            globalScope = it.initStandardObjects()
            it.evaluateString globalScope, coffeeScript, "coffee-script.js", 1, null
        }
    }

    /**
     * Higher Order function that provides a context to the closure being passed.
     *
     * @param c the function or closure to execute
     */
    static def withContext(Closure c) {
        def context = Context.enter()

        try {
            c context
        } finally {
            Context.exit()
        }
    }

    /**
     * Uses the reader to get the CoffeeScript code and the writer to store the resulted JavaScript code.
     *
     * @param reader the reader to get the text
     * @param writer the writer to receive the output
     * @param bare   compile the JavaScript without the top-level function safety wrapper
     * @return the writer with the resulted code
     */
    static def compile(Reader reader, Writer writer, Boolean bare = false) {
        withContext {
            def compileScope = it.newObject globalScope
            compileScope.parentScope = globalScope
            compileScope.put "coffeeScriptSource", compileScope, reader.text

            def script = it.evaluateString(compileScope, "CoffeeScript.compile(coffeeScriptSource, {bare: ${bare}})",
                    "CoffeeScriptCompiler", 0, null)

            writer << script
            writer.flush()
        }
    }
}