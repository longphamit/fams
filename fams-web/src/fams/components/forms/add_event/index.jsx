import { Form, Modal, Select, DatePicker, Input, InputNumber } from "antd"
import { useForm } from "antd/es/form/Form";
import TextArea from "antd/lib/input/TextArea";
import { Button } from "primereact/button";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import { memberActions } from "../../../../@app/redux/slices/MemberSlice";
import { addEventAPI, getEventsAPI } from "../../../connectors/EventConnector";
import { getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
const { RangePicker } = DatePicker;
const AddEventForm = ({ groupId }) => {
    const [groups, setGroups] = useState()
    const dispatch = useDispatch()
    const [addEventForm] = useForm()
    const isShowModalAddEvent = useSelector(state => state.member.isShowModalAddEvent)
    const eventElements = useSelector(state => state.member.eventElements)
    const accountId = useSelector(state => state.account.accountData.id)
    const onFinishAddEvent = async (values) => {
        try {
            values["fromDate"] = values.date[0]
            values["toDate"] = values.date[1]
            const res = await addEventAPI(values);
            dispatch(memberActions.setShowModalAddEvent(false))
            await getEvents();
            addEventForm.resetFields()
            toast.success("add event success")
        } catch (e) {
            console.log(e)
            toast.error("add event fail")
        }
    }
    const getEvents = async () => {
        const res = await getEventsAPI(groupId, false, false)
        dispatch(memberActions.setEvents(res.data))
    }

    const handleModalAddEventCancel = () => {
        dispatch(memberActions.setShowModalAddEvent(false))
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
    return (<>
        <Modal
            open={isShowModalAddEvent}
            title="Add Event"
            style={{ borderRadius: 20 }}
            onCancel={() => { handleModalAddEventCancel() }}
            footer={[]}
            width="600px"
        >
            <Form
                form={addEventForm}
                labelCol={{ span: 4 }}
                wrapperCol={{ span: 20 }}
                onFinish={onFinishAddEvent}
                initialValues={{ group: groupId }}
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

                <Form.Item label="Group"
                    name="groupId"
                    rules={[{ required: true, message: 'Please choose type' }]}

                >
                    <Select>
                        {
                            groups?.map(group => {
                                return <Select.Option value={group.id}>{group.name}</Select.Option>
                            })
                        }
                    </Select>
                </Form.Item>
                <Form.Item
                    rules={[{ required: true, message: 'Please choose elements' }]}
                    label="Elements"
                    name="elements">

                    <Select
                        mode="tags"
                        placeholder="Please select"

                        style={{ width: '100%' }}
                    >
                        {
                            eventElements?.map(element => {
                                return <Select.Option key={element.id} value={element.id}>{element.name}</Select.Option>
                            })
                        }

                    </Select>


                </Form.Item>
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
    </>)
}
export default AddEventForm