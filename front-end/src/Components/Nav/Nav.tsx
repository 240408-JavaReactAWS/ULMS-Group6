import Readt from 'react'
import { Link } from 'react-router-dom'
import './Nav.css'

function Nav() {
    return (
        <div className="nav">
            <Link className="logo" to="/">ULMS</Link>
            <Link to="/">Courses</Link>
        </div>
    )
}

export default Nav;