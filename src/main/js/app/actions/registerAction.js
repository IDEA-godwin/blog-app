
import {
    REGISTER_USER_LOADING,
    REGISTER_USER_SUCCESS,
    REGISTER_USER_FAILED } from "../actionTypes";

export const registerUserLoading = () => {
    return {
        type: REGISTER_USER_LOADING
    }
}

export const registerUserSuccess = payload => {
    return {
        type: REGISTER_USER_SUCCESS,
        payload
    }
}

export const registerUserFailure = error => {
    return {
        type: REGISTER_USER_FAILED,
        error
    }
}


