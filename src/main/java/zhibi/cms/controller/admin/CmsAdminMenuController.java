package zhibi.cms.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import zhibi.cms.domain.Menu;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.extra.pojo.MenuPo;
import zhibi.cms.service.MenuService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping({"cms/admin/menu"})
public class CmsAdminMenuController extends AdminBaseController
{

  @Autowired
  private MenuService menuService;

  @RequestMapping({"list"})
  public String list(Model model, Page page, MenuPo menuPo)
  {
    Example example = Example.getInstance()
      .addParam("m.name", menuPo
      .getName(), ExampleType.Operation.LIKE)
      .addOrder("m.sort", ExampleType.OrderType.DESC)
      .addOrder("m.pid", ExampleType.OrderType.DESC);

    PageInfo pageInfo = this.menuService.selectPoByExample(example, page);
    setModelAttribute(model, new Object[] { pageInfo });
    return "cms/menu/list";
  }

  @GetMapping({"add"})
  public String add(Model model)
  {
    List menuList = this.menuService.selectAll();
    model.addAttribute("menuList", menuList);
    return "cms/menu/add";
  }

  @PostMapping({"add"})
  public String add(Menu menu, MultipartFile file)
  {
    if ((menu.getPid() != null) && (!(Objects.equals(menu.getPid(), "")))) {
      Menu pMenu = (Menu)this.menuService.selectByPrimaryKey(menu.getPid());
      if (null == pMenu) throw new MessageException("上级菜单不存在");
    }
    if ((file != null) && (!(file.isEmpty()))) {
      menu.setShowpic(saveFile(file, "MENU"));
    }
    menu.setStatus(Integer.valueOf(1));
    menu.setAddtime(new Date());
    this.menuService.insertSelective(menu);
    return redirect("list");
  }

  @GetMapping({"edit/{id}"})
  public String edit(Model model, @PathVariable String id)
  {
    Menu menu = (Menu)this.menuService.selectByPrimaryKey(id);
    if (null == menu) throw new MessageException("该菜单不存在");
    List menuList = this.menuService.selectAll();
    model.addAttribute("menuList", menuList);
    model.addAttribute("menu", menu);
    return "cms/menu/edit";
  }

  @PostMapping({"update"})
  public String update(Menu menu, MultipartFile file)
  {
    if ((menu.getPid() != null) && (!(Objects.equals(menu.getPid(), "")))) {
      Menu pMenu = (Menu)this.menuService.selectByPrimaryKey(menu.getPid());
      if (null == pMenu) throw new MessageException("上级菜单不存在");
    }
    if ((file != null) && (!(file.isEmpty()))) {
      menu.setShowpic(saveFile(file, "MENU"));
    }
    this.menuService.updateByPrimaryKeySelective(menu);
    return refresh();
  }

  @RequestMapping({"del/{id}"})
  public String del(@PathVariable String id)
  {
    Menu menu = (Menu)this.menuService.selectByPrimaryKey(id);
    if (null == menu) throw new MessageException("该菜单不存在");
    List menuList = this.menuService.selectByPid(menu.getId());
    if ((menuList != null) && (menuList.size() > 0)) throw new MessageException("该菜单下面存在子菜单，不能删除");
    this.menuService.delete(menu);
    return refresh();
  }

  @RequestMapping({"updateStatus/{id}"})
  public String updateStatus(@PathVariable String id)
  {
    Menu menu = (Menu)this.menuService.selectByPrimaryKey(id);
    if (null == menu) throw new MessageException("该菜单不存在");
    menu.setStatus(Integer.valueOf((menu.getStatus().intValue() + 1) % 2));
    this.menuService.updateByPrimaryKeySelective(menu);
    return refresh();
  }
}