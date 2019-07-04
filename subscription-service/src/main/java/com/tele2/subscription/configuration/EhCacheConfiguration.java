package com.tele2.subscription.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tele2.subscription.util.CustomKeyGenerator;

@EnableJpaRepositories(basePackages = "com.tele2.subscription.repository")
@EnableCaching
@Configuration
public class EhCacheConfiguration {

	/**
	 * This is EHCache enabling method, it specify customer Cache Manager
	 * @return
	 */
    @Bean
    public CacheManager cacheManager() {
    	EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
    	cacheCacheManager.setCacheManager(cacheMangerFactory().getObject());
    	cacheCacheManager.setTransactionAware(true);
        return cacheCacheManager;
    }

    /**
     * This is EHCache enabling method, it specify ehcache configuration
     * @return EhCacheManagerFactoryBean
     */
    @Bean
    public EhCacheManagerFactoryBean cacheMangerFactory() {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }
    
    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
