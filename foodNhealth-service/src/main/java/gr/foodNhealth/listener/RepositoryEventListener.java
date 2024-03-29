package gr.foodNhealth.listener;

import gr.foodNhealth.model.BaseEntityNoId;
import gr.foodNhealth.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

@Component
public class RepositoryEventListener extends AbstractRepositoryEventListener<Object> {

    private static final Logger log = LoggerFactory.getLogger(RepositoryEventListener.class);

    @Override
    protected void onBeforeCreate(Object entity) {
        log.debug("Event BeforeCreate for entity: " + entity.getClass().getSimpleName());
        if (entity instanceof BaseEntityNoId) {
            BaseEntityNoId baseEntity = (BaseEntityNoId) entity;
            baseEntity.setDeleted(Boolean.FALSE);
            if (entity instanceof Person) {
                baseEntity.setIsActive(baseEntity.getIsActive() == null ? Boolean.FALSE : baseEntity.getIsActive());
            } else {
                baseEntity.setIsActive(Boolean.TRUE);
            }
        } else {
            // throw exception ??
            log.debug("Not an {} object but {}", BaseEntityNoId.class, entity.getClass());
        }
    }
}