package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CContacto;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CContacto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CContactoRepository extends JpaRepository<CContacto, Long>, JpaSpecificationExecutor<CContacto> {
}
