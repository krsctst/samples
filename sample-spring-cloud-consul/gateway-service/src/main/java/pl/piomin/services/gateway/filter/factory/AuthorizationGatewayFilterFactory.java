package pl.piomin.services.gateway.filter.factory;
import java.util.Collections;
import java.util.List;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import pl.piomin.services.gateway.filter.AuthorizationGatewayFilter;

/**
 * 请求权限局部过滤器工厂
 *
 * @author liuzhuoming #kr
 */
public class AuthorizationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {

    public AuthorizationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            } else {
                return new AuthorizationGatewayFilter().filter(exchange, chain);
            }
        };
    }

    public static class Config {

        /**
         * 是否开启鉴权header验证
         */
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}