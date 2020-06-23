package pl.beata.randomdb.randomdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beata.randomdb.randomdb.model.RandomNumber;

@Repository
public interface RandomRepository extends JpaRepository<RandomNumber, Long> {
}
