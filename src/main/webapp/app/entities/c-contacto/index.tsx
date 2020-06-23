import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CContacto from './c-contacto';
import CContactoDetail from './c-contacto-detail';
import CContactoUpdate from './c-contacto-update';
import CContactoDeleteDialog from './c-contacto-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CContactoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CContactoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CContactoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CContactoDetail} />
      <ErrorBoundaryRoute path={match.url} component={CContacto} />
    </Switch>
  </>
);

export default Routes;
