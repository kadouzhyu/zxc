package jubaozhu.zxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
  //首页 哈
  @RequestMapping({"/"})
  public String  index()
  {
    return "index";
  }
}