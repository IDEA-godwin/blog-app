
import { initialState } from "./AppState"
import {
    GET_ALL_POST_FAILED,
    GET_ALL_POST_LOADING,
    GET_ALL_POST_SUCCESS } from "../actionTypes";


const postReducer = (state = initialState, action) => {
    switch (action.type) {
        case GET_ALL_POST_LOADING: return {
            loading: true
        }
        case GET_ALL_POST_SUCCESS: return {
            loading: false,
            success: true,
            error: null
        }
        case GET_ALL_POST_FAILED: return {
            loading: false,
            error: action.payload
        }
        default: return {
            ...state
        }
    }
}

export default postReducer