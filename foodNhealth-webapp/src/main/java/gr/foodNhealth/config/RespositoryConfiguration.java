package gr.foodNhealth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

@Deprecated
//@Configuration
public class RespositoryConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    @Bean
    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {

        HateoasPageableHandlerMethodArgumentResolver resolver = super.pageableResolver();
        //force 'page' parameter to accept one-indexed based values (instead of the default, zero-indexed, ones)
        //WARNING: Causes side-effect, returning the 'number' property still zero-indexed.
        resolver.setOneIndexedParameters(true);
//        resolver.setMaxPageSize(Integer.MAX_VALUE); if not set, max returned is 1000
        return resolver;
    }

}