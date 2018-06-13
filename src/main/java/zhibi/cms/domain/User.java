package zhibi.cms.domain;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cms_user")
public class User
  implements Serializable
{

  @Id
  private Integer id;
  private String username;
  private String password;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}