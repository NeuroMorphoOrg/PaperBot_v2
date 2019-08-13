package org.neuromorpho.paperbot.article.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.neuromorpho.paperbot.article.model.article.Article;
import org.neuromorpho.paperbot.article.model.article.ArticleData;
import org.neuromorpho.paperbot.article.model.article.ReconstructionsStatus;
import org.neuromorpho.paperbot.article.model.article.Shared;
import org.neuromorpho.paperbot.article.service.dto.*;
import org.neuromorpho.paperbot.article.controller.Search;
import org.neuromorpho.paperbot.article.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LiteratureService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Article.ArticleStatus defaultStatus = Article.ArticleStatus.TO_EVALUATE;

    private ArticleDtoAssembler assembler = new ArticleDtoAssembler();
    private ArticleDataDtoAssembler dataAssembler = new ArticleDataDtoAssembler();


    @Autowired
    private ArticleRepository repository;
    
    public Map<String, Long> getSummary(String date) {
        return repository.getSummary(date);
    }

    public Page<ArticleDto> findArticleList(String articleStatus,
                                            Map<String, String> queryParams) throws ParseException {
        Article.ArticleStatus status = Article.ArticleStatus.getArticleStatus(articleStatus);

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getKey().contains("dataUsage")) {
                entry.setValue(ArticleData.DataUsage.getUsage(entry.getValue()).toString());
            }
        }
        Page<Article> articlePage = repository.findArticlesByQuery(status, queryParams);
        log.debug("Found #articles : " + articlePage.getTotalElements());

        List<ArticleDto> articleDtoList = articlePage.getContent().stream()
                .map(article -> assembler.createArticleDto(article)).collect(Collectors.toList());

        return new PageImpl(articleDtoList, articlePage.getPageable(), articlePage.getTotalElements());
    }


    public String saveArticleManual(ArticleDto articleDto) {
        Article article = assembler.createArticle(articleDto);
        article.setOcDate();
        ObjectId id = repository.save(article, defaultStatus);
        return id.toString();
    }

    public String saveArticleAutomatedSearch(ArticleDataDto articleDto, Article.ArticleStatus status) {
        ArticleData article = dataAssembler.createData(articleDto);

        ObjectId id = repository.saveOrUpdate(new Article(article), status);
        return id.toString();

    }

    public void deleteArticle(String id) {
        repository.delete(new ObjectId(id));
    }

    public ArticleDto findArticle(String id) {
        log.debug("Reading article id: " + id);

        Article article = repository.findById(new ObjectId(id));
        List<Article> sharedArticleList = null;
        if (article.getSharedList() != null) {
            sharedArticleList = new ArrayList();
            for (Shared shared : article.getSharedList()) {
                Article sharedArticle = repository.findById(shared.getSharedId());
                sharedArticleList.add(sharedArticle);
            }
        }
        ArticleDto articleDto = assembler.createArticleDto(article, sharedArticleList);
        return articleDto;
    }

    public void updateStatus(String id, Article.ArticleStatus status) {
        Article article = repository.findById(new ObjectId(id));
        if (article != null && !article.getStatus().equals(status)) {
            log.debug("Updating collection for article id: " + id + " from: " + article.getStatus() + " to: " + status);
            article.updateCollection(status);
            repository.updateCollection(article, status);
        }

    }

    public List<DuplicateArticleDto> findDuplicateArticleList() throws ParseException {
        List<DuplicateArticleDto> duplicateArticleList = new ArrayList<>();

        Map<String, String> parameters = new HashMap();
        Page<ArticleDto> articlePage = null;
        for (Article.ArticleStatus articleStatus : Article.ArticleStatus.values()) {
            Integer page = 0;

            if (!articleStatus.equals(Article.ArticleStatus.ALL)) {
                do {

                    parameters.put("page", page.toString());
                    articlePage = this.findArticleList(articleStatus.getStatus(), parameters);
                    for (ArticleDto articleDto : articlePage.getContent()) {
                        Article article = assembler.createArticle(articleDto);
                        Article duplicatedArticle = repository.findArticle(article);
                        if (duplicatedArticle != null) {
                            DuplicateArticleDto duplicate = new DuplicateArticleDto(
                                    article.getData().getTitle(),
                                    duplicatedArticle.getData().getTitle(),
                                    duplicatedArticle.getDistance());
                            duplicateArticleList.add(duplicate);
                        }
                    }
                    page++;

                } while (!articlePage.isLast());
            }
        }
        return duplicateArticleList;
    }

    public void fillDataArticle() {
       /* Boolean update = Boolean.FALSE;
        Integer page = 0;
        Map<String, String> parameters = new HashMap();
        Page<ArticleDto> articlePage = null;

        do {

            parameters.put("page", page.toString());

            articlePage = this.findArticleList(ArticleStatus.ALL.getStatus(), parameters);
            for (ArticleDto article : articlePage.getContent()) {
                try {
                    String pmid = pubMedConnection.findPMIDFromTitle(article.getTitle(), pubmedDB);
                    if (pmid != null) {
                        if (article.getPmid() == null || article.getPmid().isEmpty() && pmid != null) {
                            article.setPmid(pmid);
                            update = Boolean.TRUE;
                            log.debug("Article: " + article.getTitle() + " new pmid: " + pmid);


                        }
                        if (!article.getPmid().equals(pmid)) {
                            log.debug("Article: " + article.getTitle());
                            log.debug("saved pmid: " + article.getPmid() + " found pmid: " + pmid);
                        }
                    }
                    String pmcid = pubMedConnection.findPMIDFromTitle(article.getTitle(), pubmedCentralDB);
                    if (pmcid != null) {

                        if (article.getPmcid() == null || article.getPmcid().isEmpty()) {
                            article.setPmcid(pmcid);
                            update = Boolean.TRUE;
                            log.debug("Article: " + article.getTitle() + " new pmcid: " + pmcid);
                        }
                        if (!article.getPmcid().equals(pmcid)) {
                            log.debug("Article: " + article.getTitle());
                            log.debug("saved pmcid: " + article.getPmcid() + " found pmcid: " + pmid);
                        }
                    }

                    if (article.getPmid() != null) {
                        Article articleNew = pubMedConnection.findArticleFromPMID(article.getPmid(), pubmedDB);
                        if (articleNew != null && articleNew.getDoi() != null) {
                            if (!article.getDoi().equals(articleNew.getDoi())) {
                                log.debug("Article: " + article.getTitle());
                                log.debug("saved doi: " + article.getDoi() + " found doi: " + articleNew.getDoi());
                            }
//                                iDoi++;
//                                article.setDoi(articleNew.getDoi());
//                                update = Boolean.TRUE;
//                                log.debug("Article: " + article.getTitle() + " new doi: " + articleNew.getDoi());

                        }
                    }
                    if (article.getPmcid() != null) {
                        Article articleNew = pubMedConnection.findArticleFromPMID(article.getPmcid(), pubmedCentralDB);
                        if (articleNew != null && articleNew.getDoi() != null) {
                            if (!article.getDoi().equals(articleNew.getDoi())) {
                                log.debug("Article: " + article.getTitle());
                                log.debug("saved doi: " + article.getDoi() + " found doi: " + articleNew.getDoi());
                            }
//                                iDoi++;
//                                article.setDoi(articleNew.getDoi());
//                                log.debug("Article: " + article.getTitle() + " new doi: " + articleNew.getDoi());
//                                update = Boolean.TRUE;
                        }
                    }

                    if (update) {
                        mongoTemplate.save(article, ArticleStatus.getArticleStatus(article.getStatus()).getCollection());
                    }
                } catch (Exception e) {
                    log.error("Exception: ", e);
                }

            }

            page++;

        } while (!articlePage.isLast());
*/
    }
    
    public void update(String id, String field, Object object) {
        Article article = repository.findById(new ObjectId(id));
        if (field.equals("reconstructions")) {
            ObjectMapper mapper = new ObjectMapper();
            ReconstructionsDto pojo = mapper.convertValue(object, ReconstructionsDto.class);
            List<ReconstructionsStatus> currentStatusList = assembler.createCurrentStatusList(pojo);
            article.updateCurrentStatusList(currentStatusList);
            object = article.getReconstructions();
        } else if (field.equals("sharedList")) {
            ObjectMapper mapper = new ObjectMapper();
            List<SharedDto> pojo = mapper.convertValue(object, new TypeReference<ArrayList<SharedDto>>() {});
            List<Shared> sharedList = assembler.createSharedList(pojo);
            object = sharedList;

        } else if (field.equals("data")) {
            ObjectMapper mapper = new ObjectMapper();
            ArticleDataDto pojo = mapper.convertValue(object, ArticleDataDto.class);
            ArticleData data = assembler.createArticleData(pojo);
            object = data;
        } else if (field.equals("searchPortal")) {
            ObjectMapper mapper = new ObjectMapper();
            Search pojo = mapper.convertValue(object, Search.class);
            article.updateSearchPortal(pojo.getSource(), pojo.getKeyWord());
            object = article.getSearchPortal();
        } else if (field.equals("data.dataUsage")) {
            ObjectMapper mapper = new ObjectMapper();
            List pojo = mapper.convertValue(object, List.class);
            List<String> data = assembler.createDataUsage(pojo);
            object = data;
        } else if (field.equals("locked")) {
            ObjectMapper mapper = new ObjectMapper();
            HashMap result = mapper.convertValue(object, HashMap.class);
            object = result.get("locked");

        }
        String collectionName = article.getStatus().getCollection();
        repository.updateArticle(collectionName, new ObjectId(id), field, object);
    }

}
