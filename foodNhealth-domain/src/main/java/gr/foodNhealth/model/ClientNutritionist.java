package gr.foodNhealth.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class ClientNutritionist extends BaseEntity {

    @ManyToOne
    private Client client;

    @ManyToOne
    private Nutritionist nutritionist;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACCEPTED,
        REJECTED,
        PENDING
    }
}
