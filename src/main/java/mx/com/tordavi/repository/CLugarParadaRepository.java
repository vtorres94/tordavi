package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CLugarParada;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CLugarParada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CLugarParadaRepository extends JpaRepository<CLugarParada, Long>, JpaSpecificationExecutor<CLugarParada> {
}
