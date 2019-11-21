package com.order.service.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.R;
import com.order.entity.ArticleEntity;
import com.order.service.ArticleService;

/**
* @Description: TODO
* @author LGC
* @date 2019年4月26日
* @version V1.0
*/
@Service(value = "articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private EntityManager em;
	
	@Override
	public R AddArticle(String title, String content) {
		ArticleEntity article = new ArticleEntity();
		article.setTitle(title);
		article.setContent(content);
		article.setCreateTime(new Date());
		em.persist(article);
		return R.ok();
	}

}
