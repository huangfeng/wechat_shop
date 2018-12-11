package com.huaibei.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
* @Description:
* @Author:         y
* @CreateDate:     2018-12-10 15:45
* @Version:        1.0
*/
@Entity
@Data
@ToString
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductCategory {
    //类目id
    @Id
    @GeneratedValue
    private Integer categoryId;
    //类目名字
    private  String categoryName;
    //类目编号
    private Integer categoryType;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;


    public ProductCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ProductCategory(String categoryName, Integer categoryType, Date createTime) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.createTime = createTime;
    }

    public ProductCategory(String categoryName, Integer categoryType, Date createTime, Date updateTime) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
