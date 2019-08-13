package org.neuromorpho.paperbot.search.service.sciencedirect.model.search;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class Authors {

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("author")
    private JsonNode authorInternal;

    private List<String> authorList;


    public List<String> getAuthorList() {
        List<String> authorList = new ArrayList();
        if (authorInternal.isArray()) {
            for (final JsonNode objNode : authorInternal) {
                String name = objNode.get("$").asText();
                authorList.add(name);
            }
        } else if (authorInternal.isTextual()) {
            authorList.add(authorInternal.asText());
        }

        return authorList;
    }
    

}
