
import { HOST } from "../constants/host";
import request from "./AxiosConnector";
export const signInAPI=async(email,password)=>{
    const response= await request.post(`${HOST}/v1/auth/sign-in`,{
         email,
         password
     })
     return response.data
 };