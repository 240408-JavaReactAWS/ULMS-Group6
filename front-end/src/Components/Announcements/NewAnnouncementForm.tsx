// create a form for new announcements
import axios from 'axios';
import { SyntheticEvent, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './NewAnnouncementForm.css';

interface RouteParams {
    courseId: string;
    [key: string]: string | undefined;
}

function NewAnnouncementForm() {
    const [message, setMessage] = useState('');
    const date = new Date().toISOString().split('T')[0].replace(/-/g, '/'); // Today's date in 'yyyy/MM/dd' format
    
    const navigate = useNavigate();
    const { courseId } = useParams<RouteParams>();

    let updateMessage = (e: any) => {
        setMessage((e.target as HTMLInputElement).value);
    }

    let updateDate = (e: any) => {
        setMessage((e.target as HTMLInputElement).value);
    }

    let submit = async (e: SyntheticEvent) => {
        e.preventDefault();
        try{
            await axios.post(`http://localhost:8080/courses/${courseId}/announcements`, {message:message, date:date}, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        navigate(`/courses/${courseId}`)
        }catch(error){
            console.error("Error adding new announcement",error);
        }
        
    }

    return (
        <form onSubmit={submit} className="new-announcement-form">
            <h1> Add New Announcement</h1>
            <label htmlFor="message">Message:</label>
            <input type="text" id="message" name="message" required onChange={updateMessage} />
            <label htmlFor='date'>Today's Date:</label>
            <input type='date' id='date' name='date' value = {new Date().toISOString().split('T')[0]} readOnly/>
            <button type="submit">Submit</button>
        </form>
    );
}

export default NewAnnouncementForm;
