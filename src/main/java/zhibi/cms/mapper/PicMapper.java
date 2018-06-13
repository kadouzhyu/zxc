package zhibi.cms.mapper;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;
import zhibi.cms.domain.Pic;
import zhibi.cms.extra.pojo.PicPo;
import zhibi.frame.mybatis.example.Example;

public abstract interface PicMapper extends Mapper<Pic>
{
  public abstract List<PicPo> selectPoByExample(Example paramExample);
}