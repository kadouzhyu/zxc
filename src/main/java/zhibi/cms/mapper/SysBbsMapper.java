package zhibi.cms.mapper;


import zhibi.cms.domain.SysBbs;

public interface SysBbsMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(SysBbs record);

    int insertSelective(SysBbs record);

    SysBbs selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(SysBbs record);

    int updateByPrimaryKeyWithBLOBs(SysBbs record);

    int updateByPrimaryKey(SysBbs record);
}