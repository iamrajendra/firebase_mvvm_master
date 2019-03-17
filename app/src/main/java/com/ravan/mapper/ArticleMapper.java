package com.ravan.mapper;


import com.ravan.entity.ArticleEntity;
import com.ravan.model.Article;

public class ArticleMapper extends FirebaseMapper<ArticleEntity, Article> {

    @Override
    public Article map(ArticleEntity articleEntity) {
        Article article = new Article();
        article.setPrice(articleEntity.getPrice());
        article.setImageUrl(articleEntity.getImageUrl());
        article.setName(articleEntity.getName());
        return article;
    }
}
