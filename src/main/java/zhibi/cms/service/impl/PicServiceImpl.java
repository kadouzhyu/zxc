package zhibi.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhibi.cms.domain.Pic;
import zhibi.cms.extra.base.BaseServiceImpl;
import zhibi.cms.extra.pojo.PicPo;
import zhibi.cms.mapper.PicMapper;
import zhibi.cms.service.PicService;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.mybatis.example.ExampleType;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PicServiceImpl extends BaseServiceImpl<Pic>
  implements PicService
{
  private PicMapper picMapper;

  @Autowired
  public PicServiceImpl(PicMapper mapper)
  {
    super(mapper);
    this.picMapper = mapper;
  }

  public PageInfo<PicPo> selectPoByExample(Example example, Page page)
  {
    if (null != page) PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.picMapper.selectPoByExample(example), Page.NAV_SIZE.intValue());
  }

  public PicPo selectBySiteFlagAndFlag(String siteFlag, String picFlag)
  {
    PageHelper.startPage(1, 1);

    Example example = Example.getInstance()
      .addParam("s.flag", siteFlag)
      .addParam("p.flag", picFlag)
      .addOrder("p.sort", ExampleType.OrderType.DESC);

    List list = this.picMapper.selectPoByExample(example);
    if ((null == list) || (list.size() == 0)) return null;
    return ((PicPo)list.get(0));
  }
}