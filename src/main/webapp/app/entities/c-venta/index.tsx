import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CVenta from './c-venta';
import CVentaDetail from './c-venta-detail';
import CVentaUpdate from './c-venta-update';
import CVentaDeleteDialog from './c-venta-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CVentaDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CVentaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CVentaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CVentaDetail} />
      <ErrorBoundaryRoute path={match.url} component={CVenta} />
    </Switch>
  </>
);

export default Routes;
