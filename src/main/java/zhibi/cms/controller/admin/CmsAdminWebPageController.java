package zhibi.cms.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zhibi.cms.domain.WebPage;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.extra.pojo.WebPagePo;
import zhibi.cms.service.MenuService;
import zhibi.cms.service.WebPageService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping({"cms/admin/webpage"})
public class CmsAdminWebPageController extends AdminBaseController
{

  @Autowired
  private WebPageService webPageService;

  @Autowired
  private MenuService menuService;

  @RequestMapping({"list"})
  public String list(Model model, Page page, WebPagePo webPagePo)
  {
    Example example = Example.getInstance()
      .addParam("w.title", webPagePo
      .getTitle(), ExampleType.Operation.LIKE)
      .addParam("m.name", webPagePo
      .getMenuName(), ExampleType.Operation.LIKE);
    PageInfo pageInfo = this.webPageService.selectPoByExample(example, page);
    setModelAttribute(model, new Object[] { pageInfo });
    return "cms/webPage/list";
  }

  @GetMapping({"add"})
  public String add(Model model)
  {
    List menuList = this.menuService.selectAll();
    model.addAttribute("menuList", menuList);
    return "cms/webPage/add";
  }

  @PostMapping({"add"})
  public String add(WebPage webPage)
  {
    webPage.setAddtime(new Date());
    this.webPageService.insertSelective(webPage);
    return redirect("list");
  }

  @GetMapping({"edit/{id}"})
  public String edit(Model model, @PathVariable String id)
  {
    WebPage webPage = (WebPage)this.webPageService.selectByPrimaryKey(id);
    if (null == webPage) throw new MessageException("该网页不存在");
    List menuList = this.menuService.selectAll();
    model.addAttribute("menuList", menuList);
    model.addAttribute("webPage", webPage);
    return "cms/webPage/edit";
  }

  @PostMapping({"update"})
  public String update(WebPage webPage)
  {
    this.webPageService.updateByPrimaryKeySelective(webPage);
    return refresh();
  }

  @RequestMapping({"del/{id}"})
  public String del(@PathVariable String id)
  {
    WebPage webPage = (WebPage)this.webPageService.selectByPrimaryKey(id);
    if (null == webPage) throw new MessageException("该网页不存在");
    this.webPageService.delete(webPage);
    return refresh();
  }
}