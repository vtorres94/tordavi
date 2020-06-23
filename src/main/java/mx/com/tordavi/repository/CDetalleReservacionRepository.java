package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CDetalleReservacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CDetalleReservacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CDetalleReservacionRepository extends JpaRepository<CDetalleReservacion, Long>, JpaSpecificationExecutor<CDetalleReservacion> {
}
