package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CReservacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CReservacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CReservacionRepository extends JpaRepository<CReservacion, Long>, JpaSpecificationExecutor<CReservacion> {
}
