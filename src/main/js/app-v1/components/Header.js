
import React from "react"
import { NavLink } from "react-router-dom"

import { FontAwesomeIcon }  from "@fortawesome/react-fontawesome"
import { faFacebookF} from "@fortawesome/free-brands-svg-icons/faFacebookF";
import { faTwitter } from "@fortawesome/free-brands-svg-icons/faTwitter";
import { faLinkedin } from "@fortawesome/free-brands-svg-icons/faLinkedin";

const Header = props => {

    return (
        <div className="app-header container">
            <div className="header-top">
                <div className="logo">
                    <span><NavLink className="link" to="/">logo</NavLink></span>
                </div>
                <div className="social-handle">
                    <ul>
                        <li><span><FontAwesomeIcon icon={faFacebookF} /></span></li>
                        <li><span><FontAwesomeIcon icon={faTwitter} /></span></li>
                        <li><span><FontAwesomeIcon icon={faLinkedin} /></span></li>
                    </ul>
                </div>
            </div>
            <div className="header-middle">
                <h1>The Green <span>Roses'</span> Blog</h1>
                <h4>...After the roses bleed only GREEN remained</h4>
            </div>
        </div>
    )
}

export default Header