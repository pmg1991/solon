package org.noear.solon.cloud;

import org.noear.solon.Utils;
import org.noear.solon.cloud.annotation.CloudConfig;
import org.noear.solon.cloud.annotation.CloudEvent;
import org.noear.solon.cloud.impl.CloudEventManager;
import org.noear.solon.cloud.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 云接口管理器
 *
 * @author noear
 * @since 1.2
 */
public class CloudManager {
    private static final Logger log = LoggerFactory.getLogger(CloudManager.class);

    /**
     * 云端发现服务
     */
    private static CloudDiscoveryService discoveryService;
    /**
     * 云端配置服务
     */
    private static CloudConfigService configService;
    /**
     * 云端事件服务管理
     */
    private static CloudEventManager eventServiceManager = new CloudEventManager();
    /**
     * 云端锁服务
     */
    private static CloudLockService lockService;

    /**
     * 云端日志服务
     */
    private static CloudLogService logService;

    /**
     * 云端名单服务
     */
    private static CloudListService listService;

    /**
     * 云端文件服务
     */
    private static CloudFileService fileService;

    /**
     * 云端断路器服务
     */
    private static CloudBreakerService breakerService;

    /**
     * 云端跟踪服务（链路）
     */
    private static CloudTraceService traceService;

    /**
     * 云端任务服务
     */
    private static CloudJobService jobService;

    /**
     * 云端ID生成工厂
     * */
    private static CloudIdServiceFactory idServiceFactory;
    private static CloudIdService idServiceDef;


    protected final static Map<CloudConfig, CloudConfigHandler> configHandlerMap = new LinkedHashMap<>();
    protected final static Map<CloudEvent, CloudEventHandler> eventHandlerMap = new LinkedHashMap<>();

    /**
     * 登记配置订阅
     */
    public static void register(CloudConfig anno, CloudConfigHandler handler) {
        configHandlerMap.put(anno, handler);
    }

    /**
     * 登记事件订阅
     */
    public static void register(CloudEvent anno, CloudEventHandler handler) {
        eventHandlerMap.put(anno, handler);
    }


    /**
     * 登记断路器服务
     */
    public static void register(CloudBreakerService service) {
        breakerService = service;
        log.trace("Cloud: {}", "CloudBreakerService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记配置服务
     */
    public static void register(CloudConfigService service) {
        configService = service;
        log.trace("Cloud: {}", "CloudConfigService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记注册与发现服务
     */
    public static void register(CloudDiscoveryService service) {
        discoveryService = service;
        log.trace("Cloud: {}",  "CloudDiscoveryService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记事件服务
     */
    public static void register(String channel, CloudEventService service) {
        eventServiceManager.register(channel, service);
        if(Utils.isEmpty(channel)) {
            log.trace("Cloud: {}", "CloudEventService registered from the " + service.getClass().getTypeName());
        }else{
            log.trace("Cloud: {}",  "CloudEventService registered from the " + service.getClass().getTypeName() + " as &" + channel);
        }
    }

    /**
     * 登记锁服务
     */
    public static void register(CloudLockService service) {
        lockService = service;
        log.trace("Cloud: {}", "CloudLockService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记日志服务
     */
    public static void register(CloudLogService service) {
        logService = service;
        log.trace("Cloud: {}", "CloudLogService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记列表服务
     */
    public static void register(CloudListService service) {
        listService = service;
        log.trace("Cloud: {}",  "CloudListService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记文件服务
     */
    public static void register(CloudFileService service) {
        fileService = service;
        log.trace("Cloud: {}",  "CloudFileService registered from the " + service.getClass().getTypeName());
    }


    /**
     * 登记链路跟踪服务
     */
    public static void register(CloudTraceService service) {
        traceService = service;
        log.trace("Cloud: {}",  "CloudTraceService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记任务服务
     */
    public static void register(CloudJobService service) {
        jobService = service;
        log.trace("Cloud: {}", "CloudJobService registered from the " + service.getClass().getTypeName());
    }

    /**
     * 登记ID生成工厂
     */
    public static void register(CloudIdServiceFactory factory) {
        idServiceFactory = factory;
        idServiceDef = factory.create();
        log.trace("Cloud: {}", "CloudIdServiceFactory registered from the " + factory.getClass().getTypeName());
    }

    protected static CloudBreakerService breakerService() {
        return breakerService;
    }

    protected static CloudConfigService configService() {
        return configService;
    }

    protected static CloudDiscoveryService discoveryService() {
        return discoveryService;
    }

    protected static CloudEventService eventService() {
        return eventServiceManager;
    }

    protected static CloudLockService lockService() {
        return lockService;
    }

    protected static CloudLogService logService() {
        return logService;
    }

    protected static CloudListService listService() {
        return listService;
    }

    protected static CloudFileService fileService() {
        return fileService;
    }

    protected static CloudTraceService traceService() {
        return traceService;
    }

    protected static CloudIdServiceFactory idServiceFactory(){
        return idServiceFactory;
    }

    protected static CloudIdService idServiceDef(){
        return idServiceDef;
    }

    protected static CloudJobService jobService(){
        return jobService;
    }

}
