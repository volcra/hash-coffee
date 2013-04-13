package org.volcra.coffeescript.compiler

class MainTest extends GroovyTestCase {
    void testMain() {
        Main.main "-cbp", "src\\test\\resources\\test.coffee"
    }

    void testEval() {
        Main.main "-e", "console.log num for num in [10..1]"
    }
}