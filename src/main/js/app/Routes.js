
import React from "react"
import { Switch, Route } from "react-router-dom"

import App from "./App"

export class Routes extends React.Component {
    render() {
        return(
            <React.Fragment>
                <Switch>
                    <Route exact path="/">
                        <App />
                    </Route>
                </Switch>
            </React.Fragment>
            )
    }
}

export default Routes