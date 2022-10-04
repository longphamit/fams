import { Col, Form, Input, Modal, Popconfirm, Row, Space, Table, Tag } from "antd";
import { Button } from 'primereact/button';
import "./styles.scss"
import {
    CloseCircleFilled,
    UsergroupAddOutlined,
    EyeOutlined,
    PlusCircleFilled
} from "@ant-design/icons";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { addGroupAPI, getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const GroupTable = () => {
    const [groups, setGroups] = useState()
    const navigate=useNavigate()
    const [isOpenModalAddGroup, setOpenModalAddGroup] = useState(false)
    const accountId = useSelector(state => state.account.accountData.id)
    const getGroups = async () => {
        const rs = await getGroupByAccountIdAPI(accountId);
        setGroups(rs.data)
    }

    useEffect(() => {
        getGroups()
    }, []);
    const onFinishAddGroup = async ({ name }) => {
        try {
            const res = await addGroupAPI({ name: name });
            await getGroups()
            toast.success(`Add event ${name} sucess`)
            setOpenModalAddGroup(false)
        } catch (e) {
            console.log(e.response)
            toast.error(e.response.data.message)
        }
    }
    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            render: (text) => <a>{text}</a>,
        },
        {
            title: 'Action',
            key: 'action',
            align: "center",
            render: (_, record) => (
                <Space size="middle">
                    <div>
                        <Button className="p-button-info" onClick={()=>{navigate(`/group/${record.id}`)}}>
                            <EyeOutlined />
                        </Button>
                    </div>
                    <div>
                        <Button >
                            <UsergroupAddOutlined />
                        </Button>
                    </div>
                    <div>
                        <Popconfirm placement="top" title="Do you want to leave this group?" onConfirm={() => { }} okText="Yes" cancelText="No">
                            <Button className="p-button-danger">
                                <CloseCircleFilled />
                            </Button>
                        </Popconfirm>
                    </div>
                </Space>
            ),
        },
    ];

    return (
        <div>
            <div style={{ marginBottom: 20 }}>
                <Row>
                    <Col span={22}>
                    </Col>
                    <Col span={2}>
                        <Button style={{height:50}} onClick={() => { setOpenModalAddGroup(true) }} className="p-button-success">
                            <PlusCircleFilled />
                        </Button>
                    </Col>
                </Row>
            </div>
            <Table style={{ borderRadius: 30 }} columns={columns} dataSource={groups} />
            <Modal
                open={isOpenModalAddGroup}
                title="Add Group"
                onCancel={() => { setOpenModalAddGroup(false) }}
                footer={[]}
            >
                <Form
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    onFinish={onFinishAddGroup}
                >
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input group name' }]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <Button className="p-button-success" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </div>)
}
export default GroupTable