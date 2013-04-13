package org.volcra.coffeescript.compiler

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CoffeeScriptCompiler {
    private Scriptable globalScope

    CoffeeScriptCompiler() {
        String coffeeScript = getClass().getResource("/org/volcra/coffeescript/coffee-script.js").text
        Context context = Context.enter()
        //context.setOptimizationLevel(-1) // Without this, Rhino hits a 64K bytecode limit and fails
        try {
            globalScope = context.initStandardObjects()
            context.evaluateString globalScope, coffeeScript, "coffee-script.js", 0, null
        } finally {
            Context.exit()
        }
    }

    void compile(Reader reader, Writer writer, Boolean bare = false) {
        Context context = Context.enter()
        Scriptable compileScope = context.newObject globalScope
        compileScope.parentScope = globalScope
        compileScope.put "coffeeScriptSource", compileScope, reader.text

        try {
            String script = context.evaluateString(compileScope, "CoffeeScript.compile(coffeeScriptSource, {bare: ${bare}})",
                    "CoffeeScriptCompiler", 0, null)

            writer << script
        } finally {
            Context.exit()
        }
    }
}