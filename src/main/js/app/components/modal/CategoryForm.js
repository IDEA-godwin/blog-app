
import React, { useState } from "react"
import { addCategory } from "../../requestActions";

const CategoryForm = props => {
    let [category, setCategory] = useState("")

    const handleChange = event => {
        setCategory(event.target.value)
    }

    const handleSubmit = event => {
        event.preventDefault()
        addCategory(category).then(({ status, data }) => {
            if(status !== 200) {
                alert("failed to add category: encountered an error of status " + res.status)
            }
            if(status === 200) {
                alert("category added")
            }
        })
        props.handleCategoryShow()
    }

    return (
        <div
            className="modal"
            style={{visibility: props.showCategoryForm ? "visible" : "collapse",}}>
            <div className="modal-content  post-form">
                <div className="modal-header">
                    <div className="modal-title">
                        <h1>Add Category</h1>
                    </div>
                </div>
                <div className="modal-body">
                   <input
                       className="post-form-input"
                       onChange={event => handleChange(event)}
                       value={category} placeholder="Category Name" />
                </div>
                <div className="modal-footer post-form-footer">
                    <input onClick={props.handleCategoryShow} type="button" className="button" value="Cancel" />
                    <input onClick={event => handleSubmit(event)} type="submit" value="Submit" className="button" />
                </div>
            </div>
        </div>
    )
}

export default CategoryForm