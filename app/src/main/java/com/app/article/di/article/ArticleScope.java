package com.app.article.di.article;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * scope for the ArticleActivity
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ArticleScope {
}
