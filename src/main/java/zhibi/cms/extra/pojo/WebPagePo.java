package zhibi.cms.extra.pojo;

import zhibi.cms.domain.WebPage;

public class WebPagePo extends WebPage
{
  private String menuName;

  public String getMenuName()
  {
    return this.menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }
}