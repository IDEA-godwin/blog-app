

import {
    LOGIN_USER_FAILED,
    LOGIN_USER_LOADING,
    LOGIN_USER_SUCCESS } from "../actionTypes";

export const loginUserLoading = () => {
    return {
        type: LOGIN_USER_LOADING
    }
}

export const loginUserSuccess = () => {
    return {
        type: LOGIN_USER_SUCCESS
    }
}

export const loginUserFailed = error => {
    return {
        type: LOGIN_USER_FAILED,
        error
    }
}