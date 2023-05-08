/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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