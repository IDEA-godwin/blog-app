
import React, {useState} from "react"

export const CategorySelect = props => {

    return (
        <>
            <div>
                <label>Category</label>
                <select
                    className="post-form-input"
                    onChange={event => props.onChange(event)}
                    name={props.name} value={props.value}
                >
                    <option defaultChecked>Select Category</option>
                    { props.options }
                </select>
            </div>
        </>
    )
}

export const TitleInput = props => {

    return (
        <>
            <div>
                <label>Title</label>
                <input
                    className="post-form-input"
                    onChange={event => props.onChange(event)}
                    type="text" name={props.name} value={props.value} placeholder={props.placeholder} />
            </div>
        </>
    )
}

export const PostContent = props => {

    return (
        <>
            <div>
                <label>Content</label>
                <textarea
                    className="post-form-input"
                    onChange={event => props.onChange(event)}
                    name={props.name} rows={props.rows} value={props.postBody}
                    placeholder={props.placeholder} />
            </div>
        </>
    )
}

export const TagsList = props => {
    let [newTag, setNewTag] = useState("")

    const handleChange = event => {
        setNewTag(event.target.value)
    }

    const addTag = (event) => {
        event.preventDefault()
        let temp = [];
        temp.push(newTag)
        props.handleAddTag(temp)
        setNewTag("")
    }

    return (
        <>
            <label>Tags</label>
            <div className="tag-box">
                <ul className="tag-box-items">
                    {props.tags.map( s => {
                        return (
                            <li onClick={event => addTag(event)} className="tag" value={s} key={s}>
                                { s }
                            </li>
                        )
                    })}
                    <li className="add-tag">
                        <input
                            onChange={event => handleChange(event)}
                            name="tag" value={newTag} placeholder="Add Tag" />
                        <button onClick={event => addTag(event)} name="btn" className="add-tag-btn">
                            <img src="images/add.png" name="btn" alt={"add tag btn"} />
                        </button>
                    </li>
                </ul>
            </div>
        </>
    )
}