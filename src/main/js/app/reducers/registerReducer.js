
import {
    REGISTER_USER_LOADING,
    REGISTER_USER_SUCCESS,
    REGISTER_USER_FAILED } from "../actionTypes"

let initialState = {
    loading: false,
    isUserRegistered: false,
    user: null,
    error: null
}

function registerReducer(state = initialState, action) {
    switch (action.type) {
        case REGISTER_USER_LOADING: return {
            loading: !state.loading
        }
        case REGISTER_USER_SUCCESS: {
            return {
                loading: !state.loading,
                isUserRegistered: true,
                user: action.payload,
                error: null
            }
        }
        case REGISTER_USER_FAILED: return {
            loading: !state.loading,
            error: action.error
        }
        default: return {
            ...state
        }
    }
}

export default registerReducer