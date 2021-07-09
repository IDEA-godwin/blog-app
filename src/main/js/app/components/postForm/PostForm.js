
import React from "react"

import { getCategories, createPost } from "../../requestActions"
import { CategorySelect, PostContent, TitleInput, TagsList } from "./postForm-components";

class PostForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            categoriesOption: null,
            category: "Select Category",
            title: "",
            postBody: "",
            tags: [],
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.onChange = this.onChange.bind(this)
        this.handleAddTag = this.handleAddTag.bind(this)
    }

    componentDidMount() {
        getCategories().then(({ status, data }) => {
            this.setState({
                categoriesOption: status === 200 ? data.body : null
            })
            if(status !== 200) {
                alert("category options did not load")
            }
        })
    }

    onChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    onSubmit = (event) => {
        event.preventDefault()
        let { category, title, postBody, tags } = this.state
        createPost({category, title, postBody, tags}).then(({ status }) => {
            if(status !== 200) {
                alert("failed to add category: encountered an error of status " + status)
            }else if(status === 200) {
                alert("category added")
            } else alert("request not fulfilled")
        })
            this.props.history.push("/admin")
    }

    handleAddTag = (tags) => {
        this.setState({
            tags: this.state.tags.concat(tags)
        })
    }

    categoryOptions(categories) {
        if(!categories || categories.length === 0) {
            return null
        }
        return categories.map(s => {
            return (
                <option value={s.name} key={s.id}>
                    { s.name }
                </option>
            )
        })
    }

    render() {
        return (
            <div className="modal">
                <form className="modal-content">
                    <h1 className="form-title">Create Post</h1>
                    <div className="post-form-body">
                        <CategorySelect
                            name="category" value={this.state.category}
                            options={this.categoryOptions(this.state.categoriesOption)}
                            onChange={this.onChange} />
                        <TitleInput
                            name="title" type="text" value={this.state.title}
                            placeholder="Title" onChange={this.onChange} />
                        <PostContent
                            onChange={this.onChange}
                            name="postBody" rows={10} value={this.state.postBody}
                            placeholder="...Post content goes here" />
                        <TagsList
                            handleAddTag={this.handleAddTag}
                            tags={this.state.tags} />
                    </div>
                    <div className="post-form-footer">
                        <input onClick={() => {
                            this.props.history.push("/admin")
                        }} type="button" className="button" value="Cancel" />
                        <input onClick={event => this.onSubmit(event)} type="button" value="submit form" />
                    </div>
                </form>
            </div>
        );
    }
}

export default PostForm