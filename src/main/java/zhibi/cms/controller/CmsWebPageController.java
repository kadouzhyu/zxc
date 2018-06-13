package zhibi.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhibi.cms.domain.WebPage;
import zhibi.cms.service.WebPageService;
import zhibi.commons.response.JsonResult;
import zhibi.frame.springmvc.base.AbstractController;

@RestController
@RequestMapping({"cms/webPage"})
public class CmsWebPageController extends AbstractController
{

  @Autowired
  private WebPageService webPageService;

  @RequestMapping({"menuPage"})
  public JsonResult<WebPage> menuPage(String menuId)
  {
    WebPage webPage = this.webPageService.selectByMenu(menuId);
    return JsonResult.success(webPage);
  }
}