// create page for announcements
import axios from 'axios'
import { useEffect, useState } from 'react'
import "./Announcements.css"

interface Announcement {
    message: string;
    date: any;
}

interface AnnouncementsProps {
    courseId: number;
}

// function username from local storage
function getUsername() {
    let username = localStorage.getItem("username");
    if (username === null) {
        return "";
    }        
    return username;
}

function Announcements({courseId}: AnnouncementsProps) {
    const [announcements, setAnnouncements] = useState<Announcement[]>([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/courses/${courseId}/announcements`).then(response => {
            console.log(response.data);
            setAnnouncements(response.data);
        }).catch(error => {
            console.log('Errors retriving Announcements', error);
        });
    }, []);
    return (
        <>
            <div>
                <h1 className = "title">Announcements</h1>
                <div className="announcement-container">
                    {announcements.map((announcement, index) => {
                        return (
                            <div key={index} className="announcement-card">
                                <h3>{announcement.message}</h3>
                                <p>Date: {announcement.date}</p>
                            </div>
                        );
                    })}
                </div>
            </div>
        </>
    );
}

export default Announcements;