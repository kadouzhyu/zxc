package zhibi.cms.extra.pojo;

import zhibi.cms.domain.Pic;

public class PicPo extends Pic
{
  private String siteName;

  public String getSiteName()
  {
    return this.siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }
}