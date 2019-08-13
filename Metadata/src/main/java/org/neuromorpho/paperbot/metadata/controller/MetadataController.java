package org.neuromorpho.paperbot.metadata.controller;



import org.neuromorpho.paperbot.metadata.model.Metadata;
import org.neuromorpho.paperbot.metadata.service.MetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class MetadataController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MetadataService metadataService;
    

    @CrossOrigin
    @RequestMapping(value = "/distinct", method = RequestMethod.GET)
    public List<String> getMetadataDistinctValues(
            @RequestParam(required = true) String key) {
        return metadataService.getDistinctByKey(key);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public List<Metadata> findAll() {
        return metadataService.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/values", method = RequestMethod.GET)
    public List<String> getMetadataValues(
            @RequestParam(required = true) String key) {
        List<Metadata> metadataList = metadataService.getByKey(key);
        return metadataList.stream().map(value -> value.getName()).collect(Collectors.toList());
    }
    

}
