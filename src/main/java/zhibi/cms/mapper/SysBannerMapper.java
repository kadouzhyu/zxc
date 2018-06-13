package zhibi.cms.mapper;


import zhibi.cms.domain.SysBanner;

public interface SysBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBanner record);

    int insertSelective(SysBanner record);

    SysBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysBanner record);

    int updateByPrimaryKeyWithBLOBs(SysBanner record);

    int updateByPrimaryKey(SysBanner record);
}