import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICReservacion, defaultValue } from 'app/shared/model/c-reservacion.model';

export const ACTION_TYPES = {
  FETCH_CRESERVACION_LIST: 'cReservacion/FETCH_CRESERVACION_LIST',
  FETCH_CRESERVACION: 'cReservacion/FETCH_CRESERVACION',
  CREATE_CRESERVACION: 'cReservacion/CREATE_CRESERVACION',
  UPDATE_CRESERVACION: 'cReservacion/UPDATE_CRESERVACION',
  DELETE_CRESERVACION: 'cReservacion/DELETE_CRESERVACION',
  RESET: 'cReservacion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICReservacion>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CReservacionState = Readonly<typeof initialState>;

// Reducer

export default (state: CReservacionState = initialState, action): CReservacionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CRESERVACION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CRESERVACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CRESERVACION):
    case REQUEST(ACTION_TYPES.UPDATE_CRESERVACION):
    case REQUEST(ACTION_TYPES.DELETE_CRESERVACION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CRESERVACION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CRESERVACION):
    case FAILURE(ACTION_TYPES.CREATE_CRESERVACION):
    case FAILURE(ACTION_TYPES.UPDATE_CRESERVACION):
    case FAILURE(ACTION_TYPES.DELETE_CRESERVACION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CRESERVACION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CRESERVACION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CRESERVACION):
    case SUCCESS(ACTION_TYPES.UPDATE_CRESERVACION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CRESERVACION):
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

const apiUrl = 'api/c-reservacions';

// Actions

export const getEntities: ICrudGetAllAction<ICReservacion> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CRESERVACION_LIST,
    payload: axios.get<ICReservacion>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICReservacion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CRESERVACION,
    payload: axios.get<ICReservacion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICReservacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CRESERVACION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICReservacion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CRESERVACION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICReservacion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CRESERVACION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
