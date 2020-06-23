import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-direccion.reducer';
import { ICDireccion } from 'app/shared/model/c-direccion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICDireccionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CDireccion = (props: ICDireccionProps) => {
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

  const { cDireccionList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-direccion-heading">
        <Translate contentKey="tordaviApp.cDireccion.home.title">C Direccions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cDireccion.home.createLabel">Create new C Direccion</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cDireccionList && cDireccionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('direccionComplete')}>
                  <Translate contentKey="tordaviApp.cDireccion.direccionComplete">Direccion Complete</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('direccion')}>
                  <Translate contentKey="tordaviApp.cDireccion.direccion">Direccion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('numExterior')}>
                  <Translate contentKey="tordaviApp.cDireccion.numExterior">Num Exterior</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('numInterior')}>
                  <Translate contentKey="tordaviApp.cDireccion.numInterior">Num Interior</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('codigoPostal')}>
                  <Translate contentKey="tordaviApp.cDireccion.codigoPostal">Codigo Postal</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ciudad')}>
                  <Translate contentKey="tordaviApp.cDireccion.ciudad">Ciudad</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estado')}>
                  <Translate contentKey="tordaviApp.cDireccion.estado">Estado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('pais')}>
                  <Translate contentKey="tordaviApp.cDireccion.pais">Pais</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cDireccion.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cDireccion.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cDireccion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cDireccion.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cDireccion.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cDireccion.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cDireccion.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cDireccion.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cDireccion.pasajero">Pasajero</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cDireccionList.map((cDireccion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cDireccion.id}`} color="link" size="sm">
                      {cDireccion.id}
                    </Button>
                  </td>
                  <td>{cDireccion.direccionComplete}</td>
                  <td>{cDireccion.direccion}</td>
                  <td>{cDireccion.numExterior}</td>
                  <td>{cDireccion.numInterior}</td>
                  <td>{cDireccion.codigoPostal}</td>
                  <td>{cDireccion.ciudad}</td>
                  <td>{cDireccion.estado}</td>
                  <td>{cDireccion.pais}</td>
                  <td>{cDireccion.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cDireccion.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cDireccion.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cDireccion.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cDireccion.notas}</td>
                  <td>{cDireccion.estatus}</td>
                  <td>{cDireccion.borrado}</td>
                  <td>
                    {cDireccion.clienteCliente ? <Link to={`c-cliente/${cDireccion.clienteId}`}>{cDireccion.clienteCliente}</Link> : ''}
                  </td>
                  <td>
                    {cDireccion.pasajeroNombreCompleto ? (
                      <Link to={`c-pasajero/${cDireccion.pasajeroId}`}>{cDireccion.pasajeroNombreCompleto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cDireccion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cDireccion.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cDireccion.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="tordaviApp.cDireccion.home.notFound">No C Direccions found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cDireccionList && cDireccionList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cDireccion }: IRootState) => ({
  cDireccionList: cDireccion.entities,
  loading: cDireccion.loading,
  totalItems: cDireccion.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDireccion);
