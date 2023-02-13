package com.scaler.blogapi.comments;

import com.scaler.blogapi.articles.ArticleEntity;
import com.scaler.blogapi.commons.BaseEntity;
import com.scaler.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    String title;
    @Column(length = 1000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;

}
