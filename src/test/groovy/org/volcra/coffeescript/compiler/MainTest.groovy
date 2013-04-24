package org.volcra.coffeescript.compiler

class MainTest extends GroovyTestCase {
    void testMain() {
        Main.main()
        Main.main "-cbp", "src\\test\\resources\\test.coffee"
        Main.main "-cbp", "src\\test\\resources\\hash.coffee"
    }

    void testEval() {
        Main.main "-e", "console.log num for num in [10..1]"
    }

    void testHelp() {
        Main.main "-h"
    }
}