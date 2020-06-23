import React, { useState, useEffect }from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Alert } from 'reactstrap';
import { Responsive, Segment, Dropdown, Label, Grid, Input, Button, Modal} from 'semantic-ui-react';
import { getEntities as getCCorridas } from 'app/entities/c-corrida/c-corrida.reducer';
import { CCorrida } from './c-corridas';

export interface ICorridasProps extends StateProps, DispatchProps {
    open?: boolean
    lugarSalidaId?: number,
    lugarLlegadaId?: number,
    fechaSalida?: string,
    handleCloseModal: () => void
}

export const Corridas = (props: ICorridasProps) => {
  const { account } = props;
  const [
    state= {
        search: '',
        open: props.open
    },  
    setState
  ] = useState();

  useEffect(() => {
    props.getCCorridas();
  }, []);

  useEffect(() => {
    setState({ ...state, open: props.open });
  }, [props.open]);
  
  const handleSearch = () => {
    setState({
      ...state,
      search: '&lugarSalidaId.equals=' + props.lugarSalidaId
    })
  }

  const handleClose = () => {
    console.log('cerrando modal')
    setState({ ...state, open:false })
    props.handleCloseModal()
  }
  return (
    <div style={{ marginLeft: '10%' , marginRight: '10%'}}>
        <Modal
            centered
            open={state.open}
            style={{ height: 'auto', position: 'relative' }}
            size="large"
            onClose={ ()=> handleClose() }
            closeOnEscape={true}
        >
            <Modal.Content>
                <Modal.Description>
                    <Segment>
                        <CCorrida lugarSalidaId={props.lugarSalidaId}/>
                    </Segment>
                </Modal.Description>
            </Modal.Content>
        </Modal>
      
    </div>
  );
};

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = {
    getCCorridas
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Corridas);
