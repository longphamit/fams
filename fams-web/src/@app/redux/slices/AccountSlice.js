import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    accountData: "",
    jwt: ""
};

const account = createSlice({
    name: "account",
    initialState,
    reducers: {
        setAccountData: (state, actions) => {
            state.accountData = actions.payload
        },
        setJwt: (state, actions) => {
            state.jwt = actions.payload
        }

    },
});

const { reducer, actions } = account;
export const accountActions = actions;
export default reducer;