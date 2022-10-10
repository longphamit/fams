import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    isLoading: false,
    isShowModalAddEvent: false,
    isShowModalAddEventElement: false,
    isShowModalBet: false,
    eventElements: [],
    events: [],
    eventSelected:undefined
};

const member = createSlice({
    name: "member",
    initialState,
    reducers: {
        setIsLoading: (state, actions) => {
            state.isLoading = actions.payload
        },
        setEvents: (state, actions) => {
            state.events = actions.payload
        },
        setEventElements: (state, actions) => {
            state.eventElements = actions.payload
        },
        setShowModalAddEvent: (state, actions) => {
            state.isShowModalAddEvent = actions.payload
        },
        setShowModalAddEventElement: (state, actions) => {
            state.isShowModalAddEventElement = actions.payload
        },
        setShowModalBet: (state, actions) => {
            state.isShowModalBet = actions.payload
        },
        setEventSelected:(state,actions)=>{
            state.eventSelected=actions.payload
        }
    },
});

const { reducer, actions } = member;
export const memberActions = actions;
export default reducer;