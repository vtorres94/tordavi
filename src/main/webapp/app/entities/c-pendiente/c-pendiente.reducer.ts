import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICPendiente, defaultValue } from 'app/shared/model/c-pendiente.model';

export const ACTION_TYPES = {
  FETCH_CPENDIENTE_LIST: 'cPendiente/FETCH_CPENDIENTE_LIST',
  FETCH_CPENDIENTE: 'cPendiente/FETCH_CPENDIENTE',
  CREATE_CPENDIENTE: 'cPendiente/CREATE_CPENDIENTE',
  UPDATE_CPENDIENTE: 'cPendiente/UPDATE_CPENDIENTE',
  DELETE_CPENDIENTE: 'cPendiente/DELETE_CPENDIENTE',
  RESET: 'cPendiente/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICPendiente>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CPendienteState = Readonly<typeof initialState>;

// Reducer

export default (state: CPendienteState = initialState, action): CPendienteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CPENDIENTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CPENDIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CPENDIENTE):
    case REQUEST(ACTION_TYPES.UPDATE_CPENDIENTE):
    case REQUEST(ACTION_TYPES.DELETE_CPENDIENTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CPENDIENTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CPENDIENTE):
    case FAILURE(ACTION_TYPES.CREATE_CPENDIENTE):
    case FAILURE(ACTION_TYPES.UPDATE_CPENDIENTE):
    case FAILURE(ACTION_TYPES.DELETE_CPENDIENTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPENDIENTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CPENDIENTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CPENDIENTE):
    case SUCCESS(ACTION_TYPES.UPDATE_CPENDIENTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CPENDIENTE):
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

const apiUrl = 'api/c-pendientes';

// Actions

export const getEntities: ICrudGetAllAction<ICPendiente> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CPENDIENTE_LIST,
    payload: axios.get<ICPendiente>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICPendiente> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CPENDIENTE,
    payload: axios.get<ICPendiente>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICPendiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CPENDIENTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICPendiente> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CPENDIENTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICPendiente> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CPENDIENTE,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
