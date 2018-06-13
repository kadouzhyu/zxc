package zhibi.cms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_article")
public class Article
  implements Serializable
{

  @Id
  @GeneratedValue(generator="UUID")
  private String id;
  private String title;
  private String content;
  private String descript;
  private String siteid;
  private Date addtime;
  private String showpic;
  private Integer sort;
  private Integer status;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDescript() {
    return this.descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }

  public String getSiteid() {
    return this.siteid;
  }

  public void setSiteid(String siteid) {
    this.siteid = siteid;
  }

  public Date getAddtime() {
    return this.addtime;
  }

  public void setAddtime(Date addtime) {
    this.addtime = addtime;
  }

  public String getShowpic() {
    return this.showpic;
  }

  public void setShowpic(String showpic) {
    this.showpic = showpic;
  }

  public Integer getSort() {
    return this.sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}