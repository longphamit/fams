import { Col, DatePicker, Form, Input, InputNumber, Modal, Row, Select, TimePicker, Tooltip } from "antd"
import { Button } from "primereact/button"
import { useState } from "react"
import moment from 'moment';
import {
    CloseCircleFilled,
    UsergroupAddOutlined,
    EyeOutlined,
    PlusCircleFilled
} from "@ant-design/icons";
import { handleNumberInput } from "../../../../@app/utils/InputUtil";
import { getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import TextArea from "antd/lib/input/TextArea";
const { RangePicker } = DatePicker;
const EventTable = () => {
    const [isOpenModalAddEvent, setOpenModalAddEvent] = useState()
    const accountId = useSelector(state => state.account.accountData.id)
    const [groups, setGroups] = useState()
    const onFinishAddEvent = async (values) => {
        console.log(values)
    }
    const getGroups = async () => {
        try {
            const res = await getGroupByAccountIdAPI(accountId)
            setGroups(res.data)
        } catch (e) {
            console.log(e)
        }

    }
    useEffect(() => {
        getGroups()
    }, [])
    return (
        <div>
            <div style={{ marginBottom: 20 }}>
                <Row>
                    <Col span={22}>
                    </Col>
                    <Col span={2}>
                        <Button onClick={() => { setOpenModalAddEvent(true) }} className="p-button-success"><PlusCircleFilled /></Button>
                    </Col>
                </Row>
            </div>
            <Modal
                open={isOpenModalAddEvent}
                title="Add Event"
                style={{borderRadius:20}}
                onCancel={() => { setOpenModalAddEvent(false) }}
                footer={[]}
                width="600px"
            >
                <Form
                    labelCol={{ span: 4 }}
                    wrapperCol={{ span: 20 }}
                    onFinish={onFinishAddEvent}
                >
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input group name' }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item label="Type"
                        name="type"
                        rules={[{ required: true, message: 'Please choose type' }]}
                    >
                        <Select>
                            <Select.Option value="BET">BET</Select.Option>
                            <Select.Option value="BEER">BEER</Select.Option>
                            <Select.Option value="FOOD">FOOD</Select.Option>
                        </Select>
                    </Form.Item>
                    {
                        groups ? <Form.Item label="Group"
                            name="group"
                            rules={[{ required: true, message: 'Please choose type' }]}
                        >
                            <Select>
                                {
                                    groups.map(group => {
                                        return <Select.Option value={group.id}>{group.name}</Select.Option>
                                    })
                                }
                            </Select>
                        </Form.Item> : null
                    }

                    <Form.Item label="Date" name="date"
                    rules={[{ required: true, message: 'Please choose date' }]}
                    >
                        <RangePicker 
                        picker="date"
                        showTime={{
                            hideDisabledOptions: true,
                            format: "HH:mm",
                           
                        }} />
                    </Form.Item>
                    <Form.Item
                        label="Fee"
                        name="fee"
                        rules={[{ required: true, message: 'Please input fee' }]}
                    >
                        <InputNumber min={0} />
                    </Form.Item>
                    <Form.Item
                        label="Description"
                        name="description"
                        rules={[{ required: true, message: 'Please input fee' }]}
                    >
                        <TextArea />
                    </Form.Item>
                    <Form.Item wrapperCol={{ offset: 10, span: 16 }}>
                        <Button className="p-button-success" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>

        </div>)
}
export default EventTable