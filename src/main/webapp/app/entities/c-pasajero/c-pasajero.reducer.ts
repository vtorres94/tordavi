import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction, ICrudSearchAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICPasajero, defaultValue } from 'app/shared/model/c-pasajero.model';

export const ACTION_TYPES = {
  FETCH_CPASAJERO_LIST: 'cPasajero/FETCH_CPASAJERO_LIST',
  FETCH_CPASAJERO: 'cPasajero/FETCH_CPASAJERO',
  CREATE_CPASAJERO: 'cPasajero/CREATE_CPASAJERO',
  UPDATE_CPASAJERO: 'cPasajero/UPDATE_CPASAJERO',
  DELETE_CPASAJERO: 'cPasajero/DELETE_CPASAJERO',
  RESET: 'cPasajero/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICPasajero>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CPasajeroState = Readonly<typeof initialState>;

// Reducer

export default (state: CPasajeroState = initialState, action): CPasajeroState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CPASAJERO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CPASAJERO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CPASAJERO):
    case REQUEST(ACTION_TYPES.UPDATE_CPASAJERO):
    case REQUEST(ACTION_TYPES.DELETE_CPASAJERO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CPASAJERO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CPASAJERO):
    case FAILURE(ACTION_TYPES.CREATE_CPASAJERO):
    case FAILURE(ACTION_TYPES.UPDATE_CPASAJERO):
    case FAILURE(ACTION_TYPES.DELETE_CPASAJERO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPASAJERO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPASAJERO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CPASAJERO):
    case SUCCESS(ACTION_TYPES.UPDATE_CPASAJERO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CPASAJERO):
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

const apiUrl = 'api/c-pasajeros';

// Actions

export const getEntities: ICrudSearchAction<ICPasajero> = (search, page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?borrado.equals=0&page=${page}&size=${size}&sort=${sort}${search}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CPASAJERO_LIST,
    payload: axios.get<ICPasajero>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICPasajero> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CPASAJERO,
    payload: axios.get<ICPasajero>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICPasajero> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CPASAJERO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICPasajero> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CPASAJERO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICPasajero> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CPASAJERO,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
