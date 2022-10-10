import request from "../../@app/connectors/AxiosConnector";
import { HOST } from "../../@app/constants/host";


export const getEventsAPI = async (groupId,isMember,isCreator) => {
    const response = await request.get(`${HOST}/v1/events?groupId=${groupId}&isMember=${isMember}&isCreator=${isCreator}`)
    return response.data
};
export const addEventAPI=async(data)=>{
    const response = await request.post(`${HOST}/v1/events`,data)
    return response.data
}
export const getEventElementsOfEventAPI=async(eventId)=>{
    const response = await request.get(`${HOST}/v1/events/${eventId}/event-elements`)
    return response.data
}
export const addEventMemberAPI = async (id,data)=>{
    const response = await request.post(`${HOST}/v1/events/${id}/members`,data)
    return response.data
}