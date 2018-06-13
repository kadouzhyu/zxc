package zhibi.cms.mapper;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;
import zhibi.cms.domain.WebPage;
import zhibi.cms.extra.pojo.WebPagePo;
import zhibi.frame.mybatis.example.Example;

public abstract interface WebPageMapper extends Mapper<WebPage>
{
  public abstract List<WebPagePo> selectPoByExample(Example paramExample);
}