import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CCliente from './c-cliente';
import CAutobus from './c-autobus';
import CContacto from './c-contacto';
import CCorrida from './c-corrida';
import CDetalleReservacion from './c-detalle-reservacion';
import CDireccion from './c-direccion';
import CDocumentoViaje from './c-documento-viaje';
import CLugarParada from './c-lugar-parada';
import CPasajero from './c-pasajero';
import CPendiente from './c-pendiente';
import CReservacion from './c-reservacion';
import CVenta from './c-venta';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div style={{ marginTop: '200px'}}>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}c-cliente`} component={CCliente} />
      <ErrorBoundaryRoute path={`${match.url}c-autobus`} component={CAutobus} />
      <ErrorBoundaryRoute path={`${match.url}c-contacto`} component={CContacto} />
      <ErrorBoundaryRoute path={`${match.url}c-corrida`} component={CCorrida} />
      <ErrorBoundaryRoute path={`${match.url}c-detalle-reservacion`} component={CDetalleReservacion} />
      <ErrorBoundaryRoute path={`${match.url}c-direccion`} component={CDireccion} />
      <ErrorBoundaryRoute path={`${match.url}c-documento-viaje`} component={CDocumentoViaje} />
      <ErrorBoundaryRoute path={`${match.url}c-lugar-parada`} component={CLugarParada} />
      <ErrorBoundaryRoute path={`${match.url}c-pasajero`} component={CPasajero} />
      <ErrorBoundaryRoute path={`${match.url}c-pendiente`} component={CPendiente} />
      <ErrorBoundaryRoute path={`${match.url}c-reservacion`} component={CReservacion} />
      <ErrorBoundaryRoute path={`${match.url}c-venta`} component={CVenta} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
