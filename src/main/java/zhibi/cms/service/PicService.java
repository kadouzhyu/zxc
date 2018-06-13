package zhibi.cms.service;

import com.github.pagehelper.PageInfo;
import zhibi.cms.domain.Pic;
import zhibi.cms.extra.pojo.PicPo;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract interface PicService extends BaseService<Pic>
{
  public abstract PageInfo<PicPo> selectPoByExample(Example paramExample, Page paramPage);

  public abstract PicPo selectBySiteFlagAndFlag(String paramString1, String paramString2);
}