package zhibi.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhibi.cms.domain.Article;
import zhibi.cms.extra.base.BaseServiceImpl;
import zhibi.cms.extra.pojo.ArticlePo;
import zhibi.cms.mapper.ArticleMapper;
import zhibi.cms.service.ArticleService;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article>
  implements ArticleService
{
  private ArticleMapper articleMapper;

  @Autowired
  public ArticleServiceImpl(ArticleMapper mapper)
  {
    super(mapper);
    this.articleMapper = mapper;
  }

  public PageInfo<ArticlePo> selectPoByExample(Example example, Page page)
  {
    if (null != page) PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.articleMapper.selectPoByExample(example), Page.NAV_SIZE.intValue());
  }
}