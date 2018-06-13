package zhibi.cms.extra.pojo;

import zhibi.cms.domain.Site;

public class SitePo extends Site
{
  private String pName;

  public String getpName()
  {
    return this.pName;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }
}