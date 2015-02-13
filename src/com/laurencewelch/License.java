package com.laurencewelch;

import com.laurencewelch.util.LineFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by laurencewelch on 11/28/14.
 */
public class License {

  private final static Logger logger = Logger.getLogger(License.class.getName());

  private HashMap<String, String> fields = null;
  private String name = null; // name of License
  private List<String> text = null; //content of License
  private String location = null;

  public License(String name, String[] text) {
    this.name = name;
    this.text = new ArrayList<String>(Arrays.asList(text)); //TODO is arraylist really necessary here?
  }

  public License(String name, List<String> text) {
    this.name = name;
    this.text = text;
  }

  public License(String name, String[] text, HashMap<String, String> fields) {
    this.name = name;
    this.text = new ArrayList<String>(Arrays.asList(text));
    this.fields = fields;
  }

  public License(String name, List<String> text, HashMap<String, String> fields) {
    this.name = name;
    this.text = text;
    this.fields = fields;
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

    return output;
  }

  public StringBuilder format(Language language, int maxLineLength) {
    StringBuilder sb = new StringBuilder();
    LineFormatter lf = new LineFormatter(new com.laurencewelch.util.Tokenizer(getText(), fields).tokenize(), language, maxLineLength);
    while (lf.hasNext()) {
      sb.append(lf.next());
    }
    return sb;
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

  public HashMap<String, String> getFields() {
    return fields;
  }

  public License setField(String key, String value) {
    if (fields == null) {
      fields = new HashMap<String, String>();
    }
    fields.put(key, value);
    return this;
  }

}
