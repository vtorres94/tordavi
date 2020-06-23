package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CPendiente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CPendiente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CPendienteRepository extends JpaRepository<CPendiente, Long>, JpaSpecificationExecutor<CPendiente> {
}
