// create page for announcements
import axios from 'axios'
import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import './Announcements.css'

interface Announcement {
    announcementId: number;
    message: string;
    date: any;
}

// function username from local storage
function getUserRole() {
    let userRole = localStorage.getItem("role");
    if (userRole === null) {
        return "";
    }        
    return userRole;
}

function Announcements() {
    const { courseId } = useParams<{courseId: string}>();
    const [announcements, setAnnouncements] = useState<Announcement[]>([]);
    const navigate = useNavigate();
    const userRole = getUserRole();

    // delete announcement 
    const deleteAnnouncement = async (announcementId: number) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this announcement?");
        if(confirmDelete){
            try {
                await axios.delete(`http://localhost:8080/courses/${courseId}/announcements/${announcementId}`);
                
                // remove the deleted announcement from the list
                setAnnouncements(announcements.filter(announcement => announcement.announcementId !== announcementId));
            } catch (error) {
                console.error('Error deleting announcement', error);
            }
        }
        
    }

    useEffect(() => {
        const fetchAnnouncements = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/courses/${courseId}/announcements`);
                setAnnouncements(response.data);
            } catch (error) {
                console.error('Error fetching announcements', error);
            }
        }
    
        fetchAnnouncements();
    }, [courseId]);

    const handleNewAnnouncement = () => {
        navigate(`/courses/${courseId}/announcements/new-announcement`);
    }

    return (
        <>
            <div>
                <h1 className = "title">Announcements</h1>
                {
                    userRole === "TEACHER" &&
                    <button onClick={handleNewAnnouncement}>New Announcement</button>
                }
                <div className="announcement-container">
                    {announcements?.map((announcement, index) => {
                        return (
                            <div key={index} className="announcement-card">
                                <h3>{announcement.message}</h3>
                                <p>Date: {announcement.date}</p>
                                {
                                    userRole === "TEACHER" &&
                                    <button className="delete-button" onClick={() => deleteAnnouncement(announcement.announcementId)}>Delete</button>
                                }
                            </div>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default Announcements;