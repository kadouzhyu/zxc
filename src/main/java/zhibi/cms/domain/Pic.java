package zhibi.cms.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_pic")
public class Pic
  implements Serializable
{

  @Id
  @GeneratedValue(generator="UUID")
  private String id;
  private String name;
  private String path;
  private String siteid;
  private Integer sort;
  private Integer status;
  private String url;
  private String flag;

  public String getFlag()
  {
    return this.flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getSiteid() {
    return this.siteid;
  }

  public void setSiteid(String siteid) {
    this.siteid = siteid;
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