package zhibi.cms.controller.admin;

import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import zhibi.cms.domain.Article;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.extra.pojo.ArticlePo;
import zhibi.cms.service.ArticleService;
import zhibi.cms.service.SiteService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;
import zhibi.frame.mybatis.example.ExampleType.Operation;
import zhibi.frame.mybatis.example.ExampleType.OrderType;

@Controller
@RequestMapping({"cms/admin/article"})
public class CmsAdminArticleController extends AdminBaseController
{

  @Autowired
  private ArticleService articleService;

  @Autowired
  private SiteService siteService;

  @RequestMapping({"list"})
  public String list(Model model, Page page, ArticlePo articlePo)
  {
    Example example = Example.getInstance()
      .addParam("a.title", articlePo
      .getTitle(), ExampleType.Operation.LIKE)
      .addParam("s.name", articlePo
      .getSiteName(), ExampleType.Operation.LIKE)
      .addOrder("a.sort", ExampleType.OrderType.DESC)
      .addOrder("a.addtime", ExampleType.OrderType.DESC);

    PageInfo pageInfo = this.articleService.selectPoByExample(example, page);
    setModelAttribute(model, new Object[] { pageInfo });
    return "cms/article/list";
  }

  @GetMapping({"add"})
  public String add(Model model)
  {
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    return "cms/article/add";
  }

  @PostMapping({"add"})
  public String add(Article article, MultipartFile file)
  {
    if ((file != null) && (!(file.isEmpty()))) {
      article.setShowpic(saveFile(file, "IMG"));
    }
    article.setStatus(Integer.valueOf(1));
    article.setAddtime(new Date());
    this.articleService.insertSelective(article);
    return redirect("list");
  }

  @GetMapping({"edit/{id}"})
  public String edit(Model model, @PathVariable String id)
  {
    Article article = (Article)this.articleService.selectByPrimaryKey(id);
    if (null == article) throw new MessageException("该文章不存在");
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    model.addAttribute("article", article);
    return "cms/article/edit";
  }

  @PostMapping({"update"})
  public String update(Article article, MultipartFile file)
  {
    if ((file != null) && (!(file.isEmpty()))) {
      article.setShowpic(saveFile(file, "IMG"));
    }
    this.articleService.updateByPrimaryKeySelective(article);
    return refresh();
  }

  @RequestMapping({"del/{id}"})
  public String del(@PathVariable String id)
  {
    Article article = (Article)this.articleService.selectByPrimaryKey(id);
    if (null == article) throw new MessageException("该文章不存在");
    this.articleService.delete(article);
    return refresh();
  }

  @RequestMapping({"updateStatus/{id}"})
  public String updateStatus(@PathVariable String id)
  {
    Article article = (Article)this.articleService.selectByPrimaryKey(id);
    if (null == article) throw new MessageException("该文章不存在");
    article.setStatus(Integer.valueOf((article.getStatus().intValue() + 1) % 2));
    this.articleService.updateByPrimaryKeySelective(article);
    return refresh();
  }
}