import './home.scss';

import React, { useState, useEffect }from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Alert } from 'reactstrap';
import { Responsive, Segment, Dropdown, Label, Grid, Input, Button } from 'semantic-ui-react';
import Slider from './components/slider';
import Corridas from './components/modalCorridas';
import { getEntities as getCLugarParadas } from 'app/entities/c-lugar-parada/c-lugar-parada.reducer';
import { RouteComponentProps } from 'react-router-dom';
import { IRootState } from 'app/shared/reducers';

export interface IHomeProps extends StateProps, DispatchProps, RouteComponentProps {}

export const Home = (props: IHomeProps) => {
  const { account } = props;
  const [
    state= {
      lugarSalidaId: null,
      lugarLlegadaId: null,
      fechaSalida: '',
      search: '',
      modalOpen: false
    },
    setState
  ] = useState();

  useEffect(() => {
    props.getCLugarParadas();
  }, []);
  
  const handleSearch = () => {
    setState({
      ...state,
      search: '&lugarSalidaId.equals=${state.lugarSalidaId}'
    })
  }

  const handleCloseModal = () => {
    console.log('se cerro el modal ' + state.modalOpen);
    setState({ ...state, modalOpen: false })
  }

  const handleChangeInput = (e, { name, value, min, max, pattern, id }) => {
    // tslint:disable-next-line:switch-default
    switch (name) {
      case 'lugarSalida':
        console.log('lugar salida id ' + value)
        setState({ ...state, lugarSalidaId: value });
        break;
      case 'lugarLlegada':
        setState({ ...state, lugarSalidaId: value });
        break;
      case 'fechaSalida':
        setState({ ...state, fechaSalida: value });
        break;
    }
  };

  return (
    <div style={{ marginLeft: '10%' , marginRight: '10%'}}>
      <Segment centered>
        <Grid columns={4}>
          <Grid.Column largeScreen={3}>
            <Label>Origen</Label>
            <Dropdown
              style={{ width: '100%'}}
              name="lugarSalida"
              selection
              search
              options={props.lugarParadasList ?
                props.lugarParadasList.map(m => ({
                  key: m.id,
                  text: m.ciudad,
                  value: m.id
                }))
              : null
              }
              onChange={(e, { name, value, min, max, pattern, id }) => handleChangeInput(e, { name, value, min, max, pattern, id })}
            />
          </Grid.Column>
          <Grid.Column largeScreen={3}>
            <Label>Destino</Label>
            <Dropdown
              style={{ width: '100%'}}
              name="lugarLlegada"
              selection
              search
              options={props.lugarParadasList ?
                props.lugarParadasList.map(m => ({
                  key: m.id,
                  text: m.ciudad,
                  value: m.id
                }))
              : null
              }
              onChange={() => handleChangeInput}
            />
          </Grid.Column>
          <Grid.Column largeScreen={3}>
            <Label>Fecha de nacimiento</Label>
            <Input
              style={{ width: '100%'}}
              fluid
              type="date"
              name="fechaSalida"
              onChange={() => handleChangeInput}
            />
          </Grid.Column>
          <Grid.Column largeScreen={2}>
            <Button 
              style={{ width: '100%', marginTop: '50px'}}
              onClick={() => setState({ ...state, modalOpen: true })}
            >
              Buscar
            </Button>
          </Grid.Column>
        </Grid>
      </Segment>
      <Slider></Slider>
      <Segment>
        <Responsive>
          {account && account.login ? (
            <div>
              <Alert color="success">
                <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                  You are logged in as user {account.login}.
                </Translate>
              </Alert>
            </div>
          ) : (
              null
            )
          }
        </Responsive>
      </Segment>
      <div>
        <Corridas open={state.modalOpen} handleCloseModal={handleCloseModal} lugarSalidaId={state.lugarSalidaId}/>
      </div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
  lugarParadasList: storeState.cLugarParada.entities
});

const mapDispatchToProps = {
  getCLugarParadas
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Home);
