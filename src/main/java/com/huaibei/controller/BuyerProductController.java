package com.huaibei.controller;

import com.huaibei.VO.ProductInfoVO;
import com.huaibei.VO.ProductVO;
import com.huaibei.VO.ResultVO;
import com.huaibei.beans.ProductCategory;
import com.huaibei.beans.ProductInfo;
import com.huaibei.service.ProductCategoryService;
import com.huaibei.service.ProductInfoService;
import com.huaibei.utils.ResultVOUtil;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/list")
//    @Cacheable(cacheNames = "product",key = "100")
//    @Cacheable(cacheNames = "test",key = "#sellerId",condition = "#sellerId.length()>5",unless = "#result.getCode()!=0")
    public ResultVO list(){
        //1 查询所有的上架商品

        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2 查询类目（一次性查询）
        //传统方法
//        List<Integer> categoryType = new ArrayList<>();
//        for (ProductInfo productInfo : productInfoList){
//            categoryType.add(productInfo.getCategoryType());
//        }
        //精简方法（java8 ， lambda）
        List<Integer> categoryType = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryType);
        //3 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setCategoryFoods(productInfoVOList);
            productVOList.add(productVO);
        }




        return ResultVOUtil.success(productVOList);
    }
}
