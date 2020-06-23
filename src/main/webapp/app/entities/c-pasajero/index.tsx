import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CPasajero from './c-pasajero';
import CPasajeroDetail from './c-pasajero-detail';
import CPasajeroUpdate from './c-pasajero-update';
import CPasajeroDeleteDialog from './c-pasajero-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CPasajeroDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CPasajeroUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CPasajeroUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CPasajeroDetail} />
      <ErrorBoundaryRoute path={match.url} component={CPasajero} />
    </Switch>
  </>
);

export default Routes;
