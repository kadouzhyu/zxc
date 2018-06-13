package zhibi.cms.service;

import com.github.pagehelper.PageInfo;
import java.util.List;
import zhibi.cms.domain.Site;
import zhibi.cms.extra.pojo.SitePo;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract interface SiteService extends BaseService<Site>
{
  public abstract PageInfo<SitePo> selectPoByExample(Example paramExample, Page paramPage);

  public abstract List<Site> selectByPid(String paramString);
}