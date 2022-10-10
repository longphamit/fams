import { Route } from "react-router-dom";

const router = [
  {
    component: MemberHomePage,
    path: "/",
    isLayout: true,
    authen: true,
    roles: [""]
  },
]
export default router;