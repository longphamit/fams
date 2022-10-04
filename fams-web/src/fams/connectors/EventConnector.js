import request from "../../@app/connectors/AxiosConnector";
import { HOST } from "../../@app/constants/host";


export const getEventsAPI = async (isMember) => {
    const response = await request.get(`${HOST}/v1/events?isMember=${isMember}`)
    return response.data
};
export const addEventAPI=async()=>{
    
}