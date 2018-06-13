package zhibi.cms.controller.admin;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zhibi.cms.domain.Site;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.extra.pojo.SitePo;
import zhibi.cms.service.SiteService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping({"cms/admin/site"})
public class CmsAdminSiteController extends AdminBaseController
{

  @Autowired
  private SiteService siteService;

  @RequestMapping({"list"})
  public String list(Model model, Page page, SitePo sitePo)
  {
    Example example = Example.getInstance()
      .addParam("s.name", sitePo
      .getName(), ExampleType.Operation.LIKE)
      .addOrder("s.sort", ExampleType.OrderType.DESC)
      .addOrder("s.pid", ExampleType.OrderType.DESC);

    PageInfo pageInfo = this.siteService.selectPoByExample(example, page);
    setModelAttribute(model, new Object[] { pageInfo });
    return "cms/site/list";
  }

  @GetMapping({"add"})
  public String add(Model model)
  {
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    return "cms/site/add";
  }

  @PostMapping({"add"})
  public String add(Site site)
  {
    if ((site.getPid() != null) && (!(Objects.equals(site.getPid(), "")))) {
      Site pSite = (Site)this.siteService.selectByPrimaryKey(site.getPid());
      if (null == pSite) throw new MessageException("上级不存在");
    }
    site.setStatus(Integer.valueOf(1));
    this.siteService.insertSelective(site);
    return redirect("list");
  }

  @GetMapping({"edit/{id}"})
  public String edit(Model model, @PathVariable String id)
  {
    Site site = (Site)this.siteService.selectByPrimaryKey(id);
    if (null == site) throw new MessageException("该栏目不存在");
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    model.addAttribute("site", site);
    return "cms/site/edit";
  }

  @PostMapping({"update"})
  public String update(Site site)
  {
    if ((site.getPid() != null) && (!(Objects.equals(site.getPid(), "")))) {
      Site pSite = (Site)this.siteService.selectByPrimaryKey(site.getPid());
      if (null == pSite) throw new MessageException("上级栏目不存在");
    }
    this.siteService.updateByPrimaryKeySelective(site);
    return refresh();
  }

  @RequestMapping({"del/{id}"})
  public String del(@PathVariable String id)
  {
    Site site = (Site)this.siteService.selectByPrimaryKey(id);
    if (null == site) throw new MessageException("该栏目不存在");
    List siteList = this.siteService.selectByPid(site.getId());
    if ((siteList != null) && (siteList.size() > 0)) throw new MessageException("该栏目下面存在子菜单，不能删除");
    this.siteService.delete(site);
    return refresh();
  }

  @RequestMapping({"updateStatus/{id}"})
  public String updateStatus(@PathVariable String id)
  {
    Site site = (Site)this.siteService.selectByPrimaryKey(id);
    if (null == site) throw new MessageException("该栏目不存在");
    site.setStatus(Integer.valueOf((site.getStatus().intValue() + 1) % 2));
    this.siteService.updateByPrimaryKeySelective(site);
    return refresh();
  }
}