package com.httpcat.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@EnableCaching
public class config extends CachingConfigurerSupport implements AsyncConfigurer {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	@Bean(name = "taskExecutor")
	public Executor getAsyncExecutor() {
		int processors = Runtime.getRuntime().availableProcessors();
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(processors);
		executor.setMaxPoolSize(processors*2);
		executor.setQueueCapacity(500);
		executor.setDaemon(true);
		executor.initialize();

		return executor;
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(
				new ConcurrentMapCache("todosGatosRemotos"),
				new ConcurrentMapCache("todosGatos")));
		return cacheManager;
	}
}
