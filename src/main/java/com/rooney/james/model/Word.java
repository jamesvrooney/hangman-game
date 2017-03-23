package com.rooney.james.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Data @NoArgsConstructor
public class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }
}
