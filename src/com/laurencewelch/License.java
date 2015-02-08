package com.laurencewelch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by laurencewelch on 11/28/14.
 */
public class License {

    private final static Logger logger = Logger.getLogger(License.class.getName());
    private String name = null; // name of License
    private List<String> text = null; //content of License
    private String location = null;

    public License(String name, String[] text){
        this.name = name;
        this.text = new ArrayList<String>(Arrays.asList(text)); //TODO is arraylist really necessary here?
    }

    public License(String name, List<String> text) {
        this.name = name;
        this.text = text;
    }

    /*
       This constructor adds support for the reading in of licenses from files.
       The file is loaded when its needed.
    */
    public License(String location) {
        this.location = location;
    }

    public static List<String> format(License license, Language language, int maxLineLength) {
        List<String> output = new ArrayList<String>();
        List<String> licenseText = license.getText();

        if (maxLineLength == -1) {
            output.add(language.getStartComment());
            for (int i = 0; i < licenseText.size(); i++) {
                output.add(licenseText.get(i) + " " + language.getLineComment());
            }
            output.add(language.getEndComment());
            return output; //ready to be added to the start of the file
        }
        return output;
    }

    public String getName() {
        return name;
    }

    /*
        This method returns the license content.
        If the license was not previously loaded it is read in from a file.
     */
    public List<String> getText() {
        if (text == null) {//load from file
            try {
                text = new ArrayList<String>();
                FileInputStream in = new FileInputStream(location);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    text.add(tmpLine);
                }

                if (text.size() == 0) { // we read in empty file
                    throw new Exception("Read in an empty file");
                }
                return text;
            } catch (FileNotFoundException fnf) {
                logger.log(Level.SEVERE, "Could not find specified license file", fnf);
                return null;
            } catch (IOException io) {
                logger.log(Level.SEVERE, "Experiencing issues reading specified file", io);
                return null;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unknown Error", e);
                return null;
            }
        }
        return text;
    }

    private class WordOccurance {
        public WordOccurance(String sentence) {

        }
    }
}
