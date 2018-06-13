package zhibi.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zhibi.cms.domain.Article;
import zhibi.cms.extra.pojo.ArticlePo;
import zhibi.cms.service.ArticleService;
import zhibi.commons.response.JsonResult;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;
import zhibi.frame.springmvc.base.AbstractController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping({"cms/article"})
public class CmsArticleController extends AbstractController
{

  @Autowired
  private ArticleService articleService;

  @RequestMapping({"listBySite"})
  public JsonResult<PageInfo<ArticlePo>> listBySite(String site, Page page)
  {
    Example example = Example.getInstance()
      .addParam("s.flag", site)
      .addParam("a.status", 
      Integer.valueOf(1))
      .addOrder("a.sort", ExampleType.OrderType.DESC);

    return JsonResult.success(this.articleService.selectPoByExample(example, page));
  }

  @RequestMapping({"findById"})
  public JsonResult<Article> findById(String id)
  {
    return JsonResult.success(this.articleService.selectByPrimaryKey(id));
  }
}