/*
Copyright © 2023 Matthew M. Gamble

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package ca.mgamble.xkcd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mgamble
 */
public class XKCDPasswordGenerator {

    static ArrayList<String> uncommon_data = new ArrayList<String>();

    public XKCDPasswordGenerator() throws Exception {
        read_words();

    }

    public String generatePassphrase(int size) {
        String passphrase = "";
        for (int i = 0; i < size; i++) {
            String word = get_word();
            passphrase = passphrase + word.toLowerCase() + "-";
        }
        return removeLastChar(passphrase);
    }

    private static String get_word() {
        Random rand = new Random();
        int ind = rand.nextInt(uncommon_data.size()) + 0; //index of words to get

        return uncommon_data.get(ind);
    }

    private static ArrayList<String> read_words() throws Exception {
        // filename and line being read

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            
            InputStream is = classloader.getResourceAsStream("wordlist.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = r.readLine()) != null) {
                uncommon_data.add(line);
            }

        } catch (Exception ex) {
            throw new Exception("Error while opening seed file: " + ex);
            
        }

        return uncommon_data;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
