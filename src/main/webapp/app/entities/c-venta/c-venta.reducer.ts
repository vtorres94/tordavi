import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICVenta, defaultValue } from 'app/shared/model/c-venta.model';

export const ACTION_TYPES = {
  FETCH_CVENTA_LIST: 'cVenta/FETCH_CVENTA_LIST',
  FETCH_CVENTA: 'cVenta/FETCH_CVENTA',
  CREATE_CVENTA: 'cVenta/CREATE_CVENTA',
  UPDATE_CVENTA: 'cVenta/UPDATE_CVENTA',
  DELETE_CVENTA: 'cVenta/DELETE_CVENTA',
  RESET: 'cVenta/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICVenta>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CVentaState = Readonly<typeof initialState>;

// Reducer

export default (state: CVentaState = initialState, action): CVentaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CVENTA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CVENTA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CVENTA):
    case REQUEST(ACTION_TYPES.UPDATE_CVENTA):
    case REQUEST(ACTION_TYPES.DELETE_CVENTA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CVENTA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CVENTA):
    case FAILURE(ACTION_TYPES.CREATE_CVENTA):
    case FAILURE(ACTION_TYPES.UPDATE_CVENTA):
    case FAILURE(ACTION_TYPES.DELETE_CVENTA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CVENTA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CVENTA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CVENTA):
    case SUCCESS(ACTION_TYPES.UPDATE_CVENTA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CVENTA):
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

const apiUrl = 'api/c-ventas';

// Actions

export const getEntities: ICrudGetAllAction<ICVenta> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CVENTA_LIST,
    payload: axios.get<ICVenta>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICVenta> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CVENTA,
    payload: axios.get<ICVenta>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICVenta> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CVENTA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICVenta> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CVENTA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICVenta> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CVENTA,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
