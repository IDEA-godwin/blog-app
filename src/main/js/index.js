
import React from "react";
import ReactDom from "react-dom";
import {BrowserRouter as Router, useHistory} from "react-router-dom";

import store from "./app/store"
import Routes from "./app/Routes";
import {Provider} from "react-redux";

ReactDom.render(
    <Provider store={store}>
        <Router>
            <Routes />
        </Router>
    </Provider>, document.querySelector("#app")
);