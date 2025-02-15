package com.yomahub.liteflow.solon.config;


import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.monitor.MonitorBus;
import com.yomahub.liteflow.property.LiteflowConfig;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 主要的业务装配器
 * 在这个装配器里装配了执行器，执行器初始化类，监控器
 * 这个装配前置条件是需要LiteflowConfig，LiteflowPropertyAutoConfiguration以及SpringAware
 *
 * @author Bryan.Zhang
 * @author noear
 * @since 2.9
 */
@Configuration
public class LiteflowMainAutoConfiguration {

    @Inject("${liteflow.parse-on-start}")
    boolean parseOnStart;

    @Inject("${liteflow.monitor.enable-log}")
    boolean enableLog;

    //实例化FlowExecutor
    @Bean
    public FlowExecutor flowExecutor(LiteflowConfig liteflowConfig) {
        FlowExecutor flowExecutor = new FlowExecutor();
        flowExecutor.setLiteflowConfig(liteflowConfig);

        if (parseOnStart) {
            flowExecutor.init();
        }

        return flowExecutor;
    }

    @Bean
    public MonitorBus monitorBus(LiteflowConfig liteflowConfig) {
        if (enableLog) {
            return new MonitorBus(liteflowConfig);
        } else {
            return null; //null 即是没创建
        }
    }
}
