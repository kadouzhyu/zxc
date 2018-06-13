package zhibi.cms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zhibi.cms.domain.User;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.cms.mapper.UserMapper;
import zhibi.commons.exception.MessageException;
import zhibi.utils.security.SecurityUtil;

@Controller
@RequestMapping({"cms/admin"})
public class CmsAdminController extends AdminBaseController
{

  @Autowired
  private UserMapper userMapper;

  @RequestMapping({"index"})
  public String index()
  {
    return "cms/index";
  }

  @RequestMapping({"welcome"})
  public String welcome() {
    return "cms/welcome";
  }

  @GetMapping({"login"})
  public String login() {
    return "cms/login";
  }

  @PostMapping({"login"})
  public String login(String username, String password,Model model) {
    User user1 = new User();
    user1.setUsername(username);
    user1.setPassword(SecurityUtil.MD5(password).toUpperCase());
    user1 = (User)this.userMapper.selectOne(user1);
    if (null == user1){
    	  model.addAttribute("errmsg", "用户名或密码错误");
    	  return "cms/error";
//    	throw new MessageException("用户名或密码错误");
    } 
    this.logger.info("【 登 录 】 用户:{}  ", user1.getUsername());
    this.session.setAttribute("sessionAdmin", user1);
    return redirect("index");
  }

  @RequestMapping({"logout"})
  public String logout()
  {
    this.session.removeAttribute("sessionAdmin");
    return redirect("index");
  }
}