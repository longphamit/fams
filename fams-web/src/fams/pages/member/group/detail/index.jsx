import { Descriptions } from "antd";
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { getGroupByIdAPI } from "../../../../connectors/GroupConnector";

const GroupDetailPage = () => {
    const [group, setGroup] = useState()
    const [eventElements, setEventElements] = useState();
    const [events, setEvents] = useState()
    const { id } = useParams()
    const getGroupDetail = async() => {
        const res=await getGroupByIdAPI(id)
        console.log(res)
        setGroup(res.data)
    }
    useEffect(() => {
        getGroupDetail()
    }, [])
    return (
        <div>
            <Descriptions title="Group Info">
                <Descriptions.Item label="Name">{group?.name}</Descriptions.Item>
                <Descriptions.Item label="Creator"></Descriptions.Item>
                
                
            </Descriptions>
        </div>)
}
export default GroupDetailPage