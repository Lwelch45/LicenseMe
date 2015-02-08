package com.laurencewelch.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by laurence on 2/7/15.
 */
public class LineFormatter implements Iterator<String> {
    List<Tokenizer.Token> tokens;
    int lineLength = 100;
    int lineIndex = 0;
    int tokenIndex = 0;

    public LineFormatter(List<Tokenizer.Token> tokens, int lineLength) {
        this.tokens = tokens;
        this.lineLength = lineLength;
    }

    @Override
    public String next() {
        if (hasNext()) {
            if ((tokens.get(tokenIndex).getLength() + lineIndex) <= lineLength) {
                Tokenizer.Token tmp = tokens.get(tokenIndex);
                lineIndex += tmp.getLength();
                // another word can fit
                if ((tokens.get(tokenIndex++).getLength() + lineIndex + 1) <= lineLength) {
                    lineIndex += 1; // space character
                    return tmp.getWord() + " ";
                }
                //another word cant fit
                return tmp.getWord();
            }
        }
        throw new NoSuchElementException("There are no elements left to iterate over");
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
