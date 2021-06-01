
import React from "react"
import { connect } from "react-redux"
import { Link, withRouter } from "react-router-dom";

import { loginUser } from "../middleware";



export class Login extends React.Component {
    state = {
        email: !this.props.isUserRegistered ? "" : this.props.registeredUser.body.email,
        password: ""
    }

    componentDidMount() {
        console.log(this.props)
    }

    onChange(event) {
        this.setState( {
            [event.target.name]: event.target.value
        })
    }

    onClick(event) {
        let { email, password } = this.state
        this.props.dispatch(loginUser({email, password}))
        setTimeout(() => {
            console.log(this.props.appUser)
        }, 1000)
        this.setState({
            email: "",
            password: ""
        })
    }

    render() {
        let state = this.state
        return(
            <div className="login-container form">
                <h4 className="login-title">Login User Account</h4>
                <input
                    onChange={event => this.onChange(event)}
                    type="email" name="email" className="input"
                    placeholder="Enter Email" value={state.email} />
                <input
                    onChange={event => this.onChange(event)}
                    type="password" name="password" className="input"
                    placeholder="Enter Password" value={state.password} />
                <input
                    onClick={event => this.onClick(event)} type="button"
                    className="submit-button button" value="Submit" />
                <span style={{display: "block"}}>
                    No Account? <Link to="/register">Register</Link>
                </span>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        isUserRegistered: state.register.isUserRegistered,
        registeredUser: state.register.user,
        appUser: state.appState.user
    }
}

export default withRouter(connect(mapStateToProps)(Login));