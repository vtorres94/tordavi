package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CCorrida;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CCorrida entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CCorridaRepository extends JpaRepository<CCorrida, Long>, JpaSpecificationExecutor<CCorrida> {
}
