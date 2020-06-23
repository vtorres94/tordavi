import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-documento-viaje.reducer';
import { ICDocumentoViaje } from 'app/shared/model/c-documento-viaje.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICDocumentoViajeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CDocumentoViaje = (props: ICDocumentoViajeProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { cDocumentoViajeList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-documento-viaje-heading">
        <Translate contentKey="tordaviApp.cDocumentoViaje.home.title">C Documento Viajes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cDocumentoViaje.home.createLabel">Create new C Documento Viaje</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cDocumentoViajeList && cDocumentoViajeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('claveDocumento')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.claveDocumento">Clave Documento</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('documento')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.documento">Documento</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.fechaCreacion">Fecha Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cDocumentoViaje.pasajero">Pasajero</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cDocumentoViajeList.map((cDocumentoViaje, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cDocumentoViaje.id}`} color="link" size="sm">
                      {cDocumentoViaje.id}
                    </Button>
                  </td>
                  <td>{cDocumentoViaje.claveDocumento}</td>
                  <td>{cDocumentoViaje.documento}</td>
                  <td>{cDocumentoViaje.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cDocumentoViaje.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cDocumentoViaje.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cDocumentoViaje.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cDocumentoViaje.notas}</td>
                  <td>{cDocumentoViaje.estatus}</td>
                  <td>{cDocumentoViaje.borrado}</td>
                  <td>
                    {cDocumentoViaje.clienteCliente ? (
                      <Link to={`c-cliente/${cDocumentoViaje.clienteId}`}>{cDocumentoViaje.clienteCliente}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {cDocumentoViaje.pasajeroNombreCompleto ? (
                      <Link to={`c-pasajero/${cDocumentoViaje.pasajeroId}`}>{cDocumentoViaje.pasajeroNombreCompleto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cDocumentoViaje.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cDocumentoViaje.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cDocumentoViaje.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tordaviApp.cDocumentoViaje.home.notFound">No C Documento Viajes found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cDocumentoViajeList && cDocumentoViajeList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ cDocumentoViaje }: IRootState) => ({
  cDocumentoViajeList: cDocumentoViaje.entities,
  loading: cDocumentoViaje.loading,
  totalItems: cDocumentoViaje.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDocumentoViaje);
