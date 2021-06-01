
import { USER_LOGGED_IN } from "../actionTypes"

import {
    GET_ALL_POST_LOADING,
    GET_ALL_POST_SUCCESS,
    GET_ALL_POST_FAILED } from "../actionTypes"

export function userLoggedIn(payload) {
    return {
        type: USER_LOGGED_IN,
        payload
    }
}

export function getAllPostsLoading() {
    return {
        type: GET_ALL_POST_LOADING
    }
}

export function getAllPostsSuccess(payload) {
    return {
        type: GET_ALL_POST_SUCCESS,
        payload
    }
}

export function getAllPostsFailed(payload) {
    return {
        type: GET_ALL_POST_FAILED,
        payload
    }
}