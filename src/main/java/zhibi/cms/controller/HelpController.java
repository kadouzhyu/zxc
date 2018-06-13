package zhibi.cms.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import zhibi.cms.extra.base.AdminBaseController;
import zhibi.utils.JSONUtils;

@Controller
public class HelpController extends AdminBaseController
{
  @RequestMapping({"file/uploadFile"})
  public void upload(HttpServletResponse response, @RequestParam MultipartFile[] imgFile)
  {
    Map map = new HashMap();
    map.put("error", Integer.valueOf(1));
    for (MultipartFile item : imgFile)
      if (item.isEmpty()) {
        map.put("message", "请选择文件");
        try {
          response.getOutputStream().write(JSONUtils.objToJson(map).getBytes());
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        try {
          String path = saveFile(item, "image");
          map.put("error", Integer.valueOf(0));
          map.put("url", path);
          System.out.println(JSONUtils.objToJson(map));
          response.getOutputStream().write(JSONUtils.objToJson(map).getBytes());
        } catch (Exception e) {
          e.printStackTrace();
          map.put("message", e.getMessage());
          try {
            response.getOutputStream().write(JSONUtils.objToJson(map).getBytes());
          }
          catch (IOException localIOException1)
          {
          }
        }
      }
  }
}