
// const config = {
//     baseUrl: process.env.SERVER_API_URL
// }
import axios from "axios"

const baseUrl = "http://localhost:8080/api"

export const registerUser = payload => {
    return axios.post(
        baseUrl + `/register`,
        {
            fullName: payload.fullName,
            email: payload.email,
            password: payload.password
        })
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
           return { err: err.toJSON }
        })
}

export const loginUser = payload => {
    return axios.post(
        baseUrl + "/login",
        {
            email: payload.email,
            password: payload.password
        })
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const createPost = payload => {
    return axios.post(
        baseUrl + "/posts",
        {
            category: payload.category,
            title: payload.title,
            postBody: payload.postBody,
            tags: payload.tags
        },
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const getAllPost = () => {
    console.log(process.env)
    return axios.get(baseUrl + "/posts")
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const editPost = (payload) => {
    return axios.put(
        baseUrl + `/posts/${payload.postId}`,
        payload,
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const getCategories = () => {
    return axios.get(baseUrl + "/posts/categories", {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }})
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const getTags = () => {
    return axios.get(baseUrl + "/posts/tags", {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }})
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const addCategory = categoryName => {
    return axios.post(
        baseUrl + "/posts/categories",
        {
            name: categoryName
        },
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}

export const getLoggedInUser = () => {
    return axios.get(baseUrl + "/users/me", {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("token")
        }})
        .then(res => {
            return {
                data: res.data,
                status: res.status
            }
        })
        .catch(err => {
            return { err: err.toJSON }
        })
}