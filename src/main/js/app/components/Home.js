
import React from "react"

import Header from "./Header"
import Posts from "./Posts";
import {getAllPost} from "../requestActions";

class Home extends React.Component {

    state = {
        loading: true,
        posts: null
    }

    componentDidMount() {
        getAllPost().then(({status, data}) => {
            this.setState({
                loading: false,
                posts: status === 200 ? data.body : null
            })
        })
    }

    render() {
        let { loading, posts } = this.state

        if(loading){
            return (<span>Loading...</span>)
        }
        return (
            <>
                <Header />
                <article className="container">
                    <div className="content">
                        <Posts posts={posts} />
                    </div>
                </article>
            </>
        );
    }
}

export default Home