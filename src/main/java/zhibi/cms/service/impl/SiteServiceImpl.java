package zhibi.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhibi.cms.domain.Site;
import zhibi.cms.extra.base.BaseServiceImpl;
import zhibi.cms.extra.pojo.SitePo;
import zhibi.cms.mapper.SiteMapper;
import zhibi.cms.service.SiteService;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;

@Service
public class SiteServiceImpl extends BaseServiceImpl<Site>
  implements SiteService
{
  private SiteMapper siteMapper;

  @Autowired
  public SiteServiceImpl(SiteMapper mapper)
  {
    super(mapper);
    this.siteMapper = mapper;
  }

  public PageInfo<SitePo> selectPoByExample(Example example, Page page)
  {
    if (null != page) PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.siteMapper.selectPoByExample(example), Page.NAV_SIZE.intValue());
  }

  public List<Site> selectByPid(String pid)
  {
    Site site = new Site();
    site.setPid(pid);
    return this.siteMapper.select(site);
  }
}