package cn.syh.core.limiter;

import cn.syh.commoon.entity.RateLimiterRule;
import cn.syh.monitor.client.MonitorService;

public interface RateLimiter {

    MonitorService getMonitorService();

    void init(RateLimiterRule rule);

    boolean tryAcquire();

    boolean tryAcquire(String s);

    String getId();

    RateLimiterRule getRule();
}
