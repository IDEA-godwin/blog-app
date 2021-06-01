
import React from "react"


class Post extends React.Component {

    render() {
        let { post } = this.props
        return (
            <div className="post-box">
                <h4>{ post.title }</h4>
                <p>{ post.postBody }</p>
            </div>
        );
    }
}

export default Post