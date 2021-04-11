#  问题记录
### 1、  Swagger中注解@ApiModelProperty 的坑
        @ApiModelProperty 所注释的实体类属性需要满足驼峰命名法 或者 全部小写
        例如：IDentityVO中的属性 如果携程IDentity或者iDentity 结果在Swagger文档中没有该属性对应的注释