
import React from "react"
import { connect } from "react-redux";
import { Link, withRouter } from "react-router-dom"
import { registerUser } from "../middleware";

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
        let { history, error } = this.props
        let { fullName, email, password,  } = this.state
        this.props.dispatch(registerUser({ fullName, email, password }))
        this.setState({
            fullName: "",
            email: "",
            password: ""
        })
        setTimeout(() => {
            if(error === null) history.push("/login")
        }, 3000)
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
                    onClick={event => this.onClick(event)} type="button"
                    className="submit-button button" value="Submit" />
                <span style={{display: "block"}}>
                    have an Account? <Link to="/login">Login</Link>
                </span>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        loading: state.register.loading,
        user: state.register.user,
        error: state.register.error
    }
}

export default withRouter(connect(mapStateToProps)(Register))