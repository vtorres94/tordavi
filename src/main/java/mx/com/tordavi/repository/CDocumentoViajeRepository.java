package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CDocumentoViaje;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CDocumentoViaje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CDocumentoViajeRepository extends JpaRepository<CDocumentoViaje, Long>, JpaSpecificationExecutor<CDocumentoViaje> {
}
