package mx.com.tordavi.repository;

import mx.com.tordavi.domain.CCliente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CCliente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CClienteRepository extends JpaRepository<CCliente, Long>, JpaSpecificationExecutor<CCliente> {
}
