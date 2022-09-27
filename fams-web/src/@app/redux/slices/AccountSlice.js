import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountData:""
};

const account = createSlice({
    name: "account",
    initialState,
    reducers: {
        setAccountData:(state,actions)=>{
            state.accountData=actions.payload
        }
        
    },
});

const { reducer, actions } = account;
export const accountActions = actions;
export default reducer;