package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.R;
import com.order.service.ArticleService;

/**
* @Description: TODO
* @author LGC
* @date 2019年4月26日
* @version V1.0
*/
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public R addArticle(String title, String content) {
		return articleService.AddArticle(title, content);
	}
}
