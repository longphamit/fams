import { Col, Descriptions, Form, Modal, Row } from "antd";
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { ListBox } from 'primereact/listbox';
import { Button } from 'primereact/button';
import { addGroupMemberAPI, getGroupByIdAPI, getGroupMemberAPI } from "../../../../connectors/GroupConnector";
import { Chart } from 'primereact/chart';
import { getLightThemeChart } from "../../../../../@app/utils/ChartUtil";
import {
    PlusOutlined,
    StarFilled
} from "@ant-design/icons";
import "./styles.scss"
import EventElementTable from "../../../../components/tables/event_element_table";
import { useDispatch, useSelector } from "react-redux";
import { memberActions } from "../../../../../@app/redux/slices/MemberSlice";
import AddEventForm from "../../../../components/forms/add_event";
import EventTable from "../../../../components/tables/eventtable";
import { getEventsAPI } from "../../../../connectors/EventConnector";
import BetForm from "../../../../components/forms/bet";
import { toast } from "react-toastify";
const GroupDetailPage = () => {
    const [group, setGroup] = useState()
    const accountId = useSelector(state => state.account.accountData.id)
    const eventElements = useSelector(state => state.member.eventElements)
    const events = useSelector(state => state.member.events)
    const eventSelectedId = useSelector(state => state.member.eventSelected)
    const [members, setMembers] = useState()
    const { id } = useParams()
    const dispatch = useDispatch()
    const getGroupDetail = async () => {
        const res = await getGroupByIdAPI(id)
        setGroup(res.data)
        dispatch(memberActions.setEventElements(res.data.eventElements))
    }
    const getEvents = async () => {
        const res = await getEventsAPI(id, true, true)
        dispatch(memberActions.setEvents(res.data))
    }
    const getGroupMembers = async () => {
        const res = await getGroupMemberAPI(id)
        setMembers(res.data)
    }
    const addGroupMember = async (memberIds) => {
        //console.log(memberIds)
        return await addGroupMemberAPI(id, { memberIds: memberIds})
    }
    const handleJoinButton = async () => {
        try {
            const res = await addGroupMember([accountId])
            init();
            toast.success("Join group success")
        } catch (e) {
            console.log(e)
            toast.error("Join group fail")
        }
    }
    const [basicData] = useState({
        labels: ['Quân', 'GLong', 'Luân', 'KietGo', 'HuyVd', 'Khoa', 'CLong'],
        datasets: [
            {
                label: 'Tổng tiền',
                backgroundColor: '#fc0303',
                data: [100, 80, 70, 60, 50, 45, 40]
            },
        ]
    });
    const handleButtonAddEvent = () => {
        dispatch(memberActions.setShowModalAddEvent(true))
    }
  
    const init=()=>{
        getGroupDetail()
        getEvents()
        getGroupMembers()
    }
    useEffect(() => {
        init()
    }, [])
    return (
        <div className="groupDetail">
            <div className="groupInfo">
                <Descriptions title="Group Info">
                    <Descriptions.Item labelStyle={{ fontWeight: "bold" }} label="Name">{group?.name}</Descriptions.Item>
                    <Descriptions.Item labelStyle={{ fontWeight: "bold" }} label="Creator"></Descriptions.Item>
                    <div>12</div>
                </Descriptions>
                <div className="count">
                    <Row gutter={[16, 16]}>
                        <Col span={12}>
                            <div className="col" id="events">
                                Events
                                <div>
                                    {events ? events.length : 0}
                                </div>
                            </div>
                        </Col>
                        <Col span={12}>
                            <div className="col" id="members">
                                Members
                                <div>
                                    {members?.length}
                                </div>
                            </div>
                        </Col>
                    </Row>
                </div>
                {
                    !members?.map(e => e.id).includes(accountId) ? <Button onClick={handleJoinButton}>Join</Button> : null
                }
            </div>
            {
                members?.map(e => e.id).includes(accountId) ?
                    <div>
                        <div>
                            <Row gutter={16}>
                                <Col span={12}>
                                    <div className="box">
                                        <h2>Top 10 Cổ Đông</h2>
                                        <Chart type="bar" data={basicData} options={getLightThemeChart.basicOptions} />
                                    </div>
                                </Col>
                                <Col span={12}>
                                    <div className="box">
                                        <h2>Members</h2>
                                        <ListBox options={members?.map(member => {
                                            return { name: member.username, code: member.id }
                                        })} listStyle={{ maxHeight: '205px' }} optionLabel="name" style={{ width: '100%' }} />
                                    </div>
                                </Col>
                            </Row>
                        </div>
                        <div style={{ marginTop: 20 }}>
                            <div className="box">
                                <h3>Event Element</h3>
                                <EventElementTable groupId={id} />
                            </div>
                            <div className="box">
                                <h3>Event</h3>
                                <EventTable groupId={id}/>
                            </div>
                        </div>
                    </div> : null
            }

            <AddEventForm groupId={id} />
            <BetForm key={eventSelectedId} />
        </div>)
}
export default GroupDetailPage