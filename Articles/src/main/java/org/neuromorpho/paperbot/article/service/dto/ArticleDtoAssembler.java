/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.service.dto;


import org.bson.types.ObjectId;
import org.neuromorpho.paperbot.article.model.article.Article;
import org.neuromorpho.paperbot.article.model.article.ArticleData;
import org.neuromorpho.paperbot.article.model.article.ReconstructionsStatus;
import org.neuromorpho.paperbot.article.model.article.Shared;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleDtoAssembler {

    private ReconstructionsDtoAssembler reconstructionsAssembler = new ReconstructionsDtoAssembler();
    private ArticleDataDtoAssembler dataAssembler = new ArticleDataDtoAssembler();

    public ArticleDto createArticleDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId().toString());
        articleDto.setData(dataAssembler.createDataDto(article.getData()));
        articleDto.setMetadata(article.getMetadata());
        articleDto.setSearchPortal(article.getSearchPortal());
        articleDto.setReconstructions(reconstructionsAssembler.createReconstructionsDto(article.getReconstructions()));
        articleDto.setStatus(article.getStatus().getStatus());
        articleDto.setLocked(article.getLocked() != null? article.getLocked():false);
        return articleDto;

    }

    public ArticleDto createArticleDto(Article article, List<Article> sharedArticleList) {
        ArticleDto articleDto = this.createArticleDto(article);
        if (sharedArticleList != null) {
            List<ArticleDto> sharedList = sharedArticleList.stream().map(sharedArticle -> this.createArticleDto(sharedArticle)).collect(Collectors.toList());
            articleDto.setSharedList(sharedList);
        }
        articleDto.setStatus(article.getStatus().getStatus());
        return articleDto;
    }

    public Article createArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setId(new ObjectId(articleDto.getId()));
        article.setData(dataAssembler.createData(articleDto.getData()));
        article.setMetadata(articleDto.getMetadata());
        article.setSearchPortal(articleDto.getSearchPortal());
        return article;
    }

    public List<ReconstructionsStatus> createCurrentStatusList(ReconstructionsDto reconstructionsDto) {
        return reconstructionsAssembler.createCurrentStatusList(reconstructionsDto);
    }

    public List<Shared> createSharedList(List<SharedDto> sharedDtoList) {
        return sharedDtoList.stream().map(sharedDto -> new Shared(sharedDto.getId())).collect(Collectors.toList());
    }

    public ArticleData createArticleData(ArticleDataDto articleDataDto) {
        return dataAssembler.createData(articleDataDto);
    }

    public List<String> createDataUsage(List<String> dataUsageDto) {
        return dataAssembler.createDataUsage(dataUsageDto);
    }
}
