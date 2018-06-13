package zhibi.cms.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import zhibi.cms.domain.Menu;
import zhibi.cms.extra.pojo.MenuPo;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract interface MenuService extends BaseService<Menu>
{
  public abstract PageInfo<MenuPo> selectPoByExample(Example paramExample, Page paramPage);

  public abstract List<Menu> selectByPid(String paramString);

  public abstract Map<String, Object> selectTreeByPid(String paramString);
}