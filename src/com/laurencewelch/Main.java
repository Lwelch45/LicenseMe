package com.laurencewelch;

import java.util.HashMap;

public class Main {

  public static final HashMap<String, Language> languages = new HashMap<String, Language>();
  public static final HashMap<String, License> licenses = new HashMap<String, License>();


  public static void initializeLicenses() {
    licenses.put("test :)", new License("/home/laurence/Projects/LicenseMe/src/tests/TestLicense.txt"));
  }

  public static void initializeLanguages() {
    languages.put("java", new Language("java", "/*", "*", "*/", "//"));
  }


  public static void main(String[] args) {
    initializeLanguages();
    initializeLicenses();

    System.out.print(licenses.get("test :)").format(languages.get("java"), 150).toString());
  }
}
