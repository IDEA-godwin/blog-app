
import {
    LOGIN_USER_FAILED,
    LOGIN_USER_LOADING,
    LOGIN_USER_SUCCESS } from "../actionTypes";
import {initialState} from "./AppState";

const loginReducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN_USER_LOADING: return {
            loading: !state.loading
        }
        case LOGIN_USER_SUCCESS: return {
            loading: !state.loading,
            success: !state.success,
        }
        case LOGIN_USER_FAILED: return {
            loading: !state.loading,
            error: action.error
        }
        default: return {
            ...state
        }
    }
}

export default loginReducer