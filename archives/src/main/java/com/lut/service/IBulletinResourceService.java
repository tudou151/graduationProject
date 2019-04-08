package com.lut.service;


import com.lut.entity.BulletinResource;
import com.lut.entity.Result;

public interface IBulletinResourceService {
    /**
     * 获取所有的公告资源
     * @return
     */
    public Result getAllBulletinResources();

    /**
     * 根据ID删除公告
     * @param ids
     * @return
     */
    public Result deleteBulletinResource(String ids);

    /**
     * 新增公告
     * @param bulletinResource
     * @return
     */
    public Result addBulletinResource(BulletinResource bulletinResource);

    /**
     * 修改公告
     * @param bulletinResource
     * @return
     */
    public Result modifyBulletinResource(BulletinResource bulletinResource);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    public Result getBulletinById(long id);

    /**
     * @param id
     * @return
     */
    public Result getBulletinResourceById(long id);

    /**
     * @param title
     * @return
     */
    public Result getIdByTitle(String title);
}
