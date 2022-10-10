import { Form, Modal, Select, DatePicker, Input, InputNumber, Radio } from "antd"
import TextArea from "antd/lib/input/TextArea";
import { Button } from "primereact/button";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { memberActions } from "../../../../@app/redux/slices/MemberSlice";
import { addEventAPI, getEventElementsOfEventAPI } from "../../../connectors/EventConnector";
import { getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
const { RangePicker } = DatePicker;
const BetForm = () => {
    const [eventElements, setEventElements] = useState()
    const eventId = useSelector(state => state.member.eventSelected)
    const isShowModalBet = useSelector(state => state.member.isShowModalBet)
    const dispatch = useDispatch()
    const handleModalBetCancel = () => {
        dispatch(memberActions.setShowModalBet(false))
    }
    const getEventElements = async () => {
        if(eventId){
            const res = await getEventElementsOfEventAPI(eventId);
            console.log(res)
            setEventElements(res.data)
        }
        
    }
    const onFinishBet = () => {

    }
    useEffect(() => {
        getEventElements()
    }, [])
    return (<>
        <Modal
            key={eventId}
            open={isShowModalBet}
            title="BET"
            style={{ borderRadius: 20 }}
            onCancel={() => { handleModalBetCancel() }}
            footer={[]}
            width="600px"
        >
            <Form
                key={eventId}
                labelCol={{ span: 10 }}
                wrapperCol={{ span: 20 }}
                onFinish={onFinishBet}
                initialValues={{ name: eventId }}
            >
                
                <Form.Item
                    label="Bet"
                    name="bet"
                    rules={[{ required: true, message: 'Please choose element' }]}
                >
                    <Radio.Group>
                        {
                            eventElements ? eventElements.map(e => {
                                return (<Radio value={e.id}>{e.name}</Radio>)

                            }) : null
                        }
                    </Radio.Group>
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
export default BetForm