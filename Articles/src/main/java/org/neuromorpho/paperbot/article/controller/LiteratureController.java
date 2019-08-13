package org.neuromorpho.paperbot.article.controller;

import org.bson.types.ObjectId;
import org.neuromorpho.paperbot.article.model.article.Article;
import org.neuromorpho.paperbot.article.service.LiteratureService;
import org.neuromorpho.paperbot.article.service.dto.ArticleDataDto;
import org.neuromorpho.paperbot.article.exceptions.DuplicatedException;
import org.neuromorpho.paperbot.article.service.dto.ArticleDto;
import org.neuromorpho.paperbot.article.service.dto.DuplicateArticleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class LiteratureController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LiteratureService literatureService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home() {
        return "Literature up & running!";
    }

    @CrossOrigin
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Map<String, Long> getSummary(
            @RequestParam(required = false) String date) {
        return literatureService.getSummary(date);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ArticleDto findArticle(@PathVariable String id) {
        return literatureService.findArticle(id);
    }

    
    @CrossOrigin
    @RequestMapping(value = "status/{status}", method = RequestMethod.GET)
    public Page<ArticleDto> findArticleList(
            @PathVariable String status,
            @RequestParam(required = false) Map<String, String> queryParams) throws ParseException {
        log.debug("Find articles by text status : " + status + " and page: " + queryParams.get("page"));
        return literatureService.findArticleList(status, queryParams);
       
    }

    /*
     * Save manual article
     */
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveArticleManual(
            @RequestBody ArticleDto articleDto) {
        literatureService.saveArticleManual(articleDto);
    }

    /*
     * Save article for search
     */
    @CrossOrigin
    @RequestMapping(value = "/{status}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto saveArticleAutomatedSearch(
            @PathVariable String status,
            @RequestBody ArticleDataDto article) {
        String id = literatureService.saveArticleAutomatedSearch(article, Article.ArticleStatus.getArticleStatus(status));
        return new ArticleDto(id);
    }

    @CrossOrigin
    @RequestMapping(value = "objectId", method = RequestMethod.GET)
    public @ResponseBody
    IdDto getNewObjectId() {
        return new IdDto(new ObjectId());
    }

    @ExceptionHandler(DuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody
    Map<String, Object> handleDuplicatedException(DuplicatedException e,
                                                  HttpServletRequest request,
                                                  HttpServletResponse resp) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("errorMessage", e.getMessage());
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCollection(
            @PathVariable String id,
            @RequestParam String newArticleStatus) {
        literatureService.updateStatus(id,
                Article.ArticleStatus.getArticleStatus(newArticleStatus));
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable String id) {
        literatureService.deleteArticle(id);

    }

    @CrossOrigin
    @RequestMapping(value = "/duplicates", method = RequestMethod.GET)
    public List<DuplicateArticleDto> findDuplicateArticleList() throws ParseException {
        log.debug("Find duplicate articles by approximate title");
        return literatureService.findDuplicateArticleList();
    }

    @CrossOrigin
    @RequestMapping(value = "/fillData", method = RequestMethod.GET)
    public void fillDataArticle() {
        log.debug("Fill data for all articles");
        literatureService.fillDataArticle();
    }
    

    @CrossOrigin
    @RequestMapping(value = "/{id}/{field}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(
            @PathVariable String id,
            @PathVariable String field,
            @RequestBody Object object) {
        literatureService.update(id, field, object);
    }

}
