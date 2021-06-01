
import React from 'react'
import { connect } from "react-redux"
import { Switch, Route } from "react-router-dom"

import Header from "./components/Header"
import Home from "./components/Home"
import Login from "./components/Login"
import Register from "./components/Register"


class App extends React.Component {

    render() {
        let state = this.state
        return (
            <React.Fragment>
                <div id="app-header">
                    <Header isLoggedIn={state.isLoggedIn} />
                </div>
                <div id="app-body">
                    <Switch>
                        <Route
                            exact path="/"
                            render={() => <Home {...this.props} /> } />
                        <Route
                            path="/login"
                            render={() => <Login {...this.props} /> } />
                        <Route
                            path="/register"
                            render={() => <Register {...this.props} /> } />
                    </Switch>
                </div>
            </React.Fragment>
        )
    }
}

const mapStateToProps = state => {
    return {
        isUserLoggedIn: state.appState.isUserLoggedIn,
        loggedInUser: state.appState.user,
    }
}

export default connect(mapStateToProps)(App);

