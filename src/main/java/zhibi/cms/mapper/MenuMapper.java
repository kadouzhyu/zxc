package zhibi.cms.mapper;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;
import zhibi.cms.domain.Menu;
import zhibi.cms.extra.pojo.MenuPo;
import zhibi.frame.mybatis.example.Example;

public abstract interface MenuMapper extends Mapper<Menu>
{
  public abstract List<MenuPo> selectPoByExample(Example paramExample);
}