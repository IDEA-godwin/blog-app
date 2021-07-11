
import React from "react"
import { Link, withRouter } from "react-router-dom"
import { registerUser } from "../requestActions";

class Register extends React.Component {

    state = {
        fullName: "",
        email: "",
        password: "",
        error: ""
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    onClick(event) {
        event.preventDefault()
        let {fullName, email, password} = this.state
        registerUser({fullName, email, password}).then(res => {
            if(res.message === "success")
                this.props.history.push("/login")
        })
        this.setState({
            fullName: "",
            email: "",
            password: "",
            error: "invalid credentials"
        })
    }

    render() {
        let state = this.state
        return (
            <div className="register-container form">
                <h4>Fill all fields to Register</h4>
                <input
                    onChange={event => this.onChange(event)}
                    required={true}
                    type="text" name="fullName" className="input"
                    placeholder="Enter Full Name" value={state.fullName} />
                <input
                    onChange={event => this.onChange(event)}
                    required={true}
                    type="email" name="email" className="input"
                    placeholder="Enter Email" value={state.email} />
                <input
                    onChange={event => this.onChange(event)}
                    required={true}
                    type="password" name="password" className="input"
                    placeholder="Enter Password" value={state.password} />
                <span style={{display: "block"}}>{ state.error }</span>
                <input
                    onClick={event => this.onClick(event)} type="submit"
                    className="submit-button button" value="Submit" />
                <span style={{display: "block"}}>
                    have an Account? <Link to="/login">Login</Link>
                </span>
            </div>
        )
    }
}

export default withRouter(Register)