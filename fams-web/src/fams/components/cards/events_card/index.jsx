import { Col, Row } from "antd"
import { useEffect, useState } from "react"
import { getEventsAPI } from "../../../connectors/EventConnector"
import { Button } from "primereact/button";
import { RiTeamFill } from 'react-icons/ri';
import {
    StarFilled,
    ClockCircleOutlined,
    ScheduleOutlined,
    DollarCircleOutlined
} from "@ant-design/icons";
import "./styles.scss"
import { convertDate, convertTimeByString } from "../../../../@app/utils/DateUtil";
import { convertConcurrency } from "../../../../@app/utils/ConcurrencyUtil";
const EventsCards = ({events}) => {
    return (<div className="eventsCards">
        <Row gutter={[16, 16]}>
            {
                events?.map(e => {
                    return (
                        <Col span={6}>
                            <div className="event">
                                <div className="name">{e.name}</div>
                                <div className="eventSubInfo"><RiTeamFill style={{marginLeft:10,marginRight:10}}/>{e.group.name}</div>
                                <div className="eventSubInfo"><ClockCircleOutlined style={{ margin: 10 }} /> {convertTimeByString(e.fromDate)}</div>
                                <div className="eventSubInfo"><ScheduleOutlined style={{ margin: 10 }} />{convertDate(e.fromDate)}</div>
                                <div className="eventSubInfo"><DollarCircleOutlined style={{ margin: 10 }} />{convertConcurrency(e.fee)}</div>
                                <Button className="p-button-success button" style={{ height: 10 }}>Join</Button>
                                <Button className="p-button-infor button" style={{ height: 10 }}>Detail</Button>
                            </div>
                        </Col>
                    )
                })
            }
        </Row>
    </div>)
}
export default EventsCards;