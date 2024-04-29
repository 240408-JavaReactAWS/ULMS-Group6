import { Link} from "react-router-dom";

/**
 * Renders the navigation bar for the admin section.
 * @returns The JSX element representing the admin navigation bar.
 */
function AdminNav() {
  return (
    <>
      <div>
        <div className="nav">
          <Link to={"userList"}>User List</Link>
          <Link to={"courseList"}>CourseList</Link>
        </div>
      </div>
    </>
  );
}

export default AdminNav;
