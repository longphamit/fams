import { Col, Row } from "antd"
import "./styles.scss"
import { RiTeamFill } from 'react-icons/ri';
import {
    StarFilled,
    ClockCircleOutlined,
    ScheduleOutlined,
    DollarCircleOutlined
} from "@ant-design/icons";
const HomePage = () => {
    return (<div className="memberHomePage">
        <div className="dashBoard">
            <div className="firstRow">
                <Row gutter={[16, 16]}>
                    <Col span={12}>
                        <div className="col" id="groups">
                            <RiTeamFill /> Groups
                            <div>
                                12
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
                            Events Comming Soon
                        </div></Col>
                    <Col>
                        <div className="more">
                            more
                        </div>
                    </Col>
                </Row>
            </div>
            <div>
                <Row gutter={[16, 16]}>
                    <Col span={6}>
                        <div className="eventCommingSoon">
                            <div> MU vs ARS</div>
                            <div className="eventSubInfo"><ClockCircleOutlined style={{ margin: 10 }} /> 20:00</div>
                            <div className="eventSubInfo"><ScheduleOutlined style={{ margin: 10 }} />20/3/2022</div>
                            <div className="eventSubInfo"><DollarCircleOutlined style={{ margin: 10 }} />20.000 vnd</div>
                        </div>

                    </Col>
                    <Col span={6}>
                        <div className="eventCommingSoon">
                            <div> MU vs ARS</div>
                            <div className="eventSubInfo"><ClockCircleOutlined style={{ margin: 10 }} /> 20:00</div>
                            <div className="eventSubInfo"><ScheduleOutlined style={{ margin: 10 }} />20/3/2022</div>
                            <div className="eventSubInfo"><DollarCircleOutlined style={{ margin: 10 }} />20.000 vnd</div>
                        </div>

                    </Col>
                    <Col span={6}>
                        <div className="eventCommingSoon">
                            <div> MU vs ARS</div>
                            <div className="eventSubInfo"><ClockCircleOutlined style={{ margin: 10 }} /> 20:00</div>
                            <div className="eventSubInfo"><ScheduleOutlined style={{ margin: 10 }} />20/3/2022</div>
                            <div className="eventSubInfo"><DollarCircleOutlined style={{ margin: 10 }} />20.000 vnd</div>
                        </div>

                    </Col>
                    <Col span={6}>
                        <div className="eventCommingSoon">
                            <div> MU vs ARS</div>
                            <div className="eventSubInfo"><ClockCircleOutlined style={{ margin: 10 }} /> 20:00</div>
                            <div className="eventSubInfo"><ScheduleOutlined style={{ margin: 10 }} />20/3/2022</div>
                            <div className="eventSubInfo"><DollarCircleOutlined style={{ margin: 10 }} />20.000 vnd</div>
                        </div>

                    </Col>

                </Row>
            </div>
        </div>
    </div>)
}
export default HomePage