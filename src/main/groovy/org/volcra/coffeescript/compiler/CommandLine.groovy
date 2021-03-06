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
 * Helper class that provides access to the command line options.
 */
class CommandLine {
    /**
     * Command Line Builder.
     */
    private final cli = new CliBuilder(usage: '#coffee [options]', header: 'Options')

    /**
     * Default constructor.
     */
    CommandLine() {
        cli.with {
            h longOpt: 'help', 'Print Help.'
            b longOpt: 'bare', 'Compile the JavaScript without the top-level function safety wrapper.'
            c longOpt: 'compile', 'Compile a .coffee script into a .js JavaScript file of the same name.'
            e longOpt: 'eval', 'Compile and print a little snippet of CoffeeScript directly from the command line.'
            j longOpt: 'join', args: 1,
                argName: 'FILE', 'Before compiling, concatenate all scripts together in the order they were passed,\
                                  and write them into the specified file. Useful for building large projects.'
            o longOpt: 'output', args: 1,
                argName: 'DIR', 'Write out all compiled JavaScript files into the specified directory.'
            p longOpt: 'print', 'Instead of writing out the JavaScript as a file, print it directly to stdout.'
        }
    }

    /**
     * <p>Shows the command usage.</p>
     * <pre>
     * usage: #coffee [options]
     * Options
     *  -b,--bare           Compile the JavaScript without the top-level function
     *                      safety wrapper.
     *  -c,--compile        Compile a .coffee script into a .js JavaScript file
     *                      of the same name.
     *  -e,--eval           Compile and print a little snippet of CoffeeScript
     *                      directly from the command line.
     *  -h,--help           Print Help.
     *  -j,--join &lt;FILE&gt;    Before compiling, concatenate all scripts together in
     *                      the order they were passed, and write them into the
     *                      specified file. Useful for building large projects.
     *  -o,--output &lt;DIR&gt;   Write out all compiled JavaScript files into the
     *                      specified directory.
     *  -p,--print          Instead of writing out the JavaScript as a file,
     *                      print it directly to stdout.
     * </pre>
     */
    void usage() {
        cli.usage()
    }

    /**
     * Parses the arguments and returns the options.
     *
     * @param args the arguments to parse
     * @return the options holding the parser result
     */
    OptionAccessor parse(String... args) {
        cli.parse args
    }
}
