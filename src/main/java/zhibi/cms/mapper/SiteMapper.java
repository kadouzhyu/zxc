package zhibi.cms.mapper;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;
import zhibi.cms.domain.Site;
import zhibi.cms.extra.pojo.SitePo;
import zhibi.frame.mybatis.example.Example;

public abstract interface SiteMapper extends Mapper<Site>
{
  public abstract List<SitePo> selectPoByExample(Example paramExample);
}