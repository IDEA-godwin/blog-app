
import React from "react"
import {Link, NavLink } from "react-router-dom";

export class Header extends React.Component{

    state = {
        style: {
            display:  !this.props.isLoggedIn ? "inline-block" : "none"
        }
    }

    render() {
        let state = this.state
        return (
            <>
                <div className="header-menu-button">
                    <span className="menu-button button">menu</span>
                </div>
                <div className="header-title">
                    <h3 className="title">Blogging Is Cool</h3>
                </div>
                <div style={state.style} className="login-button button">
                    <NavLink to="/login">Sign In</NavLink>
                </div>
            </>
        );
    }
}

export default Header;