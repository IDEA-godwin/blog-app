
import React from "react"
import {Link, Redirect} from "react-router-dom"
import {getAllPost, editPost, getLoggedInUser} from "./requestActions";

import Posts from "./components/Posts"
import CategoryForm from "./components/modal/CategoryForm"
import PostForm from "./components/postForm/PostForm"

class Admin extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            posts: null,
            showPostForm: false,
            showCategoryForm: false,
            loading: true,
            user: null,
            error: null
        }
        this.handlePostShow = this.handlePostShow.bind(this)
        this.handleCategoryShow = this.handleCategoryShow.bind(this)
    }

    componentDidMount() {
        getLoggedInUser().then(({status, data, err}) => {
            this.setState({
                loading: status === 200,
                user: status === 200 ? data.body : null,
                error: status !== 200 ? err : null
            })
        })
        setTimeout(() => {
            if(this.state.user) {
                getAllPost().then(({status, data}) => {
                    this.setState({
                        loading: false,
                        posts: status === 200 ? data.body : null
                    })
                })
            }
        }, 1000)
    }

    editPost(payload) {
        editPost(payload).then(({status, data, err}) => {
            if(err) {
                alert("request failed with error status: " + status)
            }
            if(status === 200 && data.message === "success") {
                alert("post edited")
            }
        })
    }

    handlePostShow() {
        this.setState({
            showPostForm: !this.state.showPostForm
        })
    }

    handleCategoryShow() {
        this.setState({
            showCategoryForm: !this.state.showCategoryForm
        })
    }

    loadDashboard(loading, user, error) {
        if(!loading && user !== null) {
            let state = this.state
            return (
                <>
                    <header className="header container">
                        <h1>The Green Blog</h1>
                        <div className="header-top">
                            <ul className="list">
                                <li className="button">Posts</li>
                                <li className="button">categories</li>
                                <li className="button">Tags</li>
                            </ul>
                        </div>
                    </header>
                    <div className="container">
                        <div style={{textAlign: "right"}}>
                            <span>
                                <Link className="button create-button" to="/create-post">Create New Post</Link>
                            </span>
                            <span
                                onClick={this.handleCategoryShow}
                                className="button create-button"
                            >
                                Create New Categories
                            </span>
                            <CategoryForm
                                showCategoryForm={state.showCategoryForm}
                                handleCategoryShow={this.handleCategoryShow}/>
                        </div>
                        <div className="content">
                            <Posts isAdmin={true} editPost={this.editPost} posts={this.state.posts} />
                        </div>
                    </div>
                </>
            )
        }
        if(error)
            return <Redirect to="/login" />
        return <div>Loading...</div>
    }


    render() {
        let {loading, user, error} = this.state
        return this.loadDashboard(loading, user, error)
    }
}

export default Admin