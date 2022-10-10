import { Form, Modal, Select ,DatePicker, Input, InputNumber} from "antd"
import TextArea from "antd/lib/input/TextArea";
import { Button } from "primereact/button";
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import { memberActions } from "../../../../@app/redux/slices/MemberSlice";
import { addGroupEventElementAPI, getGroupByAccountIdAPI } from "../../../connectors/GroupConnector";
const { RangePicker } = DatePicker;
const AddEventElementForm=({groupId})=>{
    const dispatch=useDispatch()
    const isShowModalAddEventElement= useSelector(state=>state.member.isShowModalAddEventElement)
    
    const onFinishAddEventElement = async (values) => {
        try{
           const res= await addGroupEventElementAPI(groupId,values)
           toast.success("Add success")
           dispatch(memberActions.setShowModalAddEventElement(false))
        }catch(e){
            console.log(e)
        }
    }
   
    const handleModalAddEventElementCancel=()=>{
        dispatch(memberActions.setShowModalAddEventElement(false))
    }
    
    useEffect(() => {
    }, [])
    return (<>
        <Modal
                open={isShowModalAddEventElement}
                title="Add Event Element"
                style={{borderRadius:20}}
                onCancel={handleModalAddEventElementCancel}
                footer={[]}
                width="600px"
            >
                <Form
                    labelCol={{ span: 4 }}
                    wrapperCol={{ span: 20 }}
                    onFinish={onFinishAddEventElement}
                >
                    <Form.Item
                        label="Name"
                        name="name"
                        rules={[{ required: true, message: 'Please input name' }]}
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
                    <Form.Item
                        label="Description"
                        name="description"
                        rules={[{ required: true, message: 'Please input description' }]}
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
export default AddEventElementForm