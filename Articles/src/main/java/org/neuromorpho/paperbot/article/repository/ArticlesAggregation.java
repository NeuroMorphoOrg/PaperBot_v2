package org.neuromorpho.paperbot.article.repository;

import org.neuromorpho.paperbot.article.model.article.Article;

import java.util.List;

public class ArticlesAggregation {
    
    private List<Article> paginatedResults;
    private List<Count> count;

    public ArticlesAggregation() {
    }

    public List<Article> getPaginatedResults() {
        return paginatedResults;
    }

    public void setPaginatedResults(List<Article> paginatedResults) {
        this.paginatedResults = paginatedResults;
    }

    public List<Count> getCount() {
        return count;
    }

    public void setCount(List<Count> count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ArticlesAggregation{" +
                "paginatedResults=" + paginatedResults +
                ", count=" + count +
                '}';
    }
}
