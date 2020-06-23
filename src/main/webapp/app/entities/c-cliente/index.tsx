import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CCliente from './c-cliente';
import CClienteDetail from './c-cliente-detail';
import CClienteUpdate from './c-cliente-update';
import CClienteDeleteDialog from './c-cliente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CClienteDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CClienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CClienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CClienteDetail} />
      <ErrorBoundaryRoute path={match.url} component={CCliente} />
    </Switch>
  </>
);

export default Routes;
