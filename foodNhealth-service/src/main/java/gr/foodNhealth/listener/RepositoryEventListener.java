package gr.foodNhealth.listener;

import gr.foodNhealth.model.BaseEntityNoId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class RepositoryEventListener extends AbstractRepositoryEventListener<Object> {

    private static final Logger log = LoggerFactory.getLogger(RepositoryEventListener.class);

    @Override
    protected void onBeforeCreate(Object entity) {
        log.debug("Event BeforeCreate for entity: " + entity.getClass().getSimpleName());
        if (entity instanceof BaseEntityNoId) {
            BaseEntityNoId baseEntity = (BaseEntityNoId) entity;
            baseEntity.setDeleted(Boolean.FALSE);
            baseEntity.setIsActive(Boolean.TRUE);
            ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
            baseEntity.setCreateDate(Timestamp.from(utc.toInstant()));
            baseEntity.setUpdateDate(Timestamp.from(utc.toInstant()));
        } else {
            // throw exception ??
            log.debug("Not an {} object but {}", BaseEntityNoId.class, entity.getClass());
        }
    }

    @Override
    protected void onBeforeSave(Object entity) {
        log.debug("Event BeforeCreate for entity: " + entity.getClass().getSimpleName());
        if (entity instanceof BaseEntityNoId) {
            BaseEntityNoId baseEntity = (BaseEntityNoId) entity;
            ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
            baseEntity.setUpdateDate(Timestamp.from(utc.toInstant()));
        } else {
            // throw exception ??
            log.debug("Not an {} object but {}", BaseEntityNoId.class, entity.getClass());
        }
    }
}