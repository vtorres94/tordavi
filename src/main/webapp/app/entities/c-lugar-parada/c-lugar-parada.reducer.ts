import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICLugarParada, defaultValue } from 'app/shared/model/c-lugar-parada.model';

export const ACTION_TYPES = {
  FETCH_CLUGARPARADA_LIST: 'cLugarParada/FETCH_CLUGARPARADA_LIST',
  FETCH_CLUGARPARADA: 'cLugarParada/FETCH_CLUGARPARADA',
  CREATE_CLUGARPARADA: 'cLugarParada/CREATE_CLUGARPARADA',
  UPDATE_CLUGARPARADA: 'cLugarParada/UPDATE_CLUGARPARADA',
  DELETE_CLUGARPARADA: 'cLugarParada/DELETE_CLUGARPARADA',
  RESET: 'cLugarParada/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICLugarParada>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CLugarParadaState = Readonly<typeof initialState>;

// Reducer

export default (state: CLugarParadaState = initialState, action): CLugarParadaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CLUGARPARADA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CLUGARPARADA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CLUGARPARADA):
    case REQUEST(ACTION_TYPES.UPDATE_CLUGARPARADA):
    case REQUEST(ACTION_TYPES.DELETE_CLUGARPARADA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CLUGARPARADA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CLUGARPARADA):
    case FAILURE(ACTION_TYPES.CREATE_CLUGARPARADA):
    case FAILURE(ACTION_TYPES.UPDATE_CLUGARPARADA):
    case FAILURE(ACTION_TYPES.DELETE_CLUGARPARADA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLUGARPARADA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLUGARPARADA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CLUGARPARADA):
    case SUCCESS(ACTION_TYPES.UPDATE_CLUGARPARADA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CLUGARPARADA):
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

const apiUrl = 'api/c-lugar-paradas';

// Actions

export const getEntities: ICrudGetAllAction<ICLugarParada> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CLUGARPARADA_LIST,
    payload: axios.get<ICLugarParada>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICLugarParada> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CLUGARPARADA,
    payload: axios.get<ICLugarParada>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICLugarParada> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CLUGARPARADA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICLugarParada> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CLUGARPARADA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICLugarParada> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CLUGARPARADA,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
