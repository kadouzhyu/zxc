package zhibi.cms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_webpage")
public class WebPage
  implements Serializable
{

  @Id
  @GeneratedValue(generator="UUID")
  private String id;
  private String title;
  private String content;
  private String menuid;
  private Date addtime;

  public Date getAddtime()
  {
    return this.addtime;
  }

  public void setAddtime(Date addtime) {
    this.addtime = addtime;
  }

  public String getId() {
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

  public String getMenuid() {
    return this.menuid;
  }

  public void setMenuid(String menuid) {
    this.menuid = menuid;
  }
}