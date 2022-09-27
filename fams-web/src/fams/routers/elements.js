import { ACCESS_TOKEN, PATH } from "../../@app/constants/key";
import LoginPage from "../../@app/pages/login";
import UnAuthenPage from "../../@app/pages/unauthen";

const RouterAppElement = ({
    component: Component,
    layout: Layout,
    isLayout = false,
    authen,
    path,
    roles,
  }) => {
    console.log(path)
    // const access_token = localStorage.getItem(ACCESS_TOKEN);
    // sessionStorage.setItem(PATH, path);
    // if (!access_token && authen) {
    //   if (path === "/") {
    //     return <LoginPage />;
    //   }
    //   return <UnAuthenPage />;
    // }
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