package com.hml.atp.zues.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.annotation.Resource;

@Configuration
@ConditionalOnProperty(name = "spring.rabbitmq.addresses")
public class MQConfig implements RabbitListenerConfigurer {

    public static final String EXCHANGE_DELAY = "dgmq-exchange-delay";

    /**
     * 支付结果提醒
     */
    public static final String QUEUE_PAY_RESULT_REMINDER = "queue_pay_trs_result_reminder";

    /**
     * 会员协议签订通知
     */
    public static final String PAY_USER_QUEUE_SIGN_CONTRACT = "pay_user_queue_sign_contract";

    /**
     * 会员协议签订通知
     */
    public static final String PAY_EXPRESS_QUEUE_SIGN_CONTRACT = "pay_express_queue_sign_contract";


    /**
     * 账户余额扣款协议签约
     */
    public static final String PAY_SIGN_BALANCE_PROTOCOL = "pay_sign_balance_protocol";

    /**
     * 会员设置支付密码通知
     */
    public static final String PAY_USER_QUEUE_SET_PAY_PWD = "pay_user_queue_set_pay_pwd";

    /**
     * 会员修改支付密码通知
     */
    public static final String PAY_USER_QUEUE_UPDATE_PAY_PWD = "pay_user_queue_update_pay_pwd";

    /**
     * 提现结果通知
     */
    public static final String PAY_ORDER_QUEUE_WITHDRAWAL = "pay_order_queue_withdrawal";

    /**
     * 物流提现结果通知
     */
    public static final String PAY_EXPRESS_QUEUE_WITHDRAWAL = "pay_express_queue_withdrawal";


    /**
     * 退款结果通知
     */
    public static final String PAY_TRS_QUEUE_REFUND = "pay_trs_queue_refund";

    /**
     * 物流退款结果通知
     */
    public static final String PAY_EXPRESS_QUEUE_REFUND = "pay_express_queue_refund";

    /**
     * 工单支付结果通知
     */
    public static final String PAY_ORDER_QUEUE_SERVICE_PAY_RESULT = "pay_order_queue_service_pay_result";

    /**
     * 保证金支付结果通知
     */
    public static final String PAY_ORDER_QUEUE_BOND_PAY_RESULT = "pay_order_queue_bond_pay_result";


    /**
     * 充值结果通知
     */
    public static final String PAY_TRS_QUEUE_RECHARGE_PAY_RESULT = "pay_trs_queue_recharge_pay_result";

    /**
     * 汽修充值结果通知
     */
    public static final String PAY_TRS_QUEUE_GARAGE_RECHARGE_PAY_RESULT = "pay_trs_queue_garage_recharge_pay_result";

    /**
     * 物流费支付结果通知
     */
    public static final String PAY_EXPRESS_QUEUE_FREIGHT_PAY_RESULT = "pay_express_queue_freight_pay_result";

    /**
     * 服务费支付结果提醒
     */
    public static final String QUEUE_SERVICE_CHARGE_PAY_RESULT_REMINDER = "queue_service_charge_pay_result_reminder";

    /**
     * 大大币兑换结果提醒
     */
    public static final String QUEUE_DDCOIN_EXCHANGE_RESULT_REMINDER = "queue_ddcoin_exchange_result_reminder";

    /**
     * 采购单补贴结果提醒
     */
    public static final String QUEUE_MALL_PURCHASE_SUBSIDY_RESULT_REMINDER = "queue_mall_purchase_subsidy_result_reminder";

    /**
     * 支付单5分钟自动过期
     */
    public static final String QUEUE_PAYORDER_CLOSE = "dgmq_payorder_close";
    /**
     * 支付单5分钟自动过期
     */
    public static final int PAYORDER_CLOSE_SECOND = 300;

    @Resource
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
    public Binding queuePayOrderCloseDelay(DirectExchange delayExchange, Queue queuePayOrderClose) {
        return BindingBuilder.bind(queuePayOrderClose).to(delayExchange).withQueueName();
    }


    @Bean
    public Queue queuePayOrderClose() {
        return new Queue(QUEUE_PAYORDER_CLOSE, true);
    }

    @Bean
    public Queue signContractQueue() {
        return new Queue(PAY_USER_QUEUE_SIGN_CONTRACT, true);
    }

    @Bean
    public Queue withdrawalQueue() {
        return new Queue(PAY_ORDER_QUEUE_WITHDRAWAL, true);
    }

    @Bean
    public Queue servicePayQueue() {
        return new Queue(PAY_ORDER_QUEUE_SERVICE_PAY_RESULT, true);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

}