import { Col, Row } from "antd"
import "./styles.scss"
import { RiTeamFill } from 'react-icons/ri';
import {
    StarFilled,
    ClockCircleOutlined,
    ScheduleOutlined,
    DollarCircleOutlined
} from "@ant-design/icons";
import { Button } from "primereact/button";
import { getEventsAPI } from "../../../connectors/EventConnector";
import { useEffect, useState } from "react";
import EventsCards from "../../../components/cards/events_card";
import { countGroupAPI } from "../../../connectors/GroupConnector";
const HomePage = () => {
    const [joinedEvent,setJoinedEvent] = useState();
    const [notJoinedEvent,setNotJoinedEvent]=useState();
    const [countGroup,setCountGroup]=useState();
    const getJoinedEvent=async()=>{
        const res=await getEventsAPI(true)
        setJoinedEvent(res.data)
    }
    const getNotJoinedEvent=async()=>{
        const res=await getEventsAPI(false)
        setNotJoinedEvent(res.data)
    }
    const countGroupFunc=async()=>{
        const res=await countGroupAPI()
        setCountGroup(res.data)
    }
    useEffect(()=>{
        getJoinedEvent()
        getNotJoinedEvent()
        countGroupFunc()
    },[])
    return (
    <div className="memberHomePage">
        <div className="dashBoard">
            <div className="firstRow">
                <Row gutter={[16, 16]}>
                    <Col span={12}>
                        <div className="col" id="groups">
                            <RiTeamFill /> Groups
                            <div>
                                {countGroup}
                            </div>
                        </div>
                    </Col>
                    <Col span={12}>
                        <div className="col" id="events">
                            <StarFilled /> Events
                            <div>
                                12
                            </div>
                        </div>
                    </Col>
                </Row>
            </div>
        </div>
        <div className="body">
            <div className="rowTitle">
                <Row>
                    <Col span={22}>
                        <div className="title">
                            Event Joined
                        </div></Col>
                    <Col>
                        <div className="more">
                            more
                        </div>
                    </Col>
                </Row>
            </div>
            <div>
                <EventsCards events={joinedEvent}/>
            </div>
        </div>
        <div className="body">
            <div className="rowTitle">
                <Row>
                    <Col span={22}>
                        <div className="title">
                            Event Not Joined
                        </div></Col>
                    <Col>
                        <div className="more">
                            more
                        </div>
                    </Col>
                </Row>
            </div>
            <div>
                <EventsCards events={notJoinedEvent}/>
            </div>
        </div>
    </div>)
}
export default HomePage