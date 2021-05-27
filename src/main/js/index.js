
import React from "react";
import ReactDom from "react-dom";
import { BrowserRouter as Router } from "react-router-dom";

import Routes from "./app/Routes";

ReactDom.render(
    <Router>
        <Routes />
    </Router>, document.querySelector("#app")
);