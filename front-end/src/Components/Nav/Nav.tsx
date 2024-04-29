import Readt from 'react'
import { Link } from 'react-router-dom'
//import './Nav.css'

function Nav() {
    return (
        <div className="nav">
            <Link to="/">Home</Link>
            <Link to="/courses">Courses</Link>
            <Link to="/contact">Contact</Link>
        </div>
    )
}

export default Nav