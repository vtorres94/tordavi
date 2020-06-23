import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-pasajero.reducer';
import { ICPasajero } from 'app/shared/model/c-pasajero.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICPasajeroProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CPasajero = (props: ICPasajeroProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities('', 0, 1000, 'id,asc');
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

  const { cPasajeroList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-pasajero-heading">
        <Translate contentKey="tordaviApp.cPasajero.home.title">C Pasajeros</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cPasajero.home.createLabel">Create new C Pasajero</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cPasajeroList && cPasajeroList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nombreCompleto')}>
                  <Translate contentKey="tordaviApp.cPasajero.nombreCompleto">Nombre Completo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nombre')}>
                  <Translate contentKey="tordaviApp.cPasajero.nombre">Nombre</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('segundoNombre')}>
                  <Translate contentKey="tordaviApp.cPasajero.segundoNombre">Segundo Nombre</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('apellido')}>
                  <Translate contentKey="tordaviApp.cPasajero.apellido">Apellido</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('segundoApellido')}>
                  <Translate contentKey="tordaviApp.cPasajero.segundoApellido">Segundo Apellido</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('edad')}>
                  <Translate contentKey="tordaviApp.cPasajero.edad">Edad</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('curp')}>
                  <Translate contentKey="tordaviApp.cPasajero.curp">Curp</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('ciudadania')}>
                  <Translate contentKey="tordaviApp.cPasajero.ciudadania">Ciudadania</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cPasajero.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cPasajero.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cPasajero.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cPasajero.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cPasajero.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cPasajero.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cPasajero.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cPasajero.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cPasajeroList ?
              cPasajeroList.map((cPasajero, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cPasajero.id}`} color="link" size="sm">
                      {cPasajero.id}
                    </Button>
                  </td>
                  <td>{cPasajero.nombreCompleto}</td>
                  <td>{cPasajero.nombre}</td>
                  <td>{cPasajero.segundoNombre}</td>
                  <td>{cPasajero.apellido}</td>
                  <td>{cPasajero.segundoApellido}</td>
                  <td>{cPasajero.edad}</td>
                  <td>{cPasajero.curp}</td>
                  <td>{cPasajero.ciudadania}</td>
                  <td>{cPasajero.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cPasajero.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cPasajero.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cPasajero.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cPasajero.notas}</td>
                  <td>{cPasajero.estatus}</td>
                  <td>{cPasajero.borrado}</td>
                  <td>{cPasajero.clienteCliente ? <Link to={`c-cliente/${cPasajero.clienteId}`}>{cPasajero.clienteCliente}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cPasajero.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cPasajero.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cPasajero.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              )) : null}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tordaviApp.cPasajero.home.notFound">No C Pasajeros found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cPasajeroList && cPasajeroList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cPasajero }: IRootState) => ({
  cPasajeroList: cPasajero.entities,
  loading: cPasajero.loading,
  totalItems: cPasajero.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CPasajero);
