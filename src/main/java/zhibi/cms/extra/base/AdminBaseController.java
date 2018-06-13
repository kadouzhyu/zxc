package zhibi.cms.extra.base;

import org.springframework.web.multipart.MultipartFile;

import zhibi.frame.springmvc.base.AbstractController;
import zhibi.utils.io.FileUtils;

public class AdminBaseController extends AbstractController
{
  protected String saveFile(MultipartFile file, String site)
  {
    String ss = FileUtils.saveFile(file, "E:\\picpath\\", site);
    return ss;
  }

  protected String getFilePath()
  {
    return this.request.getServletContext().getRealPath("/WEB-INF/classes/data") + "/";
  }
}