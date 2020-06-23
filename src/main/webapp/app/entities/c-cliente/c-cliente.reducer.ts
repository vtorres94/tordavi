import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICCliente, defaultValue } from 'app/shared/model/c-cliente.model';

export const ACTION_TYPES = {
  FETCH_CCLIENTE_LIST: 'cCliente/FETCH_CCLIENTE_LIST',
  FETCH_CCLIENTE: 'cCliente/FETCH_CCLIENTE',
  CREATE_CCLIENTE: 'cCliente/CREATE_CCLIENTE',
  UPDATE_CCLIENTE: 'cCliente/UPDATE_CCLIENTE',
  DELETE_CCLIENTE: 'cCliente/DELETE_CCLIENTE',
  RESET: 'cCliente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICCliente>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CClienteState = Readonly<typeof initialState>;

// Reducer

export default (state: CClienteState = initialState, action): CClienteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CCLIENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CCLIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CCLIENTE):
    case REQUEST(ACTION_TYPES.UPDATE_CCLIENTE):
    case REQUEST(ACTION_TYPES.DELETE_CCLIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CCLIENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CCLIENTE):
    case FAILURE(ACTION_TYPES.CREATE_CCLIENTE):
    case FAILURE(ACTION_TYPES.UPDATE_CCLIENTE):
    case FAILURE(ACTION_TYPES.DELETE_CCLIENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCLIENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCLIENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CCLIENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_CCLIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CCLIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/c-clientes';

// Actions

export const getEntities: ICrudGetAllAction<ICCliente> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CCLIENTE_LIST,
    payload: axios.get<ICCliente>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICCliente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CCLIENTE,
    payload: axios.get<ICCliente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICCliente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CCLIENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICCliente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CCLIENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICCliente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CCLIENTE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
