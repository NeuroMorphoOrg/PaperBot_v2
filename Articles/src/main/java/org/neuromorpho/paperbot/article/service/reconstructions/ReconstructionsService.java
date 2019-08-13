package org.neuromorpho.paperbot.article.service.reconstructions;

import org.neuromorpho.paperbot.article.model.article.ReconstructionsStatus;
import org.neuromorpho.paperbot.article.repository.ArticleRepository;
import org.neuromorpho.paperbot.article.repository.ArticleSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReconstructionsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
   
    @Autowired
    private ArticleRepository repository;
   
    public Page<ReconstructionsStatus> findReconstructionStatusBySpecificDetails(
            List<ReconstructionsStatus.SpecificDetails> specificDetails, Date expirationDate, Integer page) {
        return null;
       // return reconstructionsRepositoryExtended.findByCurrentStatusListSpecificDetails(
        //         specificDetails, expirationDate, page);
    }

    public List<String> findSpecificDetailsValues() {
        List<String> values = new ArrayList();
        for (ReconstructionsStatus.SpecificDetails details : ReconstructionsStatus.SpecificDetails.values()) {
            values.add(details.getDetails());
        }
        return values;
    }
    

    public List<ArticleSummary> getSummaryReconstructions(Boolean expired) {
        List<ArticleSummary> sharedArticleSummaryList = repository.getSummaryShared();
        List<ArticleSummary> articleSummaryList = repository.getSummary(expired);
        for (ArticleSummary articleSummary: articleSummaryList){
            ArticleSummary sharedArticleSummary = sharedArticleSummaryList.stream().filter(o -> o.getSpecificDetails().equals(articleSummary.getSpecificDetails())).findFirst().orElse(null);
            Double sharedArticles = 0D;
            if (sharedArticleSummary != null) {
                sharedArticles = sharedArticleSummary.getnSharedArticles();
            }
            articleSummary.setnSharedArticles(sharedArticles);
        }
        return articleSummaryList;
    }
    
}
