
import axios from "axios";
import {getAllPostsFailed, getAllPostsLoading, getAllPostsSuccess, userLoggedIn} from "./actions/AppAction";
import {
    registerUserFailure,
    registerUserLoading,
    registerUserSuccess } from "./actions/registerAction";
import {
    loginUserFailed,
    loginUserLoading,
    loginUserSuccess } from "./actions/loginAction";


const baseUrl = "http://localhost:8080"

export const registerUser = payload => {
    return (dispatch) => {
        dispatch(registerUserLoading())
        return axios.post(
            baseUrl + "/register",
            {
                fullName: payload.fullName,
                email: payload.email,
                password: payload.password
            }
        ).then(res => {
            dispatch(registerUserSuccess(res.data))
        }).catch(err => {
            dispatch(registerUserFailure(err.toJSON))
        })
    }
}

export const loginUser = payload => {
    return dispatch => {
        dispatch(loginUserLoading())
        return axios.post(
            baseUrl + "/login",
            {
                email: payload.email,
                password: payload.password
            }
        ).then(res => {
            dispatch(loginUserSuccess())
            dispatch(userLoggedIn(res.data))
        }).catch(err => {
            dispatch(loginUserFailed(err.toJSON))
        })
    }
}

export const getAllPost = () => {
    return dispatch => {
        dispatch(getAllPostsLoading())
        return axios.get(baseUrl + "/posts")
            .then(res => {
                dispatch(getAllPostsSuccess(res.data))
            }).catch(err => {
                dispatch(getAllPostsFailed(err.toJSON))
            })
    }
}