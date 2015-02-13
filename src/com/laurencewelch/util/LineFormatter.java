package com.laurencewelch.util;

import com.laurencewelch.Language;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by laurence on 2/7/15.
 */
public class LineFormatter implements Iterator<String> {
  private List<Tokenizer.Token> tokens;
  private Language language;
  private int maxLineLength = 100;
  private int lineIndex = 0;
  private int tokenIndex = 0;

  //flags
  private boolean singleFlag = false; //is single line software
  private boolean startFlag = false; // laid starting token

  public LineFormatter(List<Tokenizer.Token> tokens, Language language, int lineLength) {
    this.tokens = tokens;
    if (lineLength > 0) {
      maxLineLength = lineLength;
    }
    this.language = language;
  }

  @Override
  public String next() {
    if (hasNext()) {
      //Weird order of operations but check to see if its a newline token
      if (tokens.get(tokenIndex).getWord().equals("[NEWLINE]")) {
        tokenIndex++;
        lineIndex = 0;
        return "\n";
      }

      if (tokenIndex == 0 && lineIndex == 0 && isLastLine()) { //single line license
        lineIndex += language.getSingleComment().length() + 1;
        singleFlag = true;
        return language.getSingleComment() + " ";

      } else if (tokenIndex == 0 && !startFlag && lineIndex == 0) { //first line in multi line file
        lineIndex = 0;
        startFlag = true;
        return language.getStartComment() + "\n";

      } else if (lineIndex == 0 && singleFlag && isLastLine()) { //multiple lines in a single line license
        lineIndex += language.getSingleComment().length() + 1;
        return language.getSingleComment() + " ";


      } else if (lineIndex == 0) { //just another regular newline
        lineIndex += language.getLineComment().length() + 1;
        return language.getLineComment() + " ";
      }

      if ((tokens.get(tokenIndex).getLength() + lineIndex) <= maxLineLength) {
        Tokenizer.Token tmp = tokens.get(tokenIndex);
        lineIndex += tmp.getLength();
        // another word can fit
        if ((tokens.size() - tokenIndex > 1) && (tokens.get(tokenIndex + 1).getLength() + lineIndex + 1) <= maxLineLength) {
          lineIndex += 1; // space character
          tokenIndex++;
          return tmp.getWord() + " ";
        }
        //another word cant fit
        lineIndex = 0;
        tokenIndex++;
        if (!hasNext()) {
          return tmp.getWord() + "\n" + language.getEndComment() + "\n";
        }
        return tmp.getWord() + "\n";
      }

      lineIndex = 0;
      return "\n ";

    }
    throw new NoSuchElementException("There are no elements left to iterate over");
  }

  /*
   * calculates a running sum at the end of each line to see if tis greater the maximum allowed per line.
   */
  private boolean isLastLine() {
    int rSum = language.getEndComment().length() + 1;
    for (int i = tokenIndex; i < tokens.size(); i++) {
      rSum += tokens.get(i).getLength() + 1;
      if (rSum > maxLineLength) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean hasNext() {
    return !(tokens.size() == tokenIndex);
  }

  @Override
  public void remove() {
    if (tokenIndex <= 0) {
      throw new IllegalStateException("You can't delete an element before first next() method call");
    }
    tokens.remove(--tokenIndex);
  }

}
