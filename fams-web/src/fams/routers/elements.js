import { useNavigate } from "react-router-dom";
import { ACCESS_TOKEN, PATH } from "../../@app/constants/key";
import LoginPage from "../../@app/pages/login";
import UnAuthenPage from "../../@app/pages/unauthen";
import { localStorageGetReduxState } from "../../@app/utils/StorageUtil";
import HomePage from "../pages/member/home";

const RouterAppElement = ({
    component: Component,
    layout: Layout,
    isLayout = false,
    authen,
    path,
    roles,
  }) => {

    const access_token = localStorageGetReduxState()?.account?.jwt;
    sessionStorage.setItem(PATH, path);
    if (!access_token && authen) {
      if (path === "/signin") {
        return <LoginPage />;
      }
      return <UnAuthenPage />;
    }
    
    // if (access_token && authen) {
    //   // return <Navigate to="/admin-home"/>
    //   const role = localStorageGetReduxState().auth.role;
    //   if (roles) {
    //     if (!roles.includes(role)) {
    //       return <UnAuthenPage />;
    //     }
    //   }
    // }
    return isLayout && Layout ? (
      <Layout>
        <Component />
      </Layout>
    ) : (
      <Component />
    );
  };
  export default RouterAppElement;