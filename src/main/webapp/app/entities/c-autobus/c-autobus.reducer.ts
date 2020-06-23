import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICAutobus, defaultValue } from 'app/shared/model/c-autobus.model';

export const ACTION_TYPES = {
  FETCH_CAUTOBUS_LIST: 'cAutobus/FETCH_CAUTOBUS_LIST',
  FETCH_CAUTOBUS: 'cAutobus/FETCH_CAUTOBUS',
  CREATE_CAUTOBUS: 'cAutobus/CREATE_CAUTOBUS',
  UPDATE_CAUTOBUS: 'cAutobus/UPDATE_CAUTOBUS',
  DELETE_CAUTOBUS: 'cAutobus/DELETE_CAUTOBUS',
  RESET: 'cAutobus/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICAutobus>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CAutobusState = Readonly<typeof initialState>;

// Reducer

export default (state: CAutobusState = initialState, action): CAutobusState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CAUTOBUS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAUTOBUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAUTOBUS):
    case REQUEST(ACTION_TYPES.UPDATE_CAUTOBUS):
    case REQUEST(ACTION_TYPES.DELETE_CAUTOBUS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CAUTOBUS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAUTOBUS):
    case FAILURE(ACTION_TYPES.CREATE_CAUTOBUS):
    case FAILURE(ACTION_TYPES.UPDATE_CAUTOBUS):
    case FAILURE(ACTION_TYPES.DELETE_CAUTOBUS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAUTOBUS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAUTOBUS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAUTOBUS):
    case SUCCESS(ACTION_TYPES.UPDATE_CAUTOBUS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAUTOBUS):
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

const apiUrl = 'api/c-autobuses';

// Actions

export const getEntities: ICrudGetAllAction<ICAutobus> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CAUTOBUS_LIST,
    payload: axios.get<ICAutobus>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICAutobus> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAUTOBUS,
    payload: axios.get<ICAutobus>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICAutobus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAUTOBUS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICAutobus> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAUTOBUS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICAutobus> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAUTOBUS,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
