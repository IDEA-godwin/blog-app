
import React from "react"


class Posts extends React.Component {

    state = {
        display: this.props.isAdmin ? "inline-block" : "none",
    }

    emptyPosts(posts) {
        if(!posts === true || typeof posts == "undefined")
        return (
            <div className="empty-posts">Empty Posts</div>
        )
    }

    postPreviews(posts) {
        return posts.map( p => {
            return (
                <div className="preview-box" key={p.postId}>
                    <div className="post-img">
                        <img src="" alt="post-img" />
                    </div>
                    <div className="post-content">
                        <div className="post-content-top">
                            <h2>{p.title}</h2>
                            <p>{p.postBody}</p>
                        </div>
                        <div className="post-content-bottom">
                            <span>19 claps</span>
                        </div>
                        <div className="admin-corner" style={{display: this.state.display}}>
                            <button className="edit-btn"><Link to="/create-post">Edit</Link></button>
                            <button className="delete-btn">Delete</button>
                        </div>
                    </div>
                </div>
            )
        })
    }

    render() {
        let { posts } = this.props
        return !posts === true ? this.emptyPosts(posts)
            : this.postPreviews(posts)
    }
}

export default Posts