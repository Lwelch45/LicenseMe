package com.laurencewelch.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by laurence on 2/7/15.
 */
public class Tokenizer {
  private List<String> text;
  //token, true value
  private Map<String, String> key_pairs;

  public Tokenizer(List<String> text, Map<String, String> key_pairs) {
    this.text = text;
    this.key_pairs = key_pairs;
  }

  //stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
  private static List<String> split(String subject, String delimeters) {
    StringTokenizer strTkn = new StringTokenizer(subject, delimeters);
    ArrayList<String> arrayL = new ArrayList<String>(subject.length());
    while (strTkn.hasMoreTokens()) {
      arrayL.add(strTkn.nextToken());
    }
    return arrayL;
  }

  private String replacePlaceHolders(String input) {
    if (key_pairs == null) {
      //TODO warn the user that the license might contain tokens
      return input;
    }
    for (Map.Entry<String, String> row : key_pairs.entrySet()) {
      if (input.contains(row.getKey()) == true) {
        input.replace(row.getKey(), row.getValue());
      }
    }
    return input;
  }

  public List<Token> tokenize() {
    ArrayList<Token> tokens = new ArrayList<Token>();
    for (int i = 0; i < text.size(); i++) {
      String tmpLine = text.remove(i);
      List<String> preprocessedTokens = split(replacePlaceHolders(tmpLine), " ");
      for (String word : preprocessedTokens) {
        tokens.add(new Token(word, word.length()));
      }
    }
    return tokens;
  }

  public class Token {
    private String word;
    private int length;

    private Token(String word, int length) {
      this.word = word;
      this.length = length;
    }

    private Token(String word) {
      this.word = word;
      this.length = word.length();
    }

    public int getLength() {
      return this.length;
    }

    public String getWord() {
      return this.word;
    }
  }
}
