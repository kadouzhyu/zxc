package zhibi.cms.extra.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.common.Mapper;
import zhibi.frame.domain.Page;
import zhibi.frame.mybatis.example.Example;
import zhibi.frame.service.BaseService;

public abstract class BaseServiceImpl<T>
  implements BaseService<T>
{
  protected static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
  private Mapper<T> mapper;

  public BaseServiceImpl(Mapper<T> mapper)
  {
    this.mapper = mapper;
  }

  public T selectOne(T entity) {
    return this.mapper.selectOne(entity);
  }

  public PageInfo<T> selectAll(Page page) {
    PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.mapper.selectAll(), Page.NAV_SIZE.intValue());
  }

  public List<T> selectAll() {
    return this.mapper.selectAll();
  }

  public T selectByPrimaryKey(Object key) {
    return this.mapper.selectByPrimaryKey(key);
  }

  public List<T> select(T entity) {
    return this.mapper.select(entity);
  }

  public PageInfo<T> select(T entity, Page page) {
    PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.mapper.select(entity), Page.NAV_SIZE.intValue());
  }

  public PageInfo<T> selectByExample(Example example, Page page) {
    PageHelper.startPage(page.getPageNum().intValue(), page.getPageSize().intValue());
    return new PageInfo(this.mapper.selectByExample(example), Page.NAV_SIZE.intValue());
  }

  public List<T> selectByExample(Example example) {
    return this.mapper.selectByExample(example);
  }

  public int selectCount(T entity) {
    return this.mapper.selectCount(entity);
  }

  public int delete(T entity) {
    return this.mapper.delete(entity);
  }

  public int deleteByPrimaryKey(Object key) {
    return this.mapper.deleteByPrimaryKey(key);
  }

  public int deleteByPrimaryKeys(List<?> ids)
  {
    int sum = 0;
    for (Iterator localIterator = ids.iterator(); localIterator.hasNext(); ) { Object id = localIterator.next();
      sum += this.mapper.deleteByPrimaryKey(id);
    }
    return sum;
  }

  public int deleteByExample(Example example) {
    return this.mapper.deleteByExample(example);
  }

  public int updateByPrimaryKey(T entity) {
    return this.mapper.updateByPrimaryKey(entity);
  }

  public int updateByExample(T entity, Example example) {
    return this.mapper.updateByExample(entity, example);
  }

  public int updateByPrimaryKeySelective(T entity) {
    return this.mapper.updateByPrimaryKeySelective(entity);
  }

  public int updateByExampleSelective(T entity, Example example) {
    return this.mapper.updateByExampleSelective(entity, example);
  }

  public int insert(T entity) throws Exception {
    return this.mapper.insert(entity);
  }

  public int insertBatch(List<T> list) {
    int sum = 0;
    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object entity = localIterator.next();
      sum += this.mapper.insertSelective((T) entity);
    }
    return sum;
  }

  public int insertSelective(T entity) {
    return this.mapper.insertSelective(entity);
  }

  public int selectCountByExample(Example example) {
    return this.mapper.selectCountByExample(example);
  }
}