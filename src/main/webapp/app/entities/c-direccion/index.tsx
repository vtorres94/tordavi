import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CDireccion from './c-direccion';
import CDireccionDetail from './c-direccion-detail';
import CDireccionUpdate from './c-direccion-update';
import CDireccionDeleteDialog from './c-direccion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CDireccionDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CDireccionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CDireccionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CDireccionDetail} />
      <ErrorBoundaryRoute path={match.url} component={CDireccion} />
    </Switch>
  </>
);

export default Routes;
