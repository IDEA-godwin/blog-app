
import React from "react"
import { Switch, Route } from "react-router-dom"

import App from "./App"
import Home from "./components/Home"
import Login from "./components/Login";
import Register from "./components/Register";
import Header from "./components/Header";

export class Routes extends React.Component {
    render() {
        return(
            <>
                <Switch>
                    <Route path="/">
                        <App />
                    </Route>
                    {/*<Route path="/login">*/}
                    {/*    <Login />*/}
                    {/*</Route>*/}
                    {/*<Route path="/register">*/}
                    {/*    <Register />*/}
                    {/*</Route>*/}
                </Switch>
            </>
            )
    }
}

export default Routes