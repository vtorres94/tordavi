package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CDireccion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CDireccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CDireccionRepository extends JpaRepository<CDireccion, Long>, JpaSpecificationExecutor<CDireccion> {
}
