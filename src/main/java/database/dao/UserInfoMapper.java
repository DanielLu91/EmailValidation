package database.dao;

import database.pojo.UserInfo;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    Integer selectPrimaryKeyByCode(String code);

    int updateByEmail(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);
}