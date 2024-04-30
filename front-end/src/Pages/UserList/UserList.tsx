import React, { useEffect, useState } from "react";
import axios from 'axios';
import User from "../../interfaces/UserInterface";
import UserForm from "../../interfaces/UserFormInterface";
import Role from "../../interfaces/RoleInterface";
import './UserList.css';

function UserList() {
  const [users, setUsers] = useState<User[]>([]);
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    axios.get('http://localhost:8080/users')
      .then(response => {
        setUsers(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, []);

const deleteUser = (userId : number) => {
    axios.delete(`http://localhost:8080/users/deleteUser/${userId}`)
        .then(response => {
            setUsers(users.filter((user: { userId: number }) => user.userId !== userId));
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
};

    const addUser = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const form = event.currentTarget;
        const newUser: UserForm = {
            username: (form.elements.namedItem('username') as HTMLInputElement).value,
            firstName: (form.elements.namedItem('firstName') as HTMLInputElement).value,
            lastName: (form.elements.namedItem('lastName') as HTMLInputElement).value,
            role: (form.elements.namedItem('role') as HTMLSelectElement).value as Role,
            password: "defaultPassword"
        };
        console.log(newUser);
        axios.post<User>('http://localhost:8080/users/register', newUser)
            .then(response => {
                setUsers((prevUsers: User[]) => [...prevUsers, response.data]);
                handleClose();
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    };

  return (
    <>
      <h2 className="user-list-header">User List</h2>
      <button className= "add-user-botton" onClick={handleShow}>Add User</button>

      {show && (
        <div className ="add-user-form">
          <h2>Add User</h2>
          <form onSubmit={addUser} autoComplete="off">
            <label>
              Username:
              <input type="text" name="username" required/>
            </label>
            <label>
              First Name:
              <input type="text" name="firstName" required/>
            </label>
            <label>
              Last Name:
              <input type="text" name="lastName" required/>
            </label>
            <label>
              Role:
              <select name="role">
                <option value="STUDENT">STUDENT</option>
                <option value="TEACHER">TEACHER</option>
                <option value="ADMIN">ADMIN</option>
              </select>
            </label>
            <button className="submit-button" type="submit">Submit</button>
          </form>
          <button onClick={handleClose}>Close</button>
        </div>
      )}
      <div className="user-table">
        <table>
          <thead>
            <tr>
              <th>UserId</th>
              <th>Username</th>
              <th>Password</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Role</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user : User) => (
              <tr key={user.userId}>
                <td>{user.userId}</td>
                <td>{user.username}</td>
                <td>{user.password}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.role}</td>
                <td>
                  <button className="delete-button" onClick={() => deleteUser(user.userId)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default UserList;