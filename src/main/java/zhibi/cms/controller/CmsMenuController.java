package zhibi.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zhibi.cms.domain.Menu;
import zhibi.cms.service.MenuService;
import zhibi.commons.exception.MessageException;
import zhibi.commons.response.JsonResult;
import zhibi.commons.tree.Tree;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;
import zhibi.frame.springmvc.base.AbstractController;
import zhibi.utils.StringUtils;

@RestController
@RequestMapping({"cms/menu"})
public class CmsMenuController extends AbstractController
{

  @Autowired
  private MenuService menuService;

  @RequestMapping({"allMenu"})
  public JsonResult<List<Tree<Menu>>> allMenu()
  {
    Example example = Example.getInstance().addOrder("sort", ExampleType.OrderType.DESC)
      .addParam("status", 
      Integer.valueOf(1));

    List menuList = this.menuService.selectByExample(example);

    List<Tree<Menu>> list = listToTree(menuList, "");
    return JsonResult.success(list);
  }

  @RequestMapping({"pageMenu"})
  public JsonResult<Map<String, Object>> pageMenu(String menuPId, String menuId)
  {
    if (StringUtils.isEmpty(menuPId)) {
      Menu menu = (Menu)this.menuService.selectByPrimaryKey(menuId);
      if (menu == null) throw new MessageException("该菜单不存在");
      menuPId = menu.getPid();
    }
    Map<String, Object> map = this.menuService.selectTreeByPid(menuPId);
    return JsonResult.success(map);
  }

  private static List<Tree<Menu>> listToTree(List<Menu> menuList, String pId)
  {
	  List<Tree<Menu>> list = new ArrayList<Tree<Menu>>();
    for (Menu menu : menuList) {
      if (pId.equals(menu.getPid())) {
        Tree child = new Tree(menu);
        list.add(child);
        List copyList = new ArrayList(menuList);
        copyList.remove(menu);
        List treeList = listToTree(menuList, menu.getId());
        child.addChild(treeList);
      }
    }
    return list;
  }
}