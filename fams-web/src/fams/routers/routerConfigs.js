
import SignInPage from "../../@app/pages/login";
import SignUpPage from "../../@app/pages/regist";
import AuthenTemplate from "../../@app/templates/AuthenTemplate";
import EventPage from "../pages/member/event";
import EventDetailPage from "../pages/member/event/detail";
import GroupPage from "../pages/member/group";
import GroupDetailPage from "../pages/member/group/detail";
import HomePage from "../pages/member/home";


const routers = [
  {
    component: HomePage,
    path: "/",
    isLayout: true,
    layout: AuthenTemplate,
    authen: true,
    roles: [""]
  },
  {
    component: GroupPage,
    path: "/group",
    isLayout: true,
    layout: AuthenTemplate,
    authen: true,
    roles: [""]
  },
  {
    component: GroupDetailPage,
    path: "/group/:id",
    isLayout: true,
    layout: AuthenTemplate,
    authen: true,
    roles: [""]
  },
  {
    component: EventPage,
    path: "/event",
    isLayout: true,
    layout: AuthenTemplate,
    authen: true,
    roles: [""]
  },
  {
    component: EventDetailPage,
    path: "/event/:id",
    isLayout: true,
    layout: AuthenTemplate,
    authen: true,
    roles: [""]
  },
  {
    component: SignInPage,
    path: "/signin",
    isLayout: false,
    authen: false,
    roles: [""]
  },
  {
    component: SignUpPage,
    path: "/signup",
    isLayout: false,
    authen: false,
    roles: [""]
  }
]
export default routers;