package com.hml.atp.zues.common;

/**
 * @Author ：GWJ
 * @Description ：
 * @Date ：Created in 2020-04-02
 */
public class MQConsumerConstant {

    //trs topic交换器名称
    public static final String TRS_EXCHANGE_NAME = "trs_exchange_name";

    //大大云报价队列
    public static final String ADMINCENTER_QUOTATION_CHANGE_QUEUE_NAME = "admincenter_quotation_change_queue_name";

    /**
     * 立即报价 , 修改报价 启动异常单监控 队列名
     */
    public static final String TRS_ABNORMAL_QUOTATION_WARNING = "trs_abnormal_quotation_warning";

    //item 节点询价消息接收队列
    public static final String ITEM_INQUIRY_ROUTING_CREATE = "item_inquiry_routing_create";

}
