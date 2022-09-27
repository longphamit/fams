import { Button, Col, Form, Input, Row, Spin } from "antd";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { signInAPI } from "../../connectors/AuthenConnector";
import { ACCOUNT, ACCOUNT_ID, JWT } from "../../constants/key";
import { accountActions } from "../../redux/slices/AccountSlice";
import "./styles.scss"
const validateMessages = {
    required: "${label} is required!",
    string: {
        len: "${label} must be have length with exact ${len}",
        min: "${label} must be at least ${min} characters",
    },
    number: {
        range: "${label} must be between ${min} and ${max}",
    },
};

const SignInPage = () => {
    const [isLoading, setLoading] = useState(false)
    const navigate = useNavigate()
    const dispatch=useDispatch()
    const onFinish = async ({ email, password }) => {
        try{
            setLoading(true)
            const res=await signInAPI(email,password);
            console.log(res)
            dispatch(accountActions.setAccountData(res.data.account))
            navigate("/")
            toast.success(res.message)
        }catch(e){
            console.log(e)
            toast.error(e.response.data.message)
        }finally{
            setLoading(false)
        }
        
    }
    return (
        <div className="loginPage">
            <Row align="middle">
                <Col span={8}></Col>
                <Col span={8}>
                    <div className="loginForm">
                        <Form
                            validateMessages={validateMessages}
                            name="basic"
                            labelCol={{ span: 5 }}
                            wrapperCol={{ span: 16 }}
                            initialValues={{ remember: true }}
                            onFinish={onFinish}
                            autoComplete="off"
                        >
                            <Form.Item
                                label="Email"
                                name="email"
                                rules={[{ required: true, type: "string" }]}
                            >
                                <Input />
                            </Form.Item>

                            <Form.Item
                                label="Password"
                                name="password"
                                rules={[
                                    {
                                        required: true,
                                        type: "string",
                                        min: 8,
                                    },
                                ]}
                                style={{ marginBottom: 0 }}
                            >
                                <Input.Password />
                            </Form.Item>
                            <Row justify="end" align="middle">
                                <a
                                    onClick={() => {
                                        navigate("/forgot-pass");
                                    }}
                                    style={{ paddingRight: 50 }}
                                >
                                    Forgot Password?
                                </a>
                            </Row>
                            <Row justify="center" align="middle">
                                <Form.Item style={{ marginTop: 10 }}>
                                    {isLoading ? (
                                        <Spin />
                                    ) : (
                                        <Button type="primary" htmlType="submit">
                                            Sign in
                                        </Button>
                                    )}
                                </Form.Item>
                            </Row>
                        </Form>
                    </div>
                </Col>
                <Col span={8}></Col>
            </Row>
        </div>)
}
export default SignInPage