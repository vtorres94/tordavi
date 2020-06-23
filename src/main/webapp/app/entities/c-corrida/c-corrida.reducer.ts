import axios from 'axios';
import { ICrudGetAction, ICrudSearchAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICCorrida, defaultValue } from 'app/shared/model/c-corrida.model';

export const ACTION_TYPES = {
  FETCH_CCORRIDA_LIST: 'cCorrida/FETCH_CCORRIDA_LIST',
  FETCH_CCORRIDA: 'cCorrida/FETCH_CCORRIDA',
  CREATE_CCORRIDA: 'cCorrida/CREATE_CCORRIDA',
  UPDATE_CCORRIDA: 'cCorrida/UPDATE_CCORRIDA',
  DELETE_CCORRIDA: 'cCorrida/DELETE_CCORRIDA',
  RESET: 'cCorrida/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICCorrida>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CCorridaState = Readonly<typeof initialState>;

// Reducer

export default (state: CCorridaState = initialState, action): CCorridaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CCORRIDA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CCORRIDA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CCORRIDA):
    case REQUEST(ACTION_TYPES.UPDATE_CCORRIDA):
    case REQUEST(ACTION_TYPES.DELETE_CCORRIDA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CCORRIDA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CCORRIDA):
    case FAILURE(ACTION_TYPES.CREATE_CCORRIDA):
    case FAILURE(ACTION_TYPES.UPDATE_CCORRIDA):
    case FAILURE(ACTION_TYPES.DELETE_CCORRIDA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCORRIDA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCORRIDA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CCORRIDA):
    case SUCCESS(ACTION_TYPES.UPDATE_CCORRIDA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CCORRIDA):
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

const apiUrl = 'api/c-corridas';

// Actions

export const getEntities: ICrudSearchAction<ICCorrida> = (search, page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?borrado.equals=0&page=${page}&size=${size}&sort=${sort}${search}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CCORRIDA_LIST,
    payload: axios.get<ICCorrida>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICCorrida> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CCORRIDA,
    payload: axios.get<ICCorrida>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICCorrida> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CCORRIDA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICCorrida> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CCORRIDA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICCorrida> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CCORRIDA,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
