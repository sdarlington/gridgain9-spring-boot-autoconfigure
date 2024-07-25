package org.gridgain.springframework.boot.autoconfigure;

import org.apache.ignite.client.IgniteClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class IgniteClientAutoConfiguration {

    /** Ignite client configuration properties prefix. */
    public static final String IGNITE_CLIENT_PROPS_PREFIX = "gridgain9-client";

    /**
     * @return Default(empty) Ignite client configurer.
     */
    @ConditionalOnMissingBean
    @Bean
    public IgniteClientConfigurer clientConfigurer() {
        return cfg -> { /* No-op. */ };
    }

    /**
     * Provides an instance of {@link IgniteClient.Builder} customized with the {@link IgniteClientConfigurer}.
     *
     * @param configurer Configurer.
     * @return Client configuration.
     */
    @ConditionalOnMissingBean
    @Bean
    @ConfigurationProperties(prefix = IGNITE_CLIENT_PROPS_PREFIX)
    public IgniteClient.Builder igniteCfg(IgniteClientConfigurer configurer) {
        var cfg = new IgniteClientConfiguration();

        configurer.accept(cfg);

        return cfg;
    }

    /**
     * Starts an {@link IgniteClient}.
     *
     * @param cfg Configuration.
     * @return Client instance.
     */
    @ConditionalOnBean(IgniteClient.Builder.class)
    @Bean
    public IgniteClient igniteClient(IgniteClient.Builder cfg) {
        return cfg.build();
    }

}
