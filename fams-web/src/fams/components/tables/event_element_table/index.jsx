import {Col, Popconfirm, Row, Space, Table } from "antd"
import { Button } from "primereact/button"
import {
    CloseCircleFilled,
    UsergroupAddOutlined,
    EyeOutlined,
    PlusOutlined,
    PlusCircleFilled
} from "@ant-design/icons";
import { useDispatch, useSelector } from "react-redux";
import "./styles.scss"
import { memberActions } from "../../../../@app/redux/slices/MemberSlice";
import AddEventElementForm from "../../forms/add_event_element";
const EventElementTable = ({groupId}) => {
    const eventElements=useSelector(state=>state.member.eventElements)
    const dispatch=useDispatch()
    const columns = [
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            render: (text) => <p>{text}</p>,
        },
        {
            title: 'Type',
            dataIndex: 'type',
            key: 'name',
            render: (text) => <p>{text}</p>,
        },
        // {
        //     title: 'Action',
        //     key: 'action',
        //     align: "center",
        //     render: (_, record) => (
        //         <Space size="middle">
        //             <div>
        //                 <Popconfirm placement="top" title="Do you want to leave this group?" onConfirm={() => { }} okText="Yes" cancelText="No">
        //                     <Button className="p-button-danger">
        //                         <CloseCircleFilled />
        //                     </Button>
        //                 </Popconfirm>
        //             </div>
        //         </Space>
        //     ),
        // },
    ];
    const handleButtonOpenAddEventElement=()=>{
        dispatch(memberActions.setShowModalAddEventElement(true))
    }
    return (
    <div className="eventElementTable">
        <div className="actions">
                <Row>
                    <Col span={22}></Col>
                    <Col span={2}>
                    <Button onClick={handleButtonOpenAddEventElement} className="p-button-success button"><PlusOutlined className="buttonIcon" /> Element</Button></Col>
                </Row>
            </div>
        <Table style={{ borderRadius: 30 }} columns={columns} dataSource={eventElements} />
        <AddEventElementForm groupId={groupId}/>
    </div>)
}
export default EventElementTable