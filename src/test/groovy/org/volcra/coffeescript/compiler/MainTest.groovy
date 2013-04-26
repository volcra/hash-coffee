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