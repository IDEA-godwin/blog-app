
import React from "react";
import ReactDom from "react-dom";
import {BrowserRouter as Router} from "react-router-dom";

import App from "./app-v1/App";

ReactDom.render(
    <Router>
        <App />
    </Router>, document.querySelector("#app")
);