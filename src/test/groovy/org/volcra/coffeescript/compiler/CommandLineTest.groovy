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

/**
 * CommandLineTest.
 */
class CommandLineTest extends GroovyTestCase {
    private final command = new CommandLine()

    /**
     * Should print help/usage.
     */
    void testUsage() {
        command.usage()
    }

    /**
     * Should return an empty list of arguments.
     */
    void testParseNoArgs() {
        assert command.parse().arguments().isEmpty()
    }

    /**
     * Should print return help as true.
     */
    void testParseWithHelp() {
        assert command.parse('-h').h
    }

    /**
     * Should print return help as true.
     */
    void testParseWithBare() {
        assert command.parse('-b').b
    }

    /**
     * Should print return help as true.
     */
    void testParseWithPrint() {
        assert command.parse('-p').p
    }
}
