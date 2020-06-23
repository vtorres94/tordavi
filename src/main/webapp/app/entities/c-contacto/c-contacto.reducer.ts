import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICContacto, defaultValue } from 'app/shared/model/c-contacto.model';

export const ACTION_TYPES = {
  FETCH_CCONTACTO_LIST: 'cContacto/FETCH_CCONTACTO_LIST',
  FETCH_CCONTACTO: 'cContacto/FETCH_CCONTACTO',
  CREATE_CCONTACTO: 'cContacto/CREATE_CCONTACTO',
  UPDATE_CCONTACTO: 'cContacto/UPDATE_CCONTACTO',
  DELETE_CCONTACTO: 'cContacto/DELETE_CCONTACTO',
  RESET: 'cContacto/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICContacto>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type CContactoState = Readonly<typeof initialState>;

// Reducer

export default (state: CContactoState = initialState, action): CContactoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CCONTACTO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CCONTACTO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CCONTACTO):
    case REQUEST(ACTION_TYPES.UPDATE_CCONTACTO):
    case REQUEST(ACTION_TYPES.DELETE_CCONTACTO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CCONTACTO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CCONTACTO):
    case FAILURE(ACTION_TYPES.CREATE_CCONTACTO):
    case FAILURE(ACTION_TYPES.UPDATE_CCONTACTO):
    case FAILURE(ACTION_TYPES.DELETE_CCONTACTO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCONTACTO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_CCONTACTO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CCONTACTO):
    case SUCCESS(ACTION_TYPES.UPDATE_CCONTACTO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CCONTACTO):
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

const apiUrl = 'api/c-contactos';

// Actions

export const getEntities: ICrudGetAllAction<ICContacto> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CCONTACTO_LIST,
    payload: axios.get<ICContacto>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ICContacto> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CCONTACTO,
    payload: axios.get<ICContacto>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICContacto> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CCONTACTO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICContacto> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CCONTACTO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICContacto> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CCONTACTO,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
