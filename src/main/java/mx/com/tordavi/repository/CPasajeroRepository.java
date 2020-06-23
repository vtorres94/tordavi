package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CPasajero;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CPasajero entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CPasajeroRepository extends JpaRepository<CPasajero, Long>, JpaSpecificationExecutor<CPasajero> {
}
