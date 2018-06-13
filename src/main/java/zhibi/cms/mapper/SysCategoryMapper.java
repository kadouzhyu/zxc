package zhibi.cms.mapper;


import zhibi.cms.domain.SysCategory;

public interface SysCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysCategory record);

    int insertSelective(SysCategory record);

    SysCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysCategory record);

    int updateByPrimaryKey(SysCategory record);
}