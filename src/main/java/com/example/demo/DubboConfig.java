package com.example.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.example.demo.service.DemoService;
import com.example.demo.service.FooService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {

    @Bean
    public ApplicationConfig dubboApplicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        config.setName("demo-provider");

        return config;
    }

    @Bean
    public RegistryConfig dubboRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://localhost:2181?client=zkclient");

        return registryConfig;
    }

    @Bean
    public ProtocolConfig dubboProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(12345);
        protocolConfig.setThreads(200);

        return protocolConfig;
    }

    @Bean(initMethod = "export")
    public ServiceConfig<DemoService> dubboServiceConfig(DemoService demoService) {
        return dubboServiceFoo(DemoService.class, demoService);
    }

    @Bean(initMethod = "export")
    public ServiceConfig<FooService> fooServiceServiceConfig(FooService fooService) {
        return dubboServiceFoo(FooService.class, fooService);
    }

    private <T> ServiceConfig<T> dubboServiceFoo(Class<?> clazz, T ref) {
        ServiceConfig<T> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(dubboApplicationConfig());
        serviceConfig.setRegistry(dubboRegistryConfig());
        serviceConfig.setProtocol(dubboProtocolConfig());
        serviceConfig.setInterface(clazz);
        serviceConfig.setRef(ref);
        serviceConfig.setVersion("1.0");

        return serviceConfig;
    }
}
