package zhibi.cms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_menu")
public class Menu
  implements Serializable
{

  @Id
  @GeneratedValue(generator="UUID")
  private String id;
  private String name;
  private String url;
  private String flag;
  private String pid;
  private String showpic;
  private Date addtime;
  private Integer sort;
  private Integer status;

  public String getShowpic()
  {
    return this.showpic;
  }

  public void setShowpic(String showpic) {
    this.showpic = showpic;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFlag() {
    return this.flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getPid() {
    return this.pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public Date getAddtime() {
    return this.addtime;
  }

  public void setAddtime(Date addtime) {
    this.addtime = addtime;
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