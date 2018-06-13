package zhibi.cms.mapper;


import zhibi.cms.domain.SysProduct;

public interface SysProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysProduct record);

    int insertSelective(SysProduct record);

    SysProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysProduct record);

    int updateByPrimaryKeyWithBLOBs(SysProduct record);

    int updateByPrimaryKey(SysProduct record);
}