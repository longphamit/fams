import { Col, Popconfirm, Row, Space, Table, Tag } from "antd";
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
import { getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";

const GroupTable = () => {
    const [groups,setGroups]=useState()
    const accountId=useSelector(state=>state.account.accountData.id)
    const getGroups=async()=>{
       const rs= await getGroupByAccountIdAPI(accountId);
       setGroups(rs.data)
    }
    useEffect(() => {
        getGroups()
    },[]);
    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            render: (text) => <a>{text}</a>,
        },
        {
            title: 'Roles',
            dataIndex: 'roles',
            key: 'name',
            render: (text) => <a>{text}</a>,
        },
        // {
        //     title: 'Tags',
        //     key: 'tags',
        //     dataIndex: 'tags',
        //     render: (_, { tags }) => (
        //         <>
        //             {tags.map((tag) => {
        //                 return (
        //                     <Tag color="blue">
        //                         {tag.toUpperCase()}
        //                     </Tag>
        //                 );
        //             })}
        //         </>
        //     ),
        // },
        {
            title: 'Action',
            key: 'action',
            align: "center",
            render: (_, record) => (
                <Space size="middle">
                    <div>
                        <Button className="p-button-info">
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
                        <Button className="p-button-success"><PlusCircleFilled /></Button>
                    </Col>
                </Row>
            </div>
            <Table style={{ borderRadius: 30 }} columns={columns} dataSource={groups} />
        </div>)
}
export default GroupTable