package zhibi.cms.mapper;


import zhibi.cms.domain.SysWebsite;

public interface SysWebsiteMapper {
    int deleteByPrimaryKey(String siteid);

    int insert(SysWebsite record);

    int insertSelective(SysWebsite record);

    SysWebsite selectByPrimaryKey(String siteid);

    int updateByPrimaryKeySelective(SysWebsite record);

    int updateByPrimaryKeyWithBLOBs(SysWebsite record);

    int updateByPrimaryKey(SysWebsite record);
}