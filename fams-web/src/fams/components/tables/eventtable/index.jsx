import { Col, DatePicker, Form, Input, InputNumber, Modal, Popconfirm, Row, Select, Space, Table, TimePicker, Tooltip } from "antd"
import { Button } from "primereact/button"
import { useState } from "react"
import moment from 'moment';
import {
    CloseCircleFilled,
    UsergroupAddOutlined,
    EyeFilled,
    PlusOutlined,
    PlusCircleFilled,
    SmileFilled
} from "@ant-design/icons";
import { handleNumberInput } from "../../../../@app/utils/InputUtil";
import { getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import TextArea from "antd/lib/input/TextArea";
import AddEventForm from "../../forms/add_event";
import { memberActions } from "../../../../@app/redux/slices/MemberSlice";
import "./styles.scss"
import { useNavigate } from "react-router-dom";
import { addEventMemberAPI, getEventsAPI } from "../../../connectors/EventConnector";
import { toast } from "react-toastify";
import { convertConcurrency } from "../../../../@app/utils/ConcurrencyUtil";
const { RangePicker } = DatePicker;
const EventTable = ({groupId}) => {
    const navigate = useNavigate()
    const events = useSelector(state => state.member.events)
    const accountId = useSelector(state => state.account.accountData.id)
    const dispatch = useDispatch()
    const handleButtonAddEvent = () => {
        dispatch(memberActions.setShowModalAddEvent(true))
    }
    const handleButtonBet = (eventId) => {
        dispatch(memberActions.setShowModalBet(true))
        dispatch(memberActions.setEventSelected(eventId))
    }
    const addEventMember = async (eventId, memberIds) => {
        return await addEventMemberAPI(eventId, {memberIds:memberIds})
    }
    const getEvents=async()=>{
        const res=await getEventsAPI(groupId,false,false)
        console.log(res)
        dispatch(memberActions.setEvents(res.data))
    }
    const handleButtonJoin = async (eventId) => {
       try{
        await addEventMember(eventId, [accountId]);
        await getEvents()
        toast.success("Join event success")
       }catch(e){
            console.log(e)
            toast.error("Join fail")
       }
    }
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
        {
            title: 'Fee',
            dataIndex: 'fee',
            key: 'name',
            render: (text) => <p>{convertConcurrency(text)} </p>,
        },
        {
            title: 'Members',
            key: 'name',
            render: (_, record) => (
                <>{record.members.length}</>
            )
        },
        
        {
            title: 'Action',
            key: 'action',
            align: "center",
            render: (_, record) => (
                <Space size="middle">

                    <Button className="p-button-info button" onClick={() => { navigate(`/event/${record.id}`) }}>
                        <EyeFilled style={{ marginRight: 10 }} /> Detail
                    </Button>
                    {
                        !record.joined ?
                            <Button className="p-button-infor button" onClick={() => { handleButtonJoin(record.id) }}>
                                <SmileFilled style={{ marginRight: 10 }} /> JOIN
                            </Button> : null
                    }
                    {
                        record.joined & record.type === "BET" ? <div>
                            <Button className="p-button-warning button" onClick={() => { handleButtonBet(record.id) }}>
                                <SmileFilled style={{ marginRight: 10 }} /> BET
                            </Button>
                        </div> : null
                    }
                    {/* <div>
                        <Popconfirm placement="top" title="Do you want to leave this group?" onConfirm={() => { }} okText="Yes" cancelText="No">
                            <Button className="p-button-danger">
                                <CloseCircleFilled />
                            </Button>
                        </Popconfirm>
                    </div> */}

                </Space>
            ),
        },
    ];
    return (
        <div className="eventTable">
            <div className="actions">
                <Row>
                    <Col span={22}></Col>
                    <Col span={2}>
                        <Button onClick={handleButtonAddEvent} className="p-button-success button"><PlusOutlined className="buttonIcon" /> Event</Button></Col>
                </Row>
            </div>
            <AddEventForm />
            <Table pagination={{ pageSize: 5 }} style={{ borderRadius: 30 }} columns={columns} dataSource={events} />
        </div>)
}
export default EventTable