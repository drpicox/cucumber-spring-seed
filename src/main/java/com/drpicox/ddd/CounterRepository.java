package com.drpicox.ddd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter,Long> {
}


/*
public interface EntityRepository extends JpaRepository<Entity, Long> {

    // https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#d0e1576
    List<Entity> findAllByPlayer(Player player);

    List<Entity> findAllByCoordinatesIn(Collection<Coordinates> coordinatesList);

}
 */
