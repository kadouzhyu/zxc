package zhibi.cms.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import zhibi.cms.domain.Pic;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.extra.pojo.PicPo;
import zhibi.cms.service.PicService;
import zhibi.cms.service.SiteService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping({"cms/admin/pic"})
public class CmsAdminPicController extends AdminBaseController
{

  @Autowired
  private PicService picService;

  @Autowired
  private SiteService siteService;

  @RequestMapping({"list"})
  public String list(Model model, Page page, PicPo picPo)
  {
    Example example = Example.getInstance()
      .addParam("p.name", picPo
      .getName(), ExampleType.Operation.LIKE)
      .addParam("s.name", picPo
      .getSiteName(), ExampleType.Operation.LIKE)
      .addOrder("p.sort", ExampleType.OrderType.DESC)
      .addOrder("p.addtime", ExampleType.OrderType.DESC);

    PageInfo pageInfo = this.picService.selectPoByExample(example, page);
    setModelAttribute(model, new Object[] { pageInfo });
    return "cms/pic/list";
  }

  @GetMapping({"add"})
  public String add(Model model)
  {
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    return "cms/pic/add";
  }

  @PostMapping({"add"})
  public String add(Pic pic, MultipartFile file)
  {
    if ((file != null) && (!(file.isEmpty()))) {
      pic.setPath(saveFile(file, "PIC"));
    }
    pic.setStatus(Integer.valueOf(1));
    this.picService.insertSelective(pic);
    return redirect("list");
  }

  @GetMapping({"edit/{id}"})
  public String edit(Model model, @PathVariable String id)
  {
    Pic pic = (Pic)this.picService.selectByPrimaryKey(id);
    if (null == pic) throw new MessageException("该图片不存在");
    List siteList = this.siteService.selectAll();
    model.addAttribute("siteList", siteList);
    model.addAttribute("pic", pic);
    return "cms/pic/edit";
  }

  @PostMapping({"update"})
  public String update(Pic pic, MultipartFile file)
  {
    if ((file != null) && (!(file.isEmpty()))) {
      pic.setPath(saveFile(file, "PIC"));
    }
    this.picService.updateByPrimaryKeySelective(pic);
    return refresh();
  }

  @RequestMapping({"del/{id}"})
  public String del(@PathVariable String id)
  {
    Pic pic = (Pic)this.picService.selectByPrimaryKey(id);
    if (null == pic) throw new MessageException("该图片不存在");
    this.picService.delete(pic);
    return refresh();
  }

  @RequestMapping({"updateStatus/{id}"})
  public String updateStatus(@PathVariable String id)
  {
    Pic pic = (Pic)this.picService.selectByPrimaryKey(id);
    if (null == pic) throw new MessageException("该图片不存在");
    pic.setStatus(Integer.valueOf((pic.getStatus().intValue() + 1) % 2));
    this.picService.updateByPrimaryKeySelective(pic);
    return refresh();
  }
}