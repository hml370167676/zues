package com.hml.atp.zues.config;

import com.hml.atp.zues.common.MQConsumerConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@ConditionalOnProperty(name = "spring.rabbitmq.addresses")
public class MQSenderConfig implements RabbitListenerConfigurer {


    public static final String EXCHANGE_DELAY = "dgmq-exchange-delay";


    /**
     * 死信交换机
     */
    public static final String TRS_DEAD_LETTER_EXCHANGE = "trs.dead.letter.exchange";

    /**
     * 死信routingkey
     */
    public static final String TRS_DEAD_LETTER_QUEUE_ROUTING_KEY = "trs.dead.letter.routingkey";

    /**
     * 死信队列
     */
    public static final String TRS_DEAD_LETTER_QUEUE_NAME = "trs.dead.letter.queue";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Exchange delayExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_DELAY).durable(true).delayed()
                .withArgument("x-delayed-type", "direct").build();
    }


    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    /**
     * 已报全
     */
    public static final String ORDER_INQUIRY_ALL_QUOTE_30M = "order_inquiry_all_quote_30m";

    /**
     * 采购单已采购未支付可以完成
     */
    public static final String ORDER_PURCHASE_TASK_ORDER_COMPLETE = "order_purchase_task_order_complete";

    /**
     * 采购单创建24小时自动取消订单队列
     */
    public static final String ORDER_ORDER_QUEUE_PURCHASE_CREATED_24H = "order_order_queue_purchase_created_24h";
    /**
     * 采购单超时未付款-提醒用户支付
     */
    public static final String ORDER_ORDER_QUEUE_PURCHASE_CREATED_22H = "order_order_queue_purchase_created_22h";

    /**
     * 询价单超时10分钟无报价时通知运营客户
     */
    public static final String TRS_QUEUE_INQUIRY_10M = "trs_queue_inquiry_10m";

    /**
     * 采购单创建24小时自动取消订单队列
     */
    public static final String TRS_QUEUE_PURCHASE_CREATED_24H = "trs_queue_purchase_created_24h";
    /**
     * 采购单超时未付款-提醒用户支付
     */
    public static final String TRS_QUEUE_PURCHASE_CREATED_22H = "trs_queue_purchase_created_22h";


    //以下是采购单的队列
    /**
     * 采购单-付款提醒
     */
    public static final String ORDER_USER_QUEUE_PURCHASE_PAY_REMINDER = "order_user_queue_purchase_pay_reminder";

    /**
     * 采购单-发货通知
     */
    public static final String ORDER_USER_QUEUE_PURCHASE_DELIVERY_REMINDER = "order_user_queue_purchase_delivery_reminder";

    /**
     * 采购单-收货通知
     */
    public static final String ORDER_USER_QUEUE_PURCHASE_RECEIVING_REMINDER = "order_user_queue_purchase_receiving_reminder";

    //以下是报价单的队列

    /**
     * 新报价单通知
     */
    public static final String ORDER_USER_QUEUE_CREATE_QUOTATION_REMINDER = "order_user_queue_create_quotation_reminder";


    //以下是云通讯-IM的队列
    /**
     * 新建询价单通知商家报价（im）
     */
    public static final String ORDER_USER_QUEUE_CREATE_INQUIRY_CLOUD_TENCENT = "order_user_queue_create_inquiry_cloud_tencent";
    /**
     * 新建报价单通知买家下单采购（im）
     */
    public static final String ORDER_USER_QUEUE_CREATE_QUOTATION_CLOUD_TENCENT = "order_user_queue_create_quotation_cloud_tencent";

    /**
     * 支付采购单通知商家发货（im）
     */
    public static final String ORDER_USER_QUEUE_PAY_PURCHASE_CLOUD_TENCENT = "order_user_queue_pay_purchase_cloud_tencent";

    /**
     * 采购单发货通知商家（im）
     */
    public static final String ORDER_USER_QUEUE_DELIVERY_CLOUD_TENCENT = "order_user_queue_delivery_cloud_tencent";

    /**
     * 新建询价单 现货秒报（im）
     */
    public static final String ORDER_USER_QUEUE_AUTO_QUOTATION_CLOUD_TENCENT = "order_user_queue_auto_quotation_cloud_tencent";

    /**
     * 询价单超时10分钟无报价时通知运营客户
     */
    public static final String ORDER_ORDER_QUEUE_INQUIRY_10M = "order_order_queue_inquiry_10m";

    /**
     * 询价单创建五分钟后
     */
    public static final String QUEUE_INQUIRY_CREATED_5M = "dgmq_inquiry_created_5m";


    public static final String QUEUE_INQUIRY_TEMPLATE_MESSAGE = "queue_inquiry_template_message";

    /**
     * 未指定到期询价单创建后48小时过期
     */
    public static final String QUEUE_INQUIRY_CREATED_EXPIRE_48H = "dgmq_inquiry_created_expire_48h";

    /**
     * 门店积分增加消息队列
     */
    public static final String QUEUE_INTEGRAL_INCREASE = "queue_integral_increase";

    /**
     * 采购确认收货
     */
    public static final String QUEUE_PURCHASE_RECEIVE = "queue_purchase_receive";

    public static final String QUEUE_PURCHASE_DELIVER_TEMPLATE_MESSAGE = "queue_purchase_deliver_template_message";

    /**
     * 支付结果提醒
     */
    public static final String QUEUE_PAY_TRS_RESULT_REMINDER = "queue_pay_trs_result_reminder";

    /**
     * 充值结果通知
     */
    public static final String PAY_TRS_QUEUE_RECHARGE_PAY_RESULT = "pay_trs_queue_recharge_pay_result";

    /**
     * 汽修充值结果通知
     */
    public static final String PAY_TRS_QUEUE_GARAGE_RECHARGE_PAY_RESULT = "pay_trs_queue_garage_recharge_pay_result";

    /**
     * 退款结果通知
     */
    public static final String PAY_TRS_QUEUE_REFUND = "pay_trs_queue_refund";

    /**
     * 订单取消,当月大单立减金额回退
     */
    public static final String PURCHASE_CANCEL_FULL_DISCOUNT_REFUND = "purchase_cancel_full_discount_refund";

    public static final String QUEUE_ORDER_TIME_OUT_TEMPLATE_MESSAGE = "queue_order_time_out_template_message";

    /**
     * 撤销五分钟内未充值的
     */
    public static final String TRS_QUEUE_REFUND_RECHARGE_5M = "trs_queue_refund_recharge_5m";


    /**
     * 采购单结算后优惠金额代付到汽配商
     */
    public static final String TRS_QUEUE_PURCHASE_SUBSIDY_SETTLE = "trs_queue_purchase_subsidy_settle";

    /**
     * 查询/增加抽奖机会
     */
    public static final String ORDER_GARAGE_WEALTH_QUEUE_LOTTERY_COUNT_ADD_5M = "order_garage_wealth_queue_lottery_count_add_5m";

    /**
     * 增加采购待回访数据
     */
    public static final String TRS_USER_ADMINCENTER_QUEUE_ADD_VISITRECORD = "trs_user_admincenter_queue_add_visitrecord";


    /**
     * 支付离合器采购单抽奖
     */
    public static final String TRS_WEALTH_QUEUE_PAY_PURCHASE_DRAW = "trs_wealth_queue_pay_purchase_draw";

    /**
     * 发货每天2次提醒，公众号提醒
     */
    public static final String QUEUE_PURCHASE_DELIVER_REMIND_MESSAGE = "queue_purchase_deliver_remind_message";


    /**
     * 标准询价 配件大于等于10个 发送延时消息队列 跟踪订单状态
     */
    public static final String TRS_QUEUE_BIG_INQUIRY_TRACK = "trs_queue_big_inquiry_track";

    /**
     * 退款单 停单追踪
     */
    public static final String TRS_QUEUE_PURCHASE_REFUND_DEALER_STOP_TRACK = "trs_queue_purchase_refund_dealer_stop_track";

    /**
     * 微信渠道结算结果获取
     */
    public static final String TRS_QUEUE_PURCHASE_SETTLE_WECHAT_RESULT = "trs_queue_purchase_settle_wechat_result";

    @Bean
    public Queue orderInquiryAllQuote30m() {
        return new Queue(ORDER_INQUIRY_ALL_QUOTE_30M, true);
    }

    @Bean
    public Queue refundRecharge5m() {
        return new Queue(TRS_QUEUE_REFUND_RECHARGE_5M, true);
    }

    @Bean
    public Queue trsWealthQueuePayPurchaseDraw() {
        return new Queue(TRS_WEALTH_QUEUE_PAY_PURCHASE_DRAW, true);
    }

    @Bean
    public Queue orderOrderQueueInquiry10m() {
        return new Queue(ORDER_ORDER_QUEUE_INQUIRY_10M, true);
    }


    @Bean
    public Queue orderUserQueuePurchasePayReminder() {
        return new Queue(ORDER_USER_QUEUE_PURCHASE_PAY_REMINDER, true);
    }

    @Bean
    public Queue orderPurchaseTaskOrderComplete() {
        return new Queue(ORDER_PURCHASE_TASK_ORDER_COMPLETE, true);
    }

    @Bean
    public Queue orderUserQueuePurchaseDeliveryReminder() {
        return new Queue(ORDER_USER_QUEUE_PURCHASE_DELIVERY_REMINDER, true);
    }

    @Bean
    public Queue orderUserQueuePurchaseReceivingReminder() {
        return new Queue(ORDER_USER_QUEUE_PURCHASE_RECEIVING_REMINDER, true);
    }

    @Bean
    public Queue orderUserQueueCreateQuotationReminder() {
        return new Queue(ORDER_USER_QUEUE_CREATE_QUOTATION_REMINDER, true);
    }

    @Bean
    public Queue orderUserQueueCreateInquiryCloudTencent() {
        return new Queue(ORDER_USER_QUEUE_CREATE_INQUIRY_CLOUD_TENCENT, true);
    }

    @Bean
    public Queue orderUserQueueCreateQuotationCloudTencent() {
        return new Queue(ORDER_USER_QUEUE_CREATE_QUOTATION_CLOUD_TENCENT, true);
    }

    @Bean
    public Queue orderUserQueuePayPurchaseCloudTencent() {
        return new Queue(ORDER_USER_QUEUE_PAY_PURCHASE_CLOUD_TENCENT, true);
    }

    @Bean
    public Queue orderUserQueueDeliveryCloudTencent() {
        return new Queue(ORDER_USER_QUEUE_DELIVERY_CLOUD_TENCENT, true);
    }

    @Bean
    public Queue orderOrderQueuePurchaseCreated24h() {
        return new Queue(TRS_QUEUE_PURCHASE_CREATED_24H, true);
    }

    @Bean
    public Queue orderOrderQueuePurchaseCreated22h() {
        return new Queue(TRS_QUEUE_PURCHASE_CREATED_22H, true);
    }

    @Bean
    public Queue trsQueueInquiry10m() {
        return new Queue(TRS_QUEUE_INQUIRY_10M, true);
    }

    @Bean
    public Queue orderuserueueAutoQuotationCloudTencet() {
        return new Queue(ORDER_USER_QUEUE_AUTO_QUOTATION_CLOUD_TENCENT, true);
    }

    @Bean
    public Queue queueInquiryCreated5m() {
        return new Queue(QUEUE_INQUIRY_CREATED_5M, true);
    }

    @Bean
    public Queue queueInquiryCreatedExpire48h() {
        return new Queue(QUEUE_INQUIRY_CREATED_EXPIRE_48H, true);
    }

    @Bean
    public Queue queueIntegralIncrease() {
        return new Queue(QUEUE_INTEGRAL_INCREASE, true);
    }

    @Bean
    public Queue queuePurchaseDeliverMessage() {
        return new Queue(QUEUE_PURCHASE_DELIVER_TEMPLATE_MESSAGE, true);
    }

    @Bean
    public Queue queuePurchaseSubsidySettle() {
        return new Queue(TRS_QUEUE_PURCHASE_SUBSIDY_SETTLE, true);
    }

    @Bean
    public Queue orderGarageWealthQueueLotteryCountAdd5m() {
        return new Queue(ORDER_GARAGE_WEALTH_QUEUE_LOTTERY_COUNT_ADD_5M, true);
    }

    @Bean
    public Queue trsUserAdmincenterQueueAddVisitrecord() {
        return new Queue(TRS_USER_ADMINCENTER_QUEUE_ADD_VISITRECORD, true);
    }

    @Bean
    public Queue queuePurchaseReceive() {
        return new Queue(QUEUE_PURCHASE_RECEIVE, true);
    }

    @Bean
    public Binding bindingPurchaseCreated24h(DirectExchange delayExchange, Queue orderOrderQueuePurchaseCreated24h) {
        return BindingBuilder.bind(orderOrderQueuePurchaseCreated24h).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingPurchaseCreated22h(DirectExchange delayExchange, Queue orderOrderQueuePurchaseCreated22h) {
        return BindingBuilder.bind(orderOrderQueuePurchaseCreated22h).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingPurchaseSubsidySettle(DirectExchange delayExchange, Queue queuePurchaseSubsidySettle) {
        return BindingBuilder.bind(queuePurchaseSubsidySettle).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingInquiryCreated5m(DirectExchange delayExchange, Queue queueInquiryCreated5m) {
        return BindingBuilder.bind(queueInquiryCreated5m).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingInquiryCreatedExpire48h(DirectExchange delayExchange, Queue queueInquiryCreatedExpire48h) {
        return BindingBuilder.bind(queueInquiryCreatedExpire48h).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingLotteryCountAdd5m(DirectExchange delayExchange, Queue orderGarageWealthQueueLotteryCountAdd5m) {
        return BindingBuilder.bind(orderGarageWealthQueueLotteryCountAdd5m).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingOrderInquiryAllQuote30m(DirectExchange delayExchange, Queue orderInquiryAllQuote30m) {
        return BindingBuilder.bind(orderInquiryAllQuote30m).to(delayExchange).withQueueName();
    }

    @Bean
    public Binding bindingRefundRecharge5m(DirectExchange delayExchange, Queue refundRecharge5m) {
        return BindingBuilder.bind(refundRecharge5m).to(delayExchange).withQueueName();
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean
    public TopicExchange trsTopicExchange() {
        return new TopicExchange(MQConsumerConstant.TRS_EXCHANGE_NAME);
    }

    /**
     * 声明死信Exchange
     *
     * @return
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(TRS_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 死信队列
     *
     * @return
     */
    @Bean("trsDeadLetterQueue")
    public Queue trsDeadLetterQueue() {
        return new Queue(TRS_DEAD_LETTER_QUEUE_NAME);
    }

    /**
     * 绑定死信队列
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBinding(@Qualifier("trsDeadLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TRS_DEAD_LETTER_QUEUE_ROUTING_KEY);
    }


}