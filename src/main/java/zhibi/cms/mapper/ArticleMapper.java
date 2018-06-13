package zhibi.cms.mapper;

import java.util.List;
import tk.mybatis.mapper.common.Mapper;
import zhibi.cms.domain.Article;
import zhibi.cms.extra.pojo.ArticlePo;
import zhibi.frame.mybatis.example.Example;

public abstract interface ArticleMapper extends Mapper<Article>
{
  public abstract List<ArticlePo> selectPoByExample(Example paramExample);
}