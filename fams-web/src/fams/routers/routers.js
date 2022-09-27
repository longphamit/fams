import { Route, BrowserRouter as Router, Routes } from "react-router-dom"
import NotFoundPage from "../../@app/pages/notfound"
import AuthenTemplate from "../../@app/templates/AuthenTemplate"
import RouterAppElement from "./elements"
import routers from "./routerConfigs"
const AppRouter=()=>{
    return (
        <Router>
          <Routes>
            {routers?.map((r) => (
              <Route
                key={r.path}
                path={r.path}
                element={
                  <RouterAppElement
                    component={r.component}
                    isLayout={r.isLayout}
                    layout={r.layout}
                    authen={r.authen}
                    path={r.path}
                    roles={r.roles}
                  />
                }
              />
            ))}
            <Route path="*" element={<NotFoundPage/>}/>
            {/* <Route index element={<AppElement 
            component={HomePage}
                    isLayout={true}
                    layout={AuthenTemplate}
                    authen={true}
                    path={HOME_PAGE_PATH}
                    roles={[ROLE_ADMIN,ROLE_LOCATION_OWNER,ROLE_SERVICE_PROVIDER]} />} /> */}
          </Routes>
        </Router>
    )
}
export default AppRouter