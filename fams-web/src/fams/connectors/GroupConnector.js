import request from "../../@app/connectors/AxiosConnector";
import { HOST } from "../../@app/constants/host";

export const getGroupByAccountIdAPI = async (id) => {
    const response = await request.get(`${HOST}/v1/groups/member/${id}`)
    return response.data
};
export const addGroupAPI = async (data)=>{
    const response = await request.post(`${HOST}/v1/groups`,data)
    return response.data
}
export const countGroupAPI = async (id) => {
    const response = await request.get(`${HOST}/v1/groups/count`)
    return response.data
};
export const getGroupByIdAPI=async(id)=>{
    const response = await request.get(`${HOST}/v1/groups/${id}`)
    return response.data
}