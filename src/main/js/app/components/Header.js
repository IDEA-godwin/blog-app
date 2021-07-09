
import React from "react"
import { NavLink, withRouter } from "react-router-dom"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebookF} from "@fortawesome/free-brands-svg-icons/faFacebookF";
import { faTwitter } from "@fortawesome/free-brands-svg-icons/faTwitter";
import { faLinkedin } from "@fortawesome/free-brands-svg-icons/faLinkedin";

export class Header extends React.Component{

    state = {
        isActive: false,
        display: this.isHome() ? "block" : "none"
    }

    isHome() {
        return this.props.location.pathname === "/"
    }

    render() {
        let state = this.state
        return (
            <div className="header container">
                <div className="header-top">
                    <span className="logo">Logo</span>
                    <ul className="list social-icons">
                        <li className="button icon"><FontAwesomeIcon icon={faTwitter} /></li>
                        <li className="button icon"><FontAwesomeIcon icon={faFacebookF} /></li>
                        <li className="button icon"><FontAwesomeIcon icon={faLinkedin} /></li>
                    </ul>
                </div>
                <div style={{display: state.display}} className="header-middle">
                    <h1>The Green Developer's Blog</h1>
                    <h3>...<span style={{color: "green"}}>Roses</span> are Green</h3>
                </div>
                <div className="header-top">
                    <span><NavLink to="/About" className="about button">The Green Developer</NavLink></span>
                </div>
            </div>
        );
    }
}

export default withRouter(Header)