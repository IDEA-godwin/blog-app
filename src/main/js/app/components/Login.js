
import React from "react"
import { Link, withRouter } from "react-router-dom";

import { loginUser } from "../requestActions";



export class Login extends React.Component {
    state = {
        email: "",
        password: "",
        error: ""
    }

    onChange(event) {
        this.setState( {
            [event.target.name]: event.target.value
        })
    }

    onClick(event) {
        let { email, password } = this.state
        loginUser({email, password}).then(({ status, data, err }) => {
            if(status === 200){
                localStorage.setItem("token", data.body.token)
                this.props.history.push("/admin")
            } else {
                this.setState({
                    email: "",
                    password: "",
                    error: "email or password incorrect"
                })
            }
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
                <span style={{display: "block", color: "red"}}>
                    { state.error}
                </span>
                <span style={{display: "block"}}>
                    No Account? <Link to="/register">Register</Link>
                </span>
            </div>
        )
    }
}

export default withRouter(Login);