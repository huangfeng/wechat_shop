package com.huaibei.beans.mapper;

import com.huaibei.beans.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/28 9:55 AM
 * @Version: 1.0
 */
//@Mapper
public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);


    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{a}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

//    @Update("update product_category set category_name = #{param1,jdbcType=VARCHAR} where category_type=#{param2}")
//    int updateByCategoryType( String categoryName, Integer categoryType);
    @Update("update product_category set category_name = #{categoryName,jdbcType=VARCHAR} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName,jdbcType=VARCHAR} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);


    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);


    ProductCategory selectByCategoryType(Integer categoryType);
}
