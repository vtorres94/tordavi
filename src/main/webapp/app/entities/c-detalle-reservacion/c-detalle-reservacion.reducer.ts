import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICDetalleReservacion, defaultValue } from 'app/shared/model/c-detalle-reservacion.model';

export const ACTION_TYPES = {
  FETCH_CDETALLERESERVACION_LIST: 'cDetalleReservacion/FETCH_CDETALLERESERVACION_LIST',
  FETCH_CDETALLERESERVACION: 'cDetalleReservacion/FETCH_CDETALLERESERVACION',
  CREATE_CDETALLERESERVACION: 'cDetalleReservacion/CREATE_CDETALLERESERVACION',
  UPDATE_CDETALLERESERVACION: 'cDetalleReservacion/UPDATE_CDETALLERESERVACION',
  DELETE_CDETALLERESERVACION: 'cDetalleReservacion/DELETE_CDETALLERESERVACION',
  RESET: 'cDetalleReservacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICDetalleReservacion>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CDetalleReservacionState = Readonly<typeof initialState>;

// Reducer

export default (state: CDetalleReservacionState = initialState, action): CDetalleReservacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CDETALLERESERVACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CDETALLERESERVACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CDETALLERESERVACION):
    case REQUEST(ACTION_TYPES.UPDATE_CDETALLERESERVACION):
    case REQUEST(ACTION_TYPES.DELETE_CDETALLERESERVACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CDETALLERESERVACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CDETALLERESERVACION):
    case FAILURE(ACTION_TYPES.CREATE_CDETALLERESERVACION):
    case FAILURE(ACTION_TYPES.UPDATE_CDETALLERESERVACION):
    case FAILURE(ACTION_TYPES.DELETE_CDETALLERESERVACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CDETALLERESERVACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CDETALLERESERVACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CDETALLERESERVACION):
    case SUCCESS(ACTION_TYPES.UPDATE_CDETALLERESERVACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CDETALLERESERVACION):
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

const apiUrl = 'api/c-detalle-reservacions';

// Actions

export const getEntities: ICrudGetAllAction<ICDetalleReservacion> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CDETALLERESERVACION_LIST,
    payload: axios.get<ICDetalleReservacion>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICDetalleReservacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CDETALLERESERVACION,
    payload: axios.get<ICDetalleReservacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICDetalleReservacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CDETALLERESERVACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICDetalleReservacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CDETALLERESERVACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICDetalleReservacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CDETALLERESERVACION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
