package zhibi.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhibi.cms.domain.WebPage;
import zhibi.cms.extra.base.BaseServiceImpl;
import zhibi.cms.extra.pojo.WebPagePo;
import zhibi.cms.mapper.WebPageMapper;
import zhibi.cms.service.WebPageService;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;

@Service
public class WebPageServiceImpl extends BaseServiceImpl<WebPage>
  implements WebPageService
{
  private WebPageMapper webPageMapper;

  @Autowired
  public WebPageServiceImpl(WebPageMapper mapper)
  {
    super(mapper);
    this.webPageMapper = mapper;
  }

  public PageInfo<WebPagePo> selectPoByExample(Example example, Page page)
  {
    if (null != page) PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.webPageMapper.selectPoByExample(example), Page.NAV_SIZE.intValue());
  }

  public WebPage selectByMenu(String menuID)
  {
    WebPage webPage = new WebPage();
    webPage.setMenuid(menuID);
    List list = this.webPageMapper.select(webPage);
    if ((list != null) && (list.size() > 0)) return ((WebPage)list.get(0));
    return null;
  }
}