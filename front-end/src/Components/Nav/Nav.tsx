import Readt from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './Nav.css'

function Nav() {
    const navigate = useNavigate();
    const isLoggedIn = localStorage.getItem('userId') !== null;
    const handleLogout = () => {
        const confirm = window.confirm('Are you sure you want to logout?');
        if(confirm){
            localStorage.clear();
            navigate('/login');
        }
    }
    return (
        <div className="nav">
            <Link className="logo" to="/">ULMS</Link>
            {
                isLoggedIn ? (
                    <>
                        <h1>{localStorage.getItem('role')} DASHBOARD</h1>
                        <button className="logout-button" onClick={handleLogout}>Logout</button>
                    </>
                    
                ):(
                    <>
                        <button className="login-button" onClick={() => navigate('/login')}>Login</button>
                    </>
                )
            }
        </div>
    )
}

export default Nav;