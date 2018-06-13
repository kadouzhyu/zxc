package zhibi.cms.extra.pojo;

import zhibi.cms.domain.Article;

public class ArticlePo extends Article
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