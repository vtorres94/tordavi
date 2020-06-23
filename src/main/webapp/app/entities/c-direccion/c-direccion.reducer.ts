import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICDireccion, defaultValue } from 'app/shared/model/c-direccion.model';

export const ACTION_TYPES = {
  FETCH_CDIRECCION_LIST: 'cDireccion/FETCH_CDIRECCION_LIST',
  FETCH_CDIRECCION: 'cDireccion/FETCH_CDIRECCION',
  CREATE_CDIRECCION: 'cDireccion/CREATE_CDIRECCION',
  UPDATE_CDIRECCION: 'cDireccion/UPDATE_CDIRECCION',
  DELETE_CDIRECCION: 'cDireccion/DELETE_CDIRECCION',
  RESET: 'cDireccion/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICDireccion>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CDireccionState = Readonly<typeof initialState>;

// Reducer

export default (state: CDireccionState = initialState, action): CDireccionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CDIRECCION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CDIRECCION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CDIRECCION):
    case REQUEST(ACTION_TYPES.UPDATE_CDIRECCION):
    case REQUEST(ACTION_TYPES.DELETE_CDIRECCION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CDIRECCION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CDIRECCION):
    case FAILURE(ACTION_TYPES.CREATE_CDIRECCION):
    case FAILURE(ACTION_TYPES.UPDATE_CDIRECCION):
    case FAILURE(ACTION_TYPES.DELETE_CDIRECCION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CDIRECCION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CDIRECCION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CDIRECCION):
    case SUCCESS(ACTION_TYPES.UPDATE_CDIRECCION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CDIRECCION):
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

const apiUrl = 'api/c-direccions';

// Actions

export const getEntities: ICrudGetAllAction<ICDireccion> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CDIRECCION_LIST,
    payload: axios.get<ICDireccion>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICDireccion> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CDIRECCION,
    payload: axios.get<ICDireccion>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICDireccion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CDIRECCION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICDireccion> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CDIRECCION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICDireccion> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CDIRECCION,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
