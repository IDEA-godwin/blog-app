
import React, {useState, useEffect} from "react"
import { Switch, Route, Redirect, withRouter } from "react-router-dom"

import Home from "./components/Home"
import About from "./components/About"
import Admin from "./Admin"
import PostForm from "./components/postForm/PostForm"
import Login from "./components/Login"

import { Error } from "./components/Error"
import Register from "./components/Register";


export class App extends React.Component {

    render() {
        return(
            <>
                <Switch>
                    <Route
                        path = "/" exact
                        render = {() => <Home /> } />
                    <Route
                        path = "/About" exact
                        render = {() => <About /> } />
                    <Route
                        path = "/admin" exact
                        render = {() => <Admin /> } />
                    <Route
                        path = "/create-post" exact
                        render={() => <PostForm {...this.props} />} />
                    <Route
                        path="/register"
                        render={() => <Register /> } />
                    <Route
                        path = "/login" exact
                        render = {() => <Login /> } />

                    <Redirect from = "/*" to = "/error" />
                    <Route
                        path = "/error"
                        render = {() => <Error /> } />
                </Switch>
            </>
        )
    }
}

export default withRouter(App)