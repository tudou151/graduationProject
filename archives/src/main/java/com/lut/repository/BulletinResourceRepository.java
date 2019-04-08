package com.lut.repository;

import com.lut.entity.BulletinResource;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface BulletinResourceRepository {
    @Insert("insert into TB_BULLETIN_RESOURCE(ID,CONTENT,CREATE_TIME,START_DATE,END_DATE,FOUNDER,STATE,TITLE)\n" +
            "VALUES(#{id},#{content},#{createTime},#{startDate},#{endDate},#{founder},#{state},#{title})")
    @SelectKey(statement = "select SEQ_BULLETINRESOURCE.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addBulletinResource(BulletinResource bulletinResource);

    @Update("update TB_BULLETIN_RESOURCE set state=#{state} where id=#{id}")
    public int updateState(@Param("state") String state, @Param("id") long id);

    @Select("select * from TB_BULLETIN_RESOURCE")
    @Results({
            @Result(property = "startDate",column = "START_DATE"),@Result(property = "endDate",column = "END_DATE"),
            @Result(property = "createTime",column = "CREATE_TIME"),@Result(property = "modifyTime",column = "MODIFY_TIME")
    })
    public List<BulletinResource> getAllBulletinResources();

    @Delete("delete from TB_BULLETIN_RESOURCE where id=#{id}")
    public int deleteBulletinResource(long id);

    @Update("update TB_BULLETIN_RESOURCE set START_DATE=#{startDate},END_DATE=#{endDate},TITLE=#{title},CONTENT=#{content},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyBulletinResource(BulletinResource bulletinResource);

    @Select("select * from TB_BULLETIN_RESOURCE where id=#{id}")
    @Results({
            @Result(property = "startDate",column = "START_DATE"),
            @Result(property = "endDate",column = "END_DATE"),
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME")
    })
    public BulletinResource getBulletinById(@Param("id") long id);

    @Select("select * from TB_BULLETIN_RESOURCE where id=#{id}")
    @Results({
            @Result(property = "startDate",column = "START_DATE"),
            @Result(property = "endDate",column = "END_DATE"),
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME")
    })
    public List<BulletinResource> getBulletinResourceById(@Param("id") long id);

    @Select("select id from TB_BULLETIN_RESOURCE where title=#{title}")
    public String getIdByTitle(@Param("title") String title);

}