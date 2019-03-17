package com.ravan.repository;


import com.ravan.mapper.ArticleMapper;
import com.ravan.model.Article;

public class ArticleRepository extends FirebaseDatabaseRepository<Article> {

    public ArticleRepository()
    {
        super(new ArticleMapper());
    }

    @Override
    protected String getRootNode() {
        return "articles";
    }


}
