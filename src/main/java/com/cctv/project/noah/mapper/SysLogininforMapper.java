package mapper;

import entity.SysLogininfor;

public interface SysLogininforMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(SysLogininfor record);

    int insertSelective(SysLogininfor record);

    SysLogininfor selectByPrimaryKey(Long infoId);

    int updateByPrimaryKeySelective(SysLogininfor record);

    int updateByPrimaryKey(SysLogininfor record);
}