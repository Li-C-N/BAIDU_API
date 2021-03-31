package com.ljd.ai.mapper;

import com.ljd.ai.model.ImageInfo;
import com.ljd.ai.model.ImageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    long countByExample(ImageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int deleteByExample(ImageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int deleteByPrimaryKey(Integer iId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int insert(ImageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int insertSelective(ImageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    List<ImageInfo> selectByExampleWithRowbounds(ImageInfoExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    List<ImageInfo> selectByExample(ImageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    ImageInfo selectByPrimaryKey(Integer iId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int updateByExampleSelective(@Param("record") ImageInfo record, @Param("example") ImageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int updateByExample(@Param("record") ImageInfo record, @Param("example") ImageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int updateByPrimaryKeySelective(ImageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IMAGE_INFO
     *
     * @mbg.generated Tue Mar 30 17:57:45 CST 2021
     */
    int updateByPrimaryKey(ImageInfo record);
}