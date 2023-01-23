import { useState } from "react"
import { useEffect } from "react"
import { useParams } from "react-router-dom"
import { getEventMembersAPI } from "../../../../connectors/EventConnector"

const EventDetailPage=()=>{
    const [members,setMembers]=useState()
    const {id}= useParams()
    useEffect(()=>{
        getEventMembers()
    },[])
    const getEventMembers=async()=>{
        const res=await getEventMembersAPI(id)
        console.log(res.data)
        setMembers(res.data)
    }
    return (
    <>
        <div>
            
        </div>
    </>
    )
}
export default EventDetailPage