package zhibi.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhibi.cms.domain.Menu;
import zhibi.cms.extra.base.BaseServiceImpl;
import zhibi.cms.extra.pojo.MenuPo;
import zhibi.cms.mapper.MenuMapper;
import zhibi.cms.service.MenuService;
import zhibi.commons.exception.MessageException;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu>
  implements MenuService
{
  private MenuMapper menuMapper;

  @Autowired
  public MenuServiceImpl(MenuMapper mapper)
  {
    super(mapper);
    this.menuMapper = mapper;
  }

  public PageInfo<MenuPo> selectPoByExample(Example example, Page page)
  {
    if (null != page) PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.menuMapper.selectPoByExample(example), Page.NAV_SIZE.intValue());
  }

  public List<Menu> selectByPid(String pid)
  {
    Menu menu = new Menu();
    menu.setPid(pid);
    return this.menuMapper.select(menu);
  }

  public Map<String, Object> selectTreeByPid(String pid)
  {
    Menu menu = (Menu)this.menuMapper.selectByPrimaryKey(pid);
    if (menu == null) throw new MessageException("该菜单不存在");

    Example example = Example.getInstance()
      .addOrder("sort", ExampleType.OrderType.DESC)
      .addParam("pid", pid)
      .addParam("status", 
      Integer.valueOf(1));

    List menuList = this.menuMapper.selectByExample(example);
    Map map = new HashMap();
    map.put("parent", menu);
    map.put("child", menuList);
    return map;
  }
}