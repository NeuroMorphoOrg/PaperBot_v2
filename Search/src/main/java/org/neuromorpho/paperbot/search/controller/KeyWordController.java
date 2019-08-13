package org.neuromorpho.paperbot.search.controller;

import java.util.List;

import org.neuromorpho.paperbot.search.model.KeyWord;
import org.neuromorpho.paperbot.search.service.portal.KeyWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keywords")
public class KeyWordController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private KeyWordService keyWordService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public List<KeyWord> findAll() {
        return keyWordService.findAll();
       
    }
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public void updateKeyWordList(@RequestBody List<KeyWord> keyWordList) {
        keyWordService.updateKeyWordList(keyWordList);
    }

}
