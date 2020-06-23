package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CVenta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CVenta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CVentaRepository extends JpaRepository<CVenta, Long>, JpaSpecificationExecutor<CVenta> {
}
