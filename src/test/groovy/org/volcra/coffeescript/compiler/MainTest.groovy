/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.coffeescript.compiler

/**
 * <p>Main Test.</p>
 * <p>This does not check for any assertions. Runs and expects no errors.</p>
 */
class MainTest extends GroovyTestCase {
    /**
     * Calling main with no arguments. Should print help/usage.
     */
    void testMainEmptyArgs() {
        Main.main()
    }

    /**
     * Calling main with CoffeeScript file. Should compile and print result to stdout.
     */
    void testMainCompileBareAndPrint() {
        Main.main "-cbp", "src\\test\\resources\\test.coffee"
        Main.main "-cbp", "src\\test\\resources\\hash.coffee"
    }

    /**
     * Calling main with eval option -e. Should print resulted JavaScript code to sdtout.
     */
    void testEval() {
        Main.main "-e", "console.log num for num in [10..1]"
    }

    /**
     * Calling main with -h. Should print help/usage.
     */
    void testHelp() {
        Main.main "-h"
    }
}