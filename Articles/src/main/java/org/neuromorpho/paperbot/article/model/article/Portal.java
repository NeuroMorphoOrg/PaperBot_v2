/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.model.article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Portal {

    private String name;
    private List<String> keyWordList;

    public Portal() {
    }

    public Portal(String name, List<String> keyWordList) {
        this.name = name;
        this.keyWordList = keyWordList;
    }

    public Portal(String name, String keyWord) {
        this.name = name;
        this.keyWordList = new ArrayList();
        this.keyWordList.add(keyWord);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeyWordList() {
        return keyWordList;
    }

    public void setKeyWordList(List<String> keyWordList) {
        this.keyWordList = keyWordList;
    }

    public void updateKeyWordList(List<String> keyWordList) {
        for (String keyword : keyWordList) {
            if (!this.keyWordList.contains(keyword)) {
                this.keyWordList.add(keyword);
            }
        }
    }

    public Set<String> getKeyWordSet() {
        Set<String> keyWordSet = new HashSet<>();
        for (String keyWord : this.keyWordList) {
            keyWordSet.add(keyWord);
        }
        return keyWordSet;
    }

    @Override
    public String toString() {
        String result = "{" + this.name + ": ";
        for (String keyWord : keyWordList) {
            result = result + keyWord + ",";
        }
        result = result.substring(0, result.length() - 1) + "}";
        return result;
    }
}
