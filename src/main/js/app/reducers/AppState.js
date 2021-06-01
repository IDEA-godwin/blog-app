
import {GET_ALL_POST_SUCCESS, USER_LOGGED_IN} from "../actionTypes"

export const initialState = {
    isUserRegistered: false,
    isLoggedIn: false,
    isAdmin: false,
    user: null,
    posts: []
}

const AppState = (state = initialState, action) => {
    switch (action.type) {
        case USER_LOGGED_IN: return {
            isLoggedIn: true,
            user: action.payload.body,
        }
        case GET_ALL_POST_SUCCESS: return {
            posts: action.payload
        }
        default: return {
            ...state
        }
    }
}

export default AppState