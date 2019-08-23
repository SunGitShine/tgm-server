package com.juma.tgm.imageUploadManage.dao;

import com.giants.dal.dao.mybatis.MybatisDao;
import com.juma.tgm.imageUploadManage.domain.ImageUploadManage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/*
 * 
 * @author  2017-07-10
 * @version 1.0 
 */

public interface ImageUploadManageDao extends MybatisDao<ImageUploadManage> {

    void deleteBy(ImageUploadManage imageUploadManage);

    @Delete("delete from image_upload_manage WHERE image_upload_manage_id = #{id}")
    void deleteById(@Param("id") int id);
}
