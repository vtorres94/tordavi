import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CDocumentoViaje from './c-documento-viaje';
import CDocumentoViajeDetail from './c-documento-viaje-detail';
import CDocumentoViajeUpdate from './c-documento-viaje-update';
import CDocumentoViajeDeleteDialog from './c-documento-viaje-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CDocumentoViajeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CDocumentoViajeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CDocumentoViajeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CDocumentoViajeDetail} />
      <ErrorBoundaryRoute path={match.url} component={CDocumentoViaje} />
    </Switch>
  </>
);

export default Routes;
