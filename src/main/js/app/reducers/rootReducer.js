
import { combineReducers } from "redux";

import registerReducer from "./registerReducer";
import AppState from "./AppState";
import loginReducer from "./loginReducer";
import postReducer from "./postReducer";

export const rootReducer = combineReducers({
    appState: AppState,
    register: registerReducer,
    login: loginReducer,
    post: postReducer
})

export default rootReducer