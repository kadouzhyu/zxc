package zhibi.cms.service;

import com.github.pagehelper.PageInfo;
import zhibi.cms.domain.Article;
import zhibi.cms.extra.pojo.ArticlePo;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract interface ArticleService extends BaseService<Article>
{
  public abstract PageInfo<ArticlePo> selectPoByExample(Example paramExample, Page paramPage);
}