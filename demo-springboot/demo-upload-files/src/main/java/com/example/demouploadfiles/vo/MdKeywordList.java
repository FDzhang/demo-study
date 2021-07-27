package com.example.demouploadfiles.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 关键词清单
 * </p>
 *
 * @author  
 * @since 2021-07-17
 */
@Data
@ApiModel(value="MdKeywordList对象", description="关键词清单")
public class MdKeywordList {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "统计时间")
    private Date statisticDate;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "关键词编码")
    private String keywordCode;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "一级类目编码")
    private String category1Code;

    @ApiModelProperty(value = "一级类目")
    private String category1;

    @ApiModelProperty(value = "二级类目编码")
    private String category2Code;

    @ApiModelProperty(value = "二级类目")
    private String category2;

    @ApiModelProperty(value = "三级类目编码")
    private String category3Code;

    @ApiModelProperty(value = "三级类目")
    private String category3;

    @ApiModelProperty(value = "老NC代码")
    private String ncCodeOld;

    @ApiModelProperty(value = "老NC类目")
    private String ncCategoryOld;

    @ApiModelProperty(value = "新NC代码")
    private String ncCodeNew;

    @ApiModelProperty(value = "新NC类目")
    private String ncCategoryNew;

    @ApiModelProperty(value = "建议零售价（元）（两位小数）")
    private Double retailPrice;

    @ApiModelProperty(value = "收费类型 	固定型，档位型，费率型")
    private String feeType;

    @ApiModelProperty(value = "综合服务费 收费标准（元）（三位小数）")
    private Double integrateServiceFee;

    @ApiModelProperty(value = "配套辅料1 NC编码 	必选")
    private String subMaterialMust1Code;

    @ApiModelProperty(value = "配套辅料1 	必选")
    private String subMaterialMust1;

    @ApiModelProperty(value = "配套辅料1 图样 	必选")
    private String subMaterialMust1Img;

    @ApiModelProperty(value = "配套辅料2 NC编码 	必选")
    private String subMaterialMust2Code;

    @ApiModelProperty(value = "配套辅料2 	必选")
    private String subMaterialMust2;

    @ApiModelProperty(value = "配套辅料2 图样 	必选")
    private String subMaterialMust2Img;

    @ApiModelProperty(value = "配套辅料3 NC编码 	必选")
    private String subMaterialMust3Code;

    @ApiModelProperty(value = "配套辅料3 	必选")
    private String subMaterialMust3;

    @ApiModelProperty(value = "配套辅料3 图样 	必选")
    private String subMaterialMust3Img;

    @ApiModelProperty(value = "配套辅料4 NC编码 	必选")
    private String subMaterialMust4Code;

    @ApiModelProperty(value = "配套辅料4 	必选")
    private String subMaterialMust4;

    @ApiModelProperty(value = "配套辅料4 图样 	必选")
    private String subMaterialMust4Img;

    @ApiModelProperty(value = "配套辅料1 NC编码 	可选")
    private String subMaterialOptional1Code;

    @ApiModelProperty(value = "配套辅料1 	可选")
    private String subMaterialOptional1;

    @ApiModelProperty(value = "配套辅料1 图样 	可选")
    private String subMaterialOptional1Img;

    @ApiModelProperty(value = "配套辅料2 NC编码 	可选")
    private String subMaterialOptional2Code;

    @ApiModelProperty(value = "配套辅料2 	可选")
    private String subMaterialOptional2;

    @ApiModelProperty(value = "配套辅料2 图样 	可选")
    private String subMaterialOptional2Img;

    @ApiModelProperty(value = "商标注册证类目 	商标")
    private String trademarkCategory;

    @ApiModelProperty(value = "注册证编码 	商标")
    private String trademarkCode;

    @ApiModelProperty(value = "商标图样 	商标")
    private String trademarkImg;

    @ApiModelProperty(value = "可授权类目（商标分类） 	商标")
    private String trademarkAuthorizedCategory;

    @ApiModelProperty(value = "合格证1")
    private String certificate1;

    @ApiModelProperty(value = "合格证2")
    private String certificate2;

    @ApiModelProperty(value = "强制性认证资质")
    private String ccc;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建人")
    private Integer creater;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private Integer updater;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;


}
