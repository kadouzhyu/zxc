package zhibi.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhibi.cms.extra.pojo.PicPo;
import zhibi.cms.service.PicService;
import zhibi.commons.response.JsonResult;
import zhibi.frame.springmvc.base.AbstractController;

@RestController
@RequestMapping({"cms/pic"})
public class CmsPicController extends AbstractController
{

  @Autowired
  private PicService picService;

  @RequestMapping({"siteFlag"})
  public JsonResult<PicPo> siteFlag(String siteFlag, String picFlag)
  {
    PicPo picPo = this.picService.selectBySiteFlagAndFlag(siteFlag, picFlag);
    return JsonResult.success(picPo);
  }
}