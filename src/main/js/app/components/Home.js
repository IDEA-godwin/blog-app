
import React from "react"

import Post from "./Post";
import {getAllPost} from "../middleware";


class Home extends React.Component {
    state = {
        posts: [
            // {
            //     title: "the static post",
            //     postBody: "it is just weird sometimes i just dont get it"
            // }
        ]
    }

    componentDidMount() {
        let getPosts = getAllPost()
        setTimeout(() => {
            this.setState({
                posts: getPosts.then(res => res.data)
            })
        }, 5000)
    }

    getAllPost(posts) {
        return posts.length === 0 ? "there is no post to show"
            : console.log(posts)
    }

    render() {
        let { posts } = this.state
        return (
            <div className="home-container">
                { this.getAllPost(posts) }
            </div>
        );
    }
}

export default Home