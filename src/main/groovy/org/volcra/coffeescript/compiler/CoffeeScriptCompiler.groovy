/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.coffeescript.compiler

import org.mozilla.javascript.Context

/**
 * CoffeeScript compiler.
 */
@Singleton(lazy = true, strict = false)
class CoffeeScriptCompiler {
    /**
     * Global Scope.
     */
    private static globalScope

    /**
     * CoffeeScript JavaScript compiler source.
     */
    private static final COFFEE_SCRIPT = getClass().getResource('/org/volcra/coffeescript/coffee-script-1.7.1.js').text

    /**
     * <p>Default Constructor.</p>
     * <p>Initializes the Rhino engine with Standard Objects and the coffee-script source.</p>
     */
    private CoffeeScriptCompiler() {
        withContext {
            it.optimizationLevel = -1
            globalScope = it.initStandardObjects()
            it.evaluateString globalScope, COFFEE_SCRIPT, 'coffee-script.js', 1, null
        }
    }

    /**
     * Higher Order function that provides a context to the closure being passed.
     *
     * @param c the function or closure to execute
     */
    private withContext(Closure c) {
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
     * @param bare compile the JavaScript without the top-level function safety wrapper
     * @return the writer with the resulted code
     */
    void compile(Reader reader, Writer writer, Boolean bare = false) {
        withContext {
            def compileScope = it.newObject globalScope
            compileScope.parentScope = globalScope
            compileScope.put 'coffeeScriptSource', compileScope, reader.text

            def script = it.evaluateString compileScope, "CoffeeScript.compile(coffeeScriptSource, {bare: ${bare}})",
                'CoffeeScriptCompiler', 0, null

            writer << script
            writer.flush()
        }
    }
}