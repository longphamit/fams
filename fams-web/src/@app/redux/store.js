import { configureStore } from "@reduxjs/toolkit";
import { STORE } from "../constants/key";
import member from "./slices/MemberSlice"
import account from "./slices/AccountSlice"
import { localStorageGetReduxState,localStorageSaveReduxState } from "../utils/StorageUtil";

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