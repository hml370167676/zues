#  问题记录
### 1、  Swagger中注解@ApiModelProperty 的坑
        @ApiModelProperty 所注释的实体类属性需要满足驼峰命名法 或者 全部小写
        例如：IDentityVO中的属性 如果携程IDentity或者iDentity 结果在Swagger文档中没有该属性对应的注释
### 2、 转换的坑 BankCardInfoImpl.java 86行
        charAt() 获取的结果为Char类型 如果直接转换为int类型，实际取值并非将char存贮的字符转换为int类型
        而是直接获取 ASCII字符编码表中 char存贮字符对应的数值
        例如：Char c = "12345".charAt(3);  此时取值为Char c='4',52  52为ASCII表中 4对应的十进制编码
        如果此时直接将c转为 int类型 int i = c; 此时 i为52