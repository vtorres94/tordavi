package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CAutobus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CAutobus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CAutobusRepository extends JpaRepository<CAutobus, Long>, JpaSpecificationExecutor<CAutobus> {
}
