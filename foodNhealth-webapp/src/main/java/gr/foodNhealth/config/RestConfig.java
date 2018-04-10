package gr.foodNhealth.config;

import gr.foodNhealth.model.BaseEntityNoId;
import gr.foodNhealth.model.Nutritionist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfig.class);

    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }

    @Bean
    public HateoasPageableHandlerMethodArgumentResolver customResolver(HateoasPageableHandlerMethodArgumentResolver pageableResolver) {
        //force 'page' parameter to accept one-indexed based values (instead of the default, zero-indexed, ones)
        //WARNING: Causes side-effect, returning the 'number' property still zero-indexed.
        // Non elegant solution, see: https://jira.spring.io/browse/DATAREST-911
        pageableResolver.setOneIndexedParameters(true);
        return pageableResolver;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        LOGGER.info("explicit repository config");

        // expose ID property for all entities, by performing reflection inside the domain package
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(BaseEntityNoId.class));
        Set<BeanDefinition> components = provider.findCandidateComponents(Nutritionist.class.getPackage().getName());
        List<Class<?>> classes = new ArrayList<>();

        components.forEach(component -> {
            try {
                classes.add(Class.forName(component.getBeanClassName()));
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        });

        config.exposeIdsFor(classes.toArray(new Class[classes.size()]));
    }

}
