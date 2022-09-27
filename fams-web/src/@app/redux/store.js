import { configureStore } from "@reduxjs/toolkit";
import { STORE } from "../constants/key";
import member from "./slices/MemberSlice"
import account from "./slices/AccountSlice"
const localStorageGetReduxState = () => {
    const serializedState = localStorage.getItem(STORE);
    if (!serializedState) return undefined;
    return JSON.parse(serializedState);
};
const localStorageSaveReduxState = (state) => {
    const serializedState = JSON.stringify(state);
    return localStorage.setItem(STORE, serializedState);
};

const rootReducer = {
    member,
    account
};
const store = configureStore({
    reducer: rootReducer,
    preloadedState: localStorageGetReduxState()
});
store.subscribe(() => {
    localStorageSaveReduxState(store.getState())
})


export default store;