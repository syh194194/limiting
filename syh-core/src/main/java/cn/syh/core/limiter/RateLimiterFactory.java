package cn.syh.core.limiter;

import cn.syh.core.config.RateLimiterConfig;
import cn.syh.core.exception.LimitingException;
import cn.syh.core.observer.RateLimiterObserver;
import cn.syh.commoon.entity.RateLimiterRule;

/**
 * 简单工厂模式
 */
public class RateLimiterFactory {

    public static RateLimiter of(RateLimiterRule rule) {
        return of(rule, RateLimiterConfig.getInstance());
    }

    public static RateLimiter of(RateLimiterRule rule, RateLimiterConfig config) {
        switch (rule.getLimiterModel()) {
            case POINT: //本地限流
                RateLimiter limiterDefault = new RateLimiterDefault(rule, config);
                RateLimiterObserver.registered(limiterDefault,config);
                return limiterDefault;
            case CLOUD: //集群限流
                limiterDefault = new RateLimiterDefault(rule, config);
                rule.setName(rule.getName() == null ? String.valueOf(limiterDefault.hashCode()) : rule.getName());
                RateLimiterObserver.registered(limiterDefault,config);
                return limiterDefault;
            default:
                throw new LimitingException("CurrentModel Parameter not set");
        }
    }

}
