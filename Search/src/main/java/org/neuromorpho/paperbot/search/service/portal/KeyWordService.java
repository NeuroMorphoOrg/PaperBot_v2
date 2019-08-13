/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.service.portal;

import java.util.List;

import org.neuromorpho.paperbot.search.repository.KeyWordRepository;
import org.neuromorpho.paperbot.search.model.KeyWord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyWordService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KeyWordRepository keyWordRepository;

    public List<KeyWord> findAll() {
        log.debug("Retrieving all keywords from DB");
        return keyWordRepository.findAll();
    }

    public void updateKeyWordList(List<KeyWord> keyWordList) {
        log.debug("Updating keywords into DB");
        keyWordRepository.saveAll(keyWordList);
    }
    
}
