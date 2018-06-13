package zhibi.cms.service;

import com.github.pagehelper.PageInfo;
import zhibi.cms.domain.WebPage;
import zhibi.cms.extra.pojo.WebPagePo;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract interface WebPageService extends BaseService<WebPage>
{
  public abstract PageInfo<WebPagePo> selectPoByExample(Example paramExample, Page paramPage);

  public abstract WebPage selectByMenu(String paramString);
}