package zhibi.cms.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_site")
public class Site
  implements Serializable
{

  @Id
  @GeneratedValue(generator="UUID")
  private String id;
  private String name;
  private String flag;
  private String pid;
  private Integer sort;
  private Integer status;

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public Integer getSort() {
    return this.sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }
}