import logo from './logo.svg';
import './App.css';
import "./scss/styles.scss";

import { RouterProvider } from 'react-router-dom';
import AppRouter from './fams/routers/routers';
import { Provider } from 'react-redux';
import store from './@app/redux/store';
import { ToastContainer } from 'react-toastify';


function App() {
  return (
    <Provider store={store}>
      <AppRouter/>
      <ToastContainer />
    </Provider>
  );
}

export default App;
