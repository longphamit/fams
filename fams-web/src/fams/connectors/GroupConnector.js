import request from "../../@app/connectors/AxiosConnector";
import { HOST } from "../../@app/constants/host";

export const getGroupByAccountIdAPI = async (id) => {
    const response = await request.get(`${HOST}/v1/groups/member/${id}`)
    return response.data
};