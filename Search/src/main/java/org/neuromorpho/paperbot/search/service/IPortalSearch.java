package org.neuromorpho.paperbot.search.service;

import org.neuromorpho.paperbot.search.dto.fulltext.ArticleContentDto;
import org.neuromorpho.paperbot.search.model.KeyWord;
import org.neuromorpho.paperbot.search.model.Portal;




public interface IPortalSearch {
    
    void findArticleList(KeyWord keyWord, Portal portal) throws Exception;

    ArticleContentDto getArticleContent(String doi, Portal portal);

}
