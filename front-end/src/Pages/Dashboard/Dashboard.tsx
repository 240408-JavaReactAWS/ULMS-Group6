import React, {useEffect, useState} from "react";
import { Navigate } from "react-router-dom";

function Dashboard() {
    const [curUser, setCurrentUser] = useState<any>();
    
    useEffect(() => {
        if(!localStorage.getItem("username")){
            return;
        }


        let username = (localStorage.getItem("username") ? localStorage.getItem("username") as string : "");

        let asyncCall = async () => {
            let res = await fetch('http://localhost:8080/users/' + username)
                .then(response => response.json())
                .then(data => setCurrentUser(data))
                .catch(error => {
                    alert("Error Detected");
                    console.error(error)
                });
        }

        asyncCall();
    },[])

    return (
        <>
            {(localStorage.getItem("username")) ? 
                <>
                    {
                        curUser.map((user : any) => {
                           return (<div>
                                <h1>Welcome {user.username}</h1>

                                <table>
                                    <tr>
                                        <td>
                                            // create table of users courses. problem is showing which courses user can see
                                        </td>
                                    </tr>
                                </table>
                           </div> 
                        )})
                    }
                    
                </>
            : 
            <Navigate to="/login" />
            }
        </>
    )
}

export default Dashboard;