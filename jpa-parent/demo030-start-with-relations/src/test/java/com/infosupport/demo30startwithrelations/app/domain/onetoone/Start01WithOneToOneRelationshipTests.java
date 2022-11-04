package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Start01WithOneToOneRelationshipTests extends Start01WithOneToOneRelationshipTestsHelper {

    @Test
    void persistPublisher() {
        executeInTransaction(
                em -> {
                    PublisherOneToOne publisher = new PublisherOneToOne("A'dam", "NL", "OBB");
                    PublisherInfoOneToOne info = new PublisherInfoOneToOne();
                    info.setPublisher(publisher);
                    em.persist(publisher);
                    em.persist(info);
                }
        );
        executeInTransaction(
                em -> {
                    PublisherOneToOne publisher = em.find(PublisherOneToOne.class, 1L);
                    assertThat(publisher.getId()).isEqualTo(1L);
                }
        );
    }

    @Test
    void addPublisherAndPublisherInfo() {
        long publisherId;
        executeInTransaction(
                em -> {
                    String info = "Een inmiddels ruim ...";
                    PublisherInfoOneToOne publisherInfo = new PublisherInfoOneToOne(info);
                    PublisherOneToOne publisher = new PublisherOneToOne("A'dam", "NL", "OBB");
                    em.persist(publisher);
                    publisherInfo.setPublisher(publisher);
                    em.persist(publisherInfo);
                }
        );
        executeInTransaction(
                em -> {
                    PublisherInfoOneToOne publisherInfo = em.find(PublisherInfoOneToOne.class, 1L);
                    assertThat(publisherInfo.getPublisher().getId()).isEqualTo(1L);
                }
        );
    }
}
