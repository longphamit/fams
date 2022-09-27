import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isLoading:false
};

const member = createSlice({
    name: "member",
    initialState,
    reducers: {
        setIsLoading:(state,actions)=>{
            state.isLoading=actions.payload
        }
        
    },
});

const { reducer, actions } = member;
export const memberActions = actions;
export default reducer;